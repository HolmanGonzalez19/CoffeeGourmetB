package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CancelSaleRequest {

    @NotNull(message = "El usuario es obligatorio.")
    private Long usuarioId;

    @NotBlank(message = "El motivo de anulación es obligatorio.")
    @Size(max = 500, message = "El motivo no puede superar los 500 caracteres.")
    private String motivo;

    public CancelSaleRequest() {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}