package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreatePurchaseRequest {

    @NotNull
    private Long proveedorId;

    @NotNull
    private Long usuarioId;

    private String observacion;

    @Valid
    @NotEmpty
    private List<CreatePurchaseDetailRequest> detalles;

    public CreatePurchaseRequest() {
    }

    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<CreatePurchaseDetailRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CreatePurchaseDetailRequest> detalles) {
        this.detalles = detalles;
    }
}