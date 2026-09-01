package com.cgb.coffeegourmetb.dto.request;

import com.cgb.coffeegourmetb.enums.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateInventoryMovementRequest {

    @NotNull(message = "El producto es obligatorio.")
    private Long productoId;

    @NotNull(message = "El usuario es obligatorio.")
    private Long usuarioId;

    @NotNull(message = "El tipo de movimiento es obligatorio.")
    private MovementType tipoMovimiento;

    @NotNull(message = "La cantidad es obligatoria.")
    @Min(value = 1, message = "La cantidad debe ser mayor que cero.")
    private Integer cantidad;

    @Size(max = 255)
    private String motivo;

    private Long compraId;

    public CreateInventoryMovementRequest() {
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public MovementType getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(MovementType tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }
}