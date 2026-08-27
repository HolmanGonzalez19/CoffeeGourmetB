package com.cgb.coffeegourmetb.dto.request;

import com.cgb.coffeegourmetb.enums.CashMovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateCashMovementRequest {

    @NotNull(message = "El tipo de movimiento es obligatorio.")
    private CashMovementType tipoMovimiento;

    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser mayor que cero.")
    private BigDecimal monto;

    @Size(
            max = 500,
            message = "La descripción no puede superar los 500 caracteres."
    )
    private String descripcion;

    public CreateCashMovementRequest() {
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
}