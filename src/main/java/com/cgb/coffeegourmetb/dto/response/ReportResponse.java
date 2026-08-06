package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class ReportResponse {

    private Long totalVentas;

    private BigDecimal valorTotalVentas;

    private Long totalProductosVendidos;

    private List<ProductSaleResponse> productosMasVendidos;

    public ReportResponse() {
    }

    public Long getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Long totalVentas) {
        this.totalVentas = totalVentas;
    }

    public BigDecimal getValorTotalVentas() {
        return valorTotalVentas;
    }

    public void setValorTotalVentas(BigDecimal valorTotalVentas) {
        this.valorTotalVentas = valorTotalVentas;
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