package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class DashboardResponse {

    private Long ventasHoy;
    private BigDecimal totalVentasHoy;
    private Long productosVendidosHoy;
    private Long cajaAbiertaId;
    private BigDecimal efectivoInicial;
    private BigDecimal efectivoEsperado;

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
}