package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponse {

    private Long ventasHoy;
    private BigDecimal totalVentasHoy;
    private Long productosVendidosHoy;

    private Long cajaAbiertaId;
    private BigDecimal efectivoInicial;
    private BigDecimal efectivoEsperado;

    private Long productosStockBajo;

    private Long totalVentas;
    private BigDecimal totalIngresos;

    private Long totalCompras;
    private BigDecimal totalEgresos;

    private Long totalProductos;

    private List<VentaRecienteResponse> ventasRecientes;

    private List<DistribucionVentaResponse> distribucionVentas;

    private List<ProductoMasVendidoResponse> productosMasVendidos;

    private List<VentaPorMesResponse> ventasPorMes;

    public DashboardResponse() {
    }

    public Long getVentasHoy() {
        return ventasHoy;
    }

    public void setVentasHoy(Long ventasHoy) {
        this.ventasHoy = ventasHoy;
    }

    public BigDecimal getTotalVentasHoy() {
        return totalVentasHoy;
    }

    public void setTotalVentasHoy(BigDecimal totalVentasHoy) {
        this.totalVentasHoy = totalVentasHoy;
    }

    public Long getProductosVendidosHoy() {
        return productosVendidosHoy;
    }

    public void setProductosVendidosHoy(Long productosVendidosHoy) {
        this.productosVendidosHoy = productosVendidosHoy;
    }

    public Long getCajaAbiertaId() {
        return cajaAbiertaId;
    }

    public void setCajaAbiertaId(Long cajaAbiertaId) {
        this.cajaAbiertaId = cajaAbiertaId;
    }

    public BigDecimal getEfectivoInicial() {
        return efectivoInicial;
    }

    public void setEfectivoInicial(BigDecimal efectivoInicial) {
        this.efectivoInicial = efectivoInicial;
    }

    public BigDecimal getEfectivoEsperado() {
        return efectivoEsperado;
    }

    public void setEfectivoEsperado(BigDecimal efectivoEsperado) {
        this.efectivoEsperado = efectivoEsperado;
    }

    public Long getProductosStockBajo() {
        return productosStockBajo;
    }

    public void setProductosStockBajo(Long productosStockBajo) {
        this.productosStockBajo = productosStockBajo;
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

    public Long getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(Long totalCompras) {
        this.totalCompras = totalCompras;
    }

    public BigDecimal getTotalEgresos() {
        return totalEgresos;
    }

    public void setTotalEgresos(BigDecimal totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    public Long getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Long totalProductos) {
        this.totalProductos = totalProductos;
    }

    public List<VentaRecienteResponse> getVentasRecientes() {
        return ventasRecientes;
    }

    public void setVentasRecientes(List<VentaRecienteResponse> ventasRecientes) {
        this.ventasRecientes = ventasRecientes;
    }

    public List<DistribucionVentaResponse> getDistribucionVentas() {
        return distribucionVentas;
    }

    public void setDistribucionVentas(List<DistribucionVentaResponse> distribucionVentas) {
        this.distribucionVentas = distribucionVentas;
    }

    public List<ProductoMasVendidoResponse> getProductosMasVendidos() {
        return productosMasVendidos;
    }

    public void setProductosMasVendidos(
            List<ProductoMasVendidoResponse> productosMasVendidos) {

        this.productosMasVendidos = productosMasVendidos;
    }

    public List<VentaPorMesResponse> getVentasPorMes() {
        return ventasPorMes;
    }

    public void setVentasPorMes(
            List<VentaPorMesResponse> ventasPorMes) {

        this.ventasPorMes = ventasPorMes;
    }

}