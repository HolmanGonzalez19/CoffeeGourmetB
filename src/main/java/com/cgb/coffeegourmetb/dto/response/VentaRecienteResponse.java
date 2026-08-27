package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VentaRecienteResponse {

    private Long id;
    private LocalDateTime fechaHora;
    private Long cantidadProductos;
    private BigDecimal total;
    private String metodoPago;

    public VentaRecienteResponse() {
    }

    public VentaRecienteResponse(
            Long id,
            LocalDateTime fechaHora,
            Long cantidadProductos,
            BigDecimal total,
            String metodoPago) {

        this.id = id;
        this.fechaHora = fechaHora;
        this.cantidadProductos = cantidadProductos;
        this.total = total;
        this.metodoPago = metodoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Long cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}