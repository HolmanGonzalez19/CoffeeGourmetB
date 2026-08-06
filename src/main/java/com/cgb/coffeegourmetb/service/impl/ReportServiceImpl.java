package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.response.ProductSaleResponse;
import com.cgb.coffeegourmetb.dto.response.ReportResponse;
import com.cgb.coffeegourmetb.repository.SaleDetailRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.service.interfaces.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl
        implements ReportService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public ReportServiceImpl(
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {

        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    @Override
    public ReportResponse salesReport(
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        LocalDateTime inicio =
                fechaInicio.atStartOfDay();

        LocalDateTime fin =
                fechaFin
                        .plusDays(1)
                        .atStartOfDay();

        ReportResponse response =
                new ReportResponse();

        response.setTotalVentas(
                saleRepository.countSales(
                        inicio,
                        fin));

        response.setValorTotalVentas(
                saleRepository.totalSales(
                        inicio,
                        fin));

        response.setTotalProductosVendidos(
                saleDetailRepository
                        .totalProductosVendidos(
                                inicio,
                                fin));

        List<ProductSaleResponse> productos =
                new ArrayList<>();

        List<Object[]> consulta =
                saleDetailRepository
                        .topSellingProductsBetweenDates(
                                inicio,
                                fin);

        for (Object[] fila : consulta) {

            ProductSaleResponse dto =
                    new ProductSaleResponse();

            dto.setProductoId(
                    (Long) fila[0]);

            dto.setProducto(
                    (String) fila[1]);

            dto.setCantidadVendida(
                    ((Number) fila[2]).longValue());

            productos.add(dto);
        }

        response.setProductosMasVendidos(
                productos);

        return response;
    }

}