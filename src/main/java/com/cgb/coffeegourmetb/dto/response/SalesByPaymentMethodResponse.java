package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class SalesByPaymentMethodResponse {

    private String metodoPago;
    private BigDecimal total;

    public SalesByPaymentMethodResponse() {
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

}