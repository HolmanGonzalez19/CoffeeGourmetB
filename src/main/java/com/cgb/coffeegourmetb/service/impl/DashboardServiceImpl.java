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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
                LocalDate.now().atTime(23,59,59);

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

        response.setTotalVentasHoy(
                dashboardRepository.totalVentasHoy(
                        inicio,
                        fin));

        response.setVentasHoy(
                saleRepository.countVentasHoy(
                        inicio,
                        fin));

        response.setProductosVendidosHoy(
                saleDetailRepository.totalProductosVendidos(
                        inicio,
                        fin));

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

        return response;
    }

}