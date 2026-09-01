package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class StatisticsMonthResponse {

    private String mes;

    private BigDecimal total;


    public StatisticsMonthResponse() {
    }


    public StatisticsMonthResponse(
            String mes,
            BigDecimal total) {

        this.mes = mes;
        this.total = total;
    }


    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}