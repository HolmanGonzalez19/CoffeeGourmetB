package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.response.ProductSaleResponse;
import com.cgb.coffeegourmetb.dto.response.StatisticsResponse;
import com.cgb.coffeegourmetb.repository.SaleDetailRepository;
import com.cgb.coffeegourmetb.repository.SaleRepository;
import com.cgb.coffeegourmetb.service.interfaces.StatisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl
        implements StatisticsService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public StatisticsServiceImpl(
            SaleRepository saleRepository,
            SaleDetailRepository saleDetailRepository) {

        this.saleRepository = saleRepository;
        this.saleDetailRepository = saleDetailRepository;
    }

    @Override
    public StatisticsResponse getStatistics() {

        StatisticsResponse response =
                new StatisticsResponse();

        response.setTotalVentas(
                saleRepository.totalSalesCount());

        response.setTotalIngresos(
                saleRepository.totalSalesAmount());

        response.setTotalProductosVendidos(
                saleDetailRepository.totalProductosVendidos(
                        java.time.LocalDate.MIN.atStartOfDay(),
                        java.time.LocalDate.MAX.atStartOfDay()));

        List<ProductSaleResponse> productos =
                new ArrayList<>();

        for (Object[] fila :
                saleDetailRepository.topSellingProducts()) {

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