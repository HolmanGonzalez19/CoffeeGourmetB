package com.cgb.coffeegourmetb.dto.request;

import com.cgb.coffeegourmetb.enums.SaleStatus;

import java.time.LocalDateTime;

public class SaleFilterRequest {

    private Long cajaId;

    private Long usuarioId;

    private Long metodoPagoId;

    private SaleStatus estado;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    public SaleFilterRequest() {
    }

    public Long getCajaId() {
        return cajaId;
    }

    public void setCajaId(Long cajaId) {
        this.cajaId = cajaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public SaleStatus getEstado() {
        return estado;
    }

    public void setEstado(SaleStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }
}