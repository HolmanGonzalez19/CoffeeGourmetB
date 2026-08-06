package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class SalesByPeriodResponse {

    private String periodo;
    private BigDecimal total;

    public SalesByPeriodResponse() {
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}