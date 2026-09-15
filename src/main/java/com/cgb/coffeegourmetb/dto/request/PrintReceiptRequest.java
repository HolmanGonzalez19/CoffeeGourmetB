package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotNull;

public class PrintReceiptRequest {

    @NotNull
    private Long ventaId;

    public PrintReceiptRequest() {
    }

    public Long getVentaId() {
        return ventaId;
    }

    public void setVentaId(Long ventaId) {
        this.ventaId = ventaId;
    }
}
