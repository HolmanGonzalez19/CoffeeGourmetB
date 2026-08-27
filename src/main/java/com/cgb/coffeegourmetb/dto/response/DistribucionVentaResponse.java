package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class DistribucionVentaResponse {

    private String metodoPago;
    private BigDecimal total;
    private BigDecimal porcentaje;

    public DistribucionVentaResponse() {
    }

    public DistribucionVentaResponse(
            String metodoPago,
            BigDecimal total,
            BigDecimal porcentaje) {

        this.metodoPago = metodoPago;
        this.total = total;
        this.porcentaje = porcentaje;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }
}