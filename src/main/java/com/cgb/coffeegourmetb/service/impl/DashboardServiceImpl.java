package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.response.DashboardResponse;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import com.cgb.coffeegourmetb.repository.CashMovementRepository;
import com.cgb.coffeegourmetb.repository.CashRegisterRepository;
import com.cgb.coffeegourmetb.repository.SaleDetailRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.service.interfaces.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl
        implements DashboardService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final CashRegisterRepository cashRegisterRepository;
    private final CashMovementRepository cashMovementRepository;

    public DashboardServiceImpl(
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository,
            CashRegisterRepository cashRegisterRepository,
            CashMovementRepository cashMovementRepository) {

        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
        this.cashRegisterRepository = cashRegisterRepository;
        this.cashMovementRepository = cashMovementRepository;
    }

    @Override
    public DashboardResponse getDashboard() {

        LocalDate hoy = LocalDate.now();

        LocalDateTime inicio = hoy.atStartOfDay();

        LocalDateTime fin = hoy.plusDays(1)
                .atStartOfDay();

        DashboardResponse response =
                new DashboardResponse();

        response.setVentasHoy(
                saleRepository.countSales(
                        inicio,
                        fin));

        response.setTotalVentasHoy(
                saleRepository.totalSales(
                        inicio,
                        fin));

        response.setProductosVendidosHoy(
                saleDetailRepository.totalProductosVendidos(
                        inicio,
                        fin));

        cashRegisterRepository
                .findByEstado(
                        CashRegisterStatus.ABIERTA)
                .ifPresent(caja -> llenarDatosCaja(
                        caja,
                        response));

        return response;
    }

    private void llenarDatosCaja(
            CashRegister caja,
            DashboardResponse response) {

        response.setCajaAbiertaId(
                caja.getId());

        response.setEfectivoInicial(
                caja.getMontoInicial());

        BigDecimal ventas =
                saleRepository.sumTotalByCaja(
                        caja);

        if (ventas == null) {
            ventas = BigDecimal.ZERO;
        }

        response.setEfectivoEsperado(
                caja.getMontoInicial()
                        .add(ventas));
    }

}