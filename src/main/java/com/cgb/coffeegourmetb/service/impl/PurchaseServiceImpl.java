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
import java.time.LocalDateTime;
import java.time.LocalDate;
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
                .findAll()
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

        if (purchaseRepository.existsByNumeroRecibo(request.getNumeroRecibo())) {

            throw new BusinessException(
                    "Ya existe una compra con ese número de recibo.");
        }

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

        Purchase purchase = new Purchase();

        purchase.setProveedor(supplier);
        purchase.setUsuario(user);
        purchase.setNumeroRecibo(request.getNumeroRecibo());
        purchase.setFecha(LocalDateTime.now());
        purchase.setObservacion(request.getObservacion());
        purchase.setTotal(BigDecimal.ZERO);
        purchase.setEstado(PurchaseStatus.REGISTRADA);

        purchase = purchaseRepository.save(purchase);

        BigDecimal total = BigDecimal.ZERO;

        for (CreatePurchaseDetailRequest detailRequest : request.getDetalles()) {

            Product product = productRepository.findById(detailRequest.getProductoId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto no encontrado con id: "
                                            + detailRequest.getProductoId()));

            BigDecimal subtotal = detailRequest.getPrecioCompra()
                    .multiply(BigDecimal.valueOf(detailRequest.getCantidad()));

            PurchaseDetail detail = new PurchaseDetail();

            detail.setCompra(purchase);
            detail.setProducto(product);
            detail.setCantidad(detailRequest.getCantidad());
            detail.setPrecioCompra(detailRequest.getPrecioCompra());
            detail.setSubtotal(subtotal);

            purchaseDetailRepository.save(detail);

            total = total.add(subtotal);

            /*
             * Actualiza inventario y registra el movimiento.
             */
            inventoryTransactionService.processMovement(
                    product.getId(),
                    user.getId(),
                    MovementType.ENTRADA,
                    detailRequest.getCantidad(),
                    "Compra proveedor",
                    request.getNumeroRecibo()
            );

            /*
             * Actualiza el historial de precios.
             */
            priceHistoryService.updatePurchasePrice(
                    product.getId(),
                    detailRequest.getPrecioCompra()
            );
        }

        purchase.setTotal(total);

        purchase = purchaseRepository.save(purchase);

        return mapper.toResponse(purchase);
    }

    /*
        Buscar por número de recibo
     */
    @Override
    public PurchaseResponse findByReceipt(String numeroRecibo) {

        Purchase purchase = purchaseRepository
                .findByNumeroRecibo(numeroRecibo)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una compra con el recibo: "
                                        + numeroRecibo));

        return mapper.toResponse(purchase);
    }

    /*
        Buscar por proveedor
     */
    @Override
    public List<PurchaseResponse> findBySupplier(Long supplierId) {

        return purchaseRepository
                .findByProveedorIdOrderByFechaDesc(supplierId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /*
        Buscar por usuario
     */
    @Override
    public List<PurchaseResponse> findByUser(Long userId) {

        return purchaseRepository
                .findByUsuarioIdOrderByFechaDesc(userId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    /*
        Buscar entre fechas
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
        Compras del día
     */
    @Override
    public List<PurchaseResponse> findToday() {

        LocalDate hoy = LocalDate.now();

        return findBetweenDates(
                hoy.atStartOfDay(),
                hoy.plusDays(1).atStartOfDay().minusNanos(1));
    }

    /*
        Compras del mes
     */
    @Override
    public List<PurchaseResponse> findCurrentMonth() {

        LocalDate inicio = LocalDate.now()
                .withDayOfMonth(1);

        LocalDate fin = inicio.plusMonths(1)
                .minusDays(1);

        return findBetweenDates(
                inicio.atStartOfDay(),
                fin.atTime(23,59,59));
    }

    /*
        Cancelar compra
     */
    @Override
    public void cancel(
            Long purchaseId,
            CancelPurchaseRequest request) {

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe la compra con id: " + purchaseId));

        if (purchase.getEstado() == PurchaseStatus.ANULADA) {

            throw new BusinessException(
                    "La compra ya fue anulada.");

        }

        User user = userRepository
                .findByIdAndActivoTrue(request.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado."));

        purchase.setEstado(PurchaseStatus.ANULADA);

        purchase.setFechaAnulacion(LocalDateTime.now());

        purchase.setUsuarioAnulacion(user);

        purchase.setMotivoAnulacion(request.getMotivo());

        for (PurchaseDetail detail : purchase.getDetalles()) {

            inventoryTransactionService.processMovement(

                    detail.getProducto().getId(),

                    user.getId(),

                    MovementType.SALIDA,

                    detail.getCantidad(),

                    "Anulación compra",

                    purchase.getNumeroRecibo()

            );

        }

        purchaseRepository.save(purchase);

    }
}