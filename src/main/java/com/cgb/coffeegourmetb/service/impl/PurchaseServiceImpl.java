package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreatePurchaseDetailRequest;
import com.cgb.coffeegourmetb.dto.request.CreatePurchaseRequest;
import com.cgb.coffeegourmetb.dto.request.CancelPurchaseRequest;
import com.cgb.coffeegourmetb.dto.response.PurchaseResponse;
import com.cgb.coffeegourmetb.entity.*;
import com.cgb.coffeegourmetb.enums.MovementType;
import com.cgb.coffeegourmetb.enums.PurchaseStatus;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.PurchaseMapper;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.repository.PurchaseDetailRepository;
import com.cgb.coffeegourmetb.repository.PurchaseRepository;
import com.cgb.coffeegourmetb.repository.SupplierRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.InventoryTransactionService;
import com.cgb.coffeegourmetb.service.interfaces.PriceHistoryService;
import com.cgb.coffeegourmetb.service.interfaces.PurchaseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryTransactionService inventoryTransactionService;
    private final PriceHistoryService priceHistoryService;
    private final PurchaseMapper mapper;

    public PurchaseServiceImpl(
            PurchaseRepository purchaseRepository,
            PurchaseDetailRepository purchaseDetailRepository,
            SupplierRepository supplierRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            InventoryTransactionService inventoryTransactionService,
            PriceHistoryService priceHistoryService,
            PurchaseMapper mapper) {

        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.supplierRepository = supplierRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryTransactionService = inventoryTransactionService;
        this.priceHistoryService = priceHistoryService;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseResponse> findAll() {

        return purchaseRepository
                .findAllByOrderByFechaDesc()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PurchaseResponse findById(Long id) {

        Purchase purchase = purchaseRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una compra con id: " + id));

        return mapper.toResponse(purchase);
    }

    @Override
    public PurchaseResponse create(CreatePurchaseRequest request) {

        Supplier supplier = supplierRepository
                .findByIdAndActivoTrue(request.getProveedorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Proveedor no encontrado."));

        User user = userRepository
                .findByIdAndActivoTrue(request.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado."));

        /*
         * Crear la compra.
         */
        Purchase purchase = new Purchase();

        purchase.setProveedor(supplier);
        purchase.setUsuario(user);

        /*
         * El código interno se genera automáticamente.
         *
         * Se utiliza un código temporal basado en la
         * secuencia de la base de datos.
         */
        String codigoCompra =
                generarCodigoCompra();

        purchase.setCodigoCompra(codigoCompra);

        purchase.setFecha(LocalDateTime.now());

        purchase.setObservacion(
                request.getObservacion()
        );

        purchase.setTotal(BigDecimal.ZERO);

        purchase.setEstado(
                PurchaseStatus.REGISTRADA
        );

        purchase = purchaseRepository.save(purchase);

        BigDecimal total = BigDecimal.ZERO;

        /*
         * Registrar detalles de la compra.
         */
        for (CreatePurchaseDetailRequest detailRequest :
                request.getDetalles()) {

            Product product = productRepository
                    .findById(detailRequest.getProductoId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto no encontrado con id: "
                                            + detailRequest.getProductoId()));

            BigDecimal subtotal =
                    detailRequest.getPrecioCompra()
                            .multiply(
                                    BigDecimal.valueOf(
                                            detailRequest.getCantidad()));

            PurchaseDetail detail =
                    new PurchaseDetail();

            detail.setCompra(purchase);

            detail.setProducto(product);

            detail.setCantidad(
                    detailRequest.getCantidad());

            detail.setPrecioCompra(
                    detailRequest.getPrecioCompra());

            detail.setSubtotal(subtotal);

            purchaseDetailRepository.save(detail);

            total = total.add(subtotal);

            /*
             * La compra genera automáticamente
             * una entrada de inventario.
             *
             * La referencia utiliza el código interno
             * generado automáticamente.
             */
            inventoryTransactionService.processMovement(
                    product.getId(),
                    user.getId(),
                    MovementType.ENTRADA,
                    detailRequest.getCantidad(),
                    "Compra proveedor",
                    purchase.getCodigoCompra()
            );

            /*
             * Actualizar historial de precios.
             */
            priceHistoryService.updatePurchasePrice(
                    product.getId(),
                    detailRequest.getPrecioCompra()
            );
        }

        /*
         * Actualizar el total definitivo.
         */
        purchase.setTotal(total);

        purchase = purchaseRepository.save(purchase);

        return mapper.toResponse(purchase);
    }

    /*
     * Genera el código interno de la compra.
     *
     * Formato:
     *
     * COMP-000001
     * COMP-000002
     * COMP-000003
     *
     * El usuario nunca debe escribir este código.
     */
    private String generarCodigoCompra() {

        Long siguienteId =
                purchaseRepository.findNextId();

        return String.format(
                "COMP-%06d",
                siguienteId
        );
    }

    /*
     * Buscar compras por proveedor.
     */
    @Override
    public List<PurchaseResponse> findBySupplier(
            Long supplierId) {

        return purchaseRepository
                .findByProveedorIdOrderByFechaDesc(
                        supplierId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /*
     * Buscar compras por usuario.
     */
    @Override
    public List<PurchaseResponse> findByUser(
            Long userId) {

        return purchaseRepository
                .findByUsuarioIdOrderByFechaDesc(
                        userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /*
     * Buscar compras entre fechas.
     */
    @Override
    public List<PurchaseResponse> findBetweenDates(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        return purchaseRepository
                .findByFechaBetweenOrderByFechaDesc(
                        fechaInicio,
                        fechaFin)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /*
     * Compras realizadas hoy.
     */
    @Override
    public List<PurchaseResponse> findToday() {

        LocalDate hoy = LocalDate.now();

        return findBetweenDates(
                hoy.atStartOfDay(),
                hoy.plusDays(1)
                        .atStartOfDay()
                        .minusNanos(1));
    }

    /*
     * Compras realizadas durante el mes actual.
     */
    @Override
    public List<PurchaseResponse> findCurrentMonth() {

        LocalDate inicio =
                LocalDate.now()
                        .withDayOfMonth(1);

        LocalDate fin =
                inicio.plusMonths(1)
                        .minusDays(1);

        return findBetweenDates(
                inicio.atStartOfDay(),
                fin.atTime(23, 59, 59));
    }

    /*
     * Anular compra.
     */
    @Override
    public void cancel(
            Long purchaseId,
            CancelPurchaseRequest request) {

        Purchase purchase =
                purchaseRepository
                        .findById(purchaseId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe una compra con id: "
                                                + purchaseId));

        if (purchase.getEstado() ==
                PurchaseStatus.ANULADA) {

            throw new BusinessException(
                    "La compra ya fue anulada.");
        }

        User user =
                userRepository
                        .findByIdAndActivoTrue(
                                request.getUsuarioId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Usuario no encontrado."));

        purchase.setEstado(
                PurchaseStatus.ANULADA);

        purchase.setFechaAnulacion(
                LocalDateTime.now());

        purchase.setUsuarioAnulacion(user);

        purchase.setMotivoAnulacion(
                request.getMotivo());

        /*
         * Al anular una compra se revierte
         * automáticamente la entrada de inventario.
         */
        for (PurchaseDetail detail :
                purchase.getDetalles()) {

            inventoryTransactionService.processMovement(
                    detail.getProducto().getId(),
                    user.getId(),
                    MovementType.SALIDA,
                    detail.getCantidad(),
                    "Anulación compra",
                    purchase.getCodigoCompra()
            );
        }

        purchaseRepository.save(purchase);
    }
}