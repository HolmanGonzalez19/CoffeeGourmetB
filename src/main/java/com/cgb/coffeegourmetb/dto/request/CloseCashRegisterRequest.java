package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class CloseCashRegisterRequest {

    @NotNull(message = "El efectivo contado es obligatorio.")
    @PositiveOrZero(message = "El efectivo contado no puede ser negativo.")
    private BigDecimal efectivoContado;

    public CloseCashRegisterRequest() {
    }

    public BigDecimal getEfectivoContado() {
        return efectivoContado;
    }

    public void setEfectivoContado(BigDecimal efectivoContado) {
        this.efectivoContado = efectivoContado;
    }
}