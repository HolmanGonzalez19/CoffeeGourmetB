package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Información de una caja.
 */
public class CashRegisterResponse {

    private Long id;

    private Long usuarioAperturaId;

    private String usuarioAperturaNombre;

    private LocalDateTime fechaApertura;

    private BigDecimal montoInicial;

    private String estado;

    private Long usuarioCierreId;

    private String usuarioCierreNombre;

    private LocalDateTime fechaCierre;

    private BigDecimal efectivoEsperado;

    private BigDecimal efectivoContado;

    private BigDecimal diferencia;

    public CashRegisterResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioAperturaId() {
        return usuarioAperturaId;
    }

    public void setUsuarioAperturaId(Long usuarioAperturaId) {
        this.usuarioAperturaId = usuarioAperturaId;
    }

    public String getUsuarioAperturaNombre() {
        return usuarioAperturaNombre;
    }

    public void setUsuarioAperturaNombre(String usuarioAperturaNombre) {
        this.usuarioAperturaNombre = usuarioAperturaNombre;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUsuarioCierreId() {
        return usuarioCierreId;
    }

    public void setUsuarioCierreId(Long usuarioCierreId) {
        this.usuarioCierreId = usuarioCierreId;
    }

    public String getUsuarioCierreNombre() {
        return usuarioCierreNombre;
    }

    public void setUsuarioCierreNombre(String usuarioCierreNombre) {
        this.usuarioCierreNombre = usuarioCierreNombre;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getEfectivoEsperado() {
        return efectivoEsperado;
    }

    public void setEfectivoEsperado(BigDecimal efectivoEsperado) {
        this.efectivoEsperado = efectivoEsperado;
    }

    public BigDecimal getEfectivoContado() {
        return efectivoContado;
    }

    public void setEfectivoContado(BigDecimal efectivoContado) {
        this.efectivoContado = efectivoContado;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }
}