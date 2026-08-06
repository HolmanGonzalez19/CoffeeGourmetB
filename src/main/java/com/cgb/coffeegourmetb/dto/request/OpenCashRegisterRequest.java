package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OpenCashRegisterRequest {

    @NotNull
    private Long usuarioAperturaId;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal montoInicial;

    public OpenCashRegisterRequest() {
    }

    public Long getUsuarioAperturaId() {
        return usuarioAperturaId;
    }

    public void setUsuarioAperturaId(Long usuarioAperturaId) {
        this.usuarioAperturaId = usuarioAperturaId;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }
}