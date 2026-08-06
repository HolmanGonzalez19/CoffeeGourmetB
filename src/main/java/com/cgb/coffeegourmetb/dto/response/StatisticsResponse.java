package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class StatisticsResponse {

    private Long totalVentas;

    private BigDecimal totalIngresos;

    private Long totalProductosVendidos;

    private List<ProductSaleResponse> productosMasVendidos;

    public StatisticsResponse() {
    }

    public Long getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Long totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(BigDecimal totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public Long getTotalProductosVendidos() {
        return totalProductosVendidos;
    }

    public void setTotalProductosVendidos(Long totalProductosVendidos) {
        this.totalProductosVendidos = totalProductosVendidos;
    }

    public List<ProductSaleResponse> getProductosMasVendidos() {
        return productosMasVendidos;
    }

    public void setProductosMasVendidos(
            List<ProductSaleResponse> productosMasVendidos) {
        this.productosMasVendidos = productosMasVendidos;
    }

}