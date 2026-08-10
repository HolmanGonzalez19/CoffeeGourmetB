package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CancelSaleRequest;
import com.cgb.coffeegourmetb.dto.request.CreateSaleDetailRequest;
import com.cgb.coffeegourmetb.dto.request.CreateSaleRequest;
import com.cgb.coffeegourmetb.dto.response.SaleResponse;
import com.cgb.coffeegourmetb.enums.MovementType;
import com.cgb.coffeegourmetb.entity.PaymentMethod;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.entity.Sale;
import com.cgb.coffeegourmetb.entity.SaleDetail;
import com.cgb.coffeegourmetb.enums.SaleStatus;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.SaleMapper;
import com.cgb.coffeegourmetb.repository.PaymentMethodRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.InventoryTransactionService;
import com.cgb.coffeegourmetb.service.interfaces.SaleService;
import org.springframework.stereotype.Service;
import com.cgb.coffeegourmetb.repository.CashRegisterRepository;
import com.cgb.coffeegourmetb.repository.SaleDetailRepository;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import com.cgb.coffeegourmetb.entity.CashRegister;
import org.springframework.transaction.annotation.Transactional;
import com.cgb.coffeegourmetb.entity.PriceHistory;
import com.cgb.coffeegourmetb.repository.PriceHistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final InventoryTransactionService inventoryTransactionService;
    private final SaleMapper saleMapper;
    private final CashRegisterRepository cashRegisterRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public SaleServiceImpl(
            SaleRepository saleRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            PaymentMethodRepository paymentMethodRepository,
            InventoryTransactionService inventoryTransactionService,
            SaleMapper saleMapper,
            CashRegisterRepository cashRegisterRepository,
            SaleDetailRepository saleDetailRepository,
            PriceHistoryRepository priceHistoryRepository) {

        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.inventoryTransactionService = inventoryTransactionService;
        this.saleMapper = saleMapper;
        this.cashRegisterRepository = cashRegisterRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public List<SaleResponse> findAll() {

        return saleRepository
                .findAllByOrderByFechaHoraDesc()
                .stream()
                .map(saleMapper::toResponse)
                .toList();
    }

    @Override
    public SaleResponse findById(Long id) {

        Sale sale = saleRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una venta registrada con id: " + id));

        return saleMapper.toResponse(sale);
    }

    @Override
    public SaleResponse create(CreateSaleRequest request) {

        User usuario = userRepository
                .findByIdAndActivoTrue(request.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado o inactivo."));

        CashRegister caja = cashRegisterRepository
                .findByEstado(CashRegisterStatus.ABIERTA)
                .orElseThrow(() ->
                        new BusinessException(
                                "No existe una caja abierta."));

        PaymentMethod metodoPago = paymentMethodRepository
                .findByIdAndActivoTrue(request.getMetodoPagoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Método de pago no encontrado o inactivo."));

        Sale sale = new Sale();

        sale.setCaja(caja);
        sale.setUsuario(usuario);
        sale.setMetodoPago(metodoPago);
        sale.setFechaHora(LocalDateTime.now());
        sale.setObservacion(request.getObservacion());
        sale.setEstado(SaleStatus.REGISTRADA);
        sale.setTotal(BigDecimal.ZERO);

        BigDecimal total = BigDecimal.ZERO;

        for (CreateSaleDetailRequest detailRequest :
                request.getDetalles()) {

            Product producto = productRepository
                    .findByIdAndActivoTrue(
                            detailRequest.getProductoId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto no encontrado o inactivo con id: "
                                            + detailRequest.getProductoId()));

            PriceHistory precioVigente = priceHistoryRepository
                    .findByProductoIdAndActivoTrue(producto.getId())
                    .orElseThrow(() ->
                            new BusinessException(
                                    "El producto '" + producto.getNombre()
                                            + "' no tiene un precio vigente."));

            BigDecimal precioVenta = precioVigente.getPrecioVenta();

            if (precioVenta == null
                    || precioVenta.compareTo(BigDecimal.ZERO) <= 0) {

                throw new BusinessException(
                        "El producto '" + producto.getNombre()
                                + "' no tiene un precio de venta válido.");
            }

            BigDecimal subtotal = precioVenta
                    .multiply(
                            BigDecimal.valueOf(
                                    detailRequest.getCantidad()));

            SaleDetail detalle = new SaleDetail();

            detalle.setVenta(sale);
            detalle.setProducto(producto);
            detalle.setCantidad(detailRequest.getCantidad());
            detalle.setPrecioUnitario(precioVenta);
            detalle.setSubtotal(subtotal);

            sale.getDetalles().add(detalle);

            total = total.add(subtotal);
        }

        sale.setTotal(total);

        /*
         * Primero se guarda la venta con sus detalles.
         * CascadeType.ALL permite persistir los detalles.
         */
        Sale ventaGuardada = saleRepository.save(sale);

        /*
         * Solo las ventas pagadas en efectivo
         * incrementan el efectivo esperado de la caja.
         */
        if ("EFECTIVO".equalsIgnoreCase(
                ventaGuardada.getMetodoPago().getNombre())) {

            caja.setEfectivoEsperado(
                    caja.getEfectivoEsperado() == null
                            ? ventaGuardada.getTotal()
                            : caja.getEfectivoEsperado()
                            .add(ventaGuardada.getTotal()));

            cashRegisterRepository.save(caja);
        }

        cashRegisterRepository.save(caja);
        /*
         * Se descuenta el inventario después de guardar
         * la venta.
         *
         * Si existe stock insuficiente, InventoryTransactionService
         * lanza una BusinessException y la transacción completa
         * realiza rollback.
         */
        for (SaleDetail detalle : ventaGuardada.getDetalles()) {

            inventoryTransactionService.processMovement(
                    detalle.getProducto().getId(),
                    usuario.getId(),
                    MovementType.SALIDA,
                    detalle.getCantidad(),
                    "Salida de inventario por venta",
                    "VENTA-" + ventaGuardada.getId()
            );
        }

        return saleMapper.toResponse(ventaGuardada);
    }

    @Override
    public List<SaleResponse> findByUser(Long userId) {

        return saleRepository
                .findByUsuarioIdOrderByFechaHoraDesc(userId)
                .stream()
                .map(saleMapper::toResponse)
                .toList();
    }

    @Override
    public List<SaleResponse> findByPaymentMethod(
            Long paymentMethodId) {

        return saleRepository
                .findByMetodoPagoIdOrderByFechaHoraDesc(
                        paymentMethodId)
                .stream()
                .map(saleMapper::toResponse)
                .toList();
    }

    @Override
    public List<SaleResponse> findToday() {

        LocalDate hoy = LocalDate.now();

        return findBetweenDates(
                hoy.atStartOfDay(),
                hoy.plusDays(1).atStartOfDay());
    }

    @Override
    public List<SaleResponse> findCurrentMonth() {

        LocalDate inicioMes = LocalDate
                .now()
                .withDayOfMonth(1);

        LocalDate inicioSiguienteMes = inicioMes
                .plusMonths(1);

        return findBetweenDates(
                inicioMes.atStartOfDay(),
                inicioSiguienteMes.atStartOfDay());
    }

    @Override
    public List<SaleResponse> findBetweenDates(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        if (fechaInicio == null || fechaFin == null) {

            throw new BusinessException(
                    "Las fechas de inicio y fin son obligatorias.");
        }

        if (fechaInicio.isAfter(fechaFin)) {

            throw new BusinessException(
                    "La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        return saleRepository
                .findByFechaHoraBetweenOrderByFechaHoraDesc(
                        fechaInicio,
                        fechaFin)
                .stream()
                .map(saleMapper::toResponse)
                .toList();
    }

    @Override
    public void cancel(
            Long id,
            CancelSaleRequest request) {

        Sale sale = saleRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una venta registrada con id: " + id));

        if (sale.getEstado() == SaleStatus.ANULADA) {

            throw new BusinessException(
                    "La venta ya se encuentra anulada.");
        }

        User usuarioAnulacion = userRepository
                .findByIdAndActivoTrue(request.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado o inactivo."));

        /*
         * Devuelve al inventario las cantidades
         * descontadas originalmente por la venta.
         */
        for (SaleDetail detalle : sale.getDetalles()) {

            inventoryTransactionService.processMovement(
                    detalle.getProducto().getId(),
                    usuarioAnulacion.getId(),
                    MovementType.ENTRADA,
                    detalle.getCantidad(),
                    "Entrada de inventario por anulación de venta",
                    "ANULACION-VENTA-" + sale.getId()
            );
        }

        sale.setEstado(SaleStatus.ANULADA);
        sale.setFechaAnulacion(LocalDateTime.now());
        sale.setUsuarioAnulacion(usuarioAnulacion);
        sale.setMotivoAnulacion(request.getMotivo());

        /*
         * Solo una venta pagada en efectivo afecta
         * nuevamente el efectivo esperado al ser anulada.
         */
        if (sale.getCaja() != null
                && "EFECTIVO".equalsIgnoreCase(
                sale.getMetodoPago().getNombre())) {

            sale.getCaja().setEfectivoEsperado(
                    sale.getCaja().getEfectivoEsperado()
                            .subtract(sale.getTotal()));

            cashRegisterRepository.save(sale.getCaja());
        }
        saleRepository.save(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public Long contarVentasDelDia(LocalDate fecha) {

        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return saleRepository.countVentasRegistradas(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalVentasDelDia(LocalDate fecha) {

        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return saleRepository.totalVentasRegistradas(inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public Long productosVendidosDelDia(LocalDate fecha) {

        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(LocalTime.MAX);

        return saleDetailRepository.totalProductosVendidos(inicio, fin);
    }
}