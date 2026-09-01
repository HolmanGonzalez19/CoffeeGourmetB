package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.response.DashboardResponse;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import com.cgb.coffeegourmetb.repository.CashRegisterRepository;
import com.cgb.coffeegourmetb.repository.DashboardRepository;
import com.cgb.coffeegourmetb.repository.SaleDetailRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.service.interfaces.DashboardService;
import org.springframework.stereotype.Service;
import com.cgb.coffeegourmetb.dto.response.DistribucionVentaResponse;
import com.cgb.coffeegourmetb.dto.response.VentaRecienteResponse;
import com.cgb.coffeegourmetb.entity.Sale;
import com.cgb.coffeegourmetb.enums.SaleStatus;
import com.cgb.coffeegourmetb.dto.response.ProductoMasVendidoResponse;
import com.cgb.coffeegourmetb.dto.response.VentaPorMesResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl
        implements DashboardService {

    private final DashboardRepository dashboardRepository;
    private final CashRegisterRepository cashRegisterRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public DashboardServiceImpl(
            DashboardRepository dashboardRepository,
            CashRegisterRepository cashRegisterRepository,
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {

        this.dashboardRepository = dashboardRepository;
        this.cashRegisterRepository = cashRegisterRepository;
        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    @Override
    public DashboardResponse obtenerDashboard() {

        DashboardResponse response = new DashboardResponse();

        LocalDateTime inicio =
                LocalDate.now().atStartOfDay();

        LocalDateTime fin =
                LocalDate.now().atTime(23, 59, 59);

        // ========================================================
        // INDICADORES GENERALES
        // ========================================================

        response.setTotalVentas(
                dashboardRepository.totalVentas());

        response.setTotalIngresos(
                dashboardRepository.totalIngresos());

        response.setTotalCompras(
                dashboardRepository.totalCompras());

        response.setTotalEgresos(
                dashboardRepository.totalEgresos());

        response.setTotalProductos(
                dashboardRepository.totalProductos());

        response.setProductosStockBajo(
                dashboardRepository.productosStockBajo());


        // ========================================================
        // INDICADORES DEL DÍA
        // ========================================================

        response.setTotalVentasHoy(
                dashboardRepository.totalVentasHoy(
                        inicio,
                        fin));

        response.setVentasHoy(
                saleRepository.countSales(
                        inicio,
                        fin));

        response.setProductosVendidosHoy(
                saleDetailRepository.totalProductosVendidos(
                        inicio,
                        fin));


        // ========================================================
        // CAJA
        // ========================================================

        Optional<CashRegister> caja =
                cashRegisterRepository.findByEstado(
                        CashRegisterStatus.ABIERTA);

        if (caja.isPresent()) {

            CashRegister actual = caja.get();

            response.setCajaAbiertaId(
                    actual.getId());

            response.setEfectivoInicial(
                    actual.getMontoInicial());

            response.setEfectivoEsperado(
                    actual.getEfectivoEsperado());

        } else {

            response.setCajaAbiertaId(null);

            response.setEfectivoInicial(
                    BigDecimal.ZERO);

            response.setEfectivoEsperado(
                    BigDecimal.ZERO);
        }


        // ========================================================
        // VENTAS RECIENTES
        // ========================================================

        List<Sale> ventasRecientes =
                saleRepository.findTop5ByEstadoOrderByFechaHoraDesc(
                        SaleStatus.REGISTRADA);

        List<VentaRecienteResponse> ventas =
                new ArrayList<>();

        for (Sale venta : ventasRecientes) {

            Long cantidadProductos =
                    saleDetailRepository.cantidadProductosPorVenta(
                            venta.getId());

            ventas.add(
                    new VentaRecienteResponse(
                            venta.getId(),
                            venta.getFechaHora(),
                            cantidadProductos,
                            venta.getTotal(),
                            venta.getMetodoPago().getNombre()
                    )
            );
        }

        response.setVentasRecientes(ventas);


        // ========================================================
        // DISTRIBUCIÓN DE VENTAS
        // ========================================================

        List<Object[]> distribucion =
                saleRepository.ventasPorMetodoPago();

        BigDecimal totalIngresos =
                response.getTotalIngresos();

        List<DistribucionVentaResponse> distribucionResponse =
                new ArrayList<>();

        for (Object[] fila : distribucion) {

            String metodoPago =
                    (String) fila[0];

            BigDecimal total =
                    (BigDecimal) fila[1];

            BigDecimal porcentaje =
                    BigDecimal.ZERO;

            if (totalIngresos.compareTo(BigDecimal.ZERO) > 0) {

                porcentaje =
                        total
                                .multiply(BigDecimal.valueOf(100))
                                .divide(
                                        totalIngresos,
                                        2,
                                        RoundingMode.HALF_UP
                                );
            }

            distribucionResponse.add(
                    new DistribucionVentaResponse(
                            metodoPago,
                            total,
                            porcentaje
                    )
            );
        }

        response.setDistribucionVentas(
                distribucionResponse);

        // ========================================================
// PRODUCTOS MÁS VENDIDOS
// ========================================================

        List<Object[]> productosMasVendidosData =
                saleDetailRepository.topSellingProducts();

        List<ProductoMasVendidoResponse> productosMasVendidos =
                new ArrayList<>();

        for (Object[] fila : productosMasVendidosData) {

            Long productoId =
                    ((Number) fila[0]).longValue();

            String producto =
                    (String) fila[1];

            Long cantidadVendida =
                    ((Number) fila[2]).longValue();

            productosMasVendidos.add(
                    new ProductoMasVendidoResponse(
                            productoId,
                            producto,
                            cantidadVendida
                    )
            );
        }

        response.setProductosMasVendidos(
                productosMasVendidos);


        // ========================================================
// EVOLUCIÓN DE VENTAS POR MES
// ========================================================

        List<Object[]> ventasPorMesData =
                saleRepository.ventasPorMes();

        List<VentaPorMesResponse> ventasPorMes =
                new ArrayList<>();

        for (Object[] fila : ventasPorMesData) {

            String mes =
                    (String) fila[0];

            BigDecimal total =
                    (BigDecimal) fila[1];

            ventasPorMes.add(
                    new VentaPorMesResponse(
                            mes,
                            total
                    )
            );
        }

        response.setVentasPorMes(
                ventasPorMes);

        return response;
    }

}