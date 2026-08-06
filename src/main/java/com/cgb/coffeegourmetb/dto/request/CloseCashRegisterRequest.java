package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CloseCashRegisterRequest {

    @NotNull
    private Long usuarioCierreId;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal efectivoContado;

    public CloseCashRegisterRequest() {
    }

    public Long getUsuarioCierreId() {
        return usuarioCierreId;
    }

    public void setUsuarioCierreId(Long usuarioCierreId) {
        this.usuarioCierreId = usuarioCierreId;
    }

    public BigDecimal getEfectivoContado() {
        return efectivoContado;
    }

    public void setEfectivoContado(BigDecimal efectivoContado) {
        this.efectivoContado = efectivoContado;
    }
}