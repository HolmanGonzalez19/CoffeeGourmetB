package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OpenCashRegisterRequest {

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal montoInicial;

    public OpenCashRegisterRequest() {
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }
}