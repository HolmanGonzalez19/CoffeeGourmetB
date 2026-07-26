package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateSaleRequest {

    @NotNull(message = "El usuario es obligatorio.")
    private Long usuarioId;

    @NotNull(message = "El método de pago es obligatorio.")
    private Long metodoPagoId;

    private String observacion;

    @NotEmpty(message = "La venta debe contener al menos un producto.")
    @Valid
    private List<CreateSaleDetailRequest> detalles;

    public CreateSaleRequest() {
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<CreateSaleDetailRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CreateSaleDetailRequest> detalles) {
        this.detalles = detalles;
    }
}