package com.cgb.coffeegourmetb.dto.response;

import com.cgb.coffeegourmetb.enums.CashMovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashMovementResponse {

    private Long id;

    private Long cajaId;

    private Long usuarioId;

    private String usuarioNombre;

    private CashMovementType tipoMovimiento;

    private BigDecimal monto;

    private String descripcion;

    private LocalDateTime fechaMovimiento;

    public CashMovementResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public CashMovementType getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(
            CashMovementType tipoMovimiento) {

        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(
            LocalDateTime fechaMovimiento) {

        this.fechaMovimiento = fechaMovimiento;
    }
}