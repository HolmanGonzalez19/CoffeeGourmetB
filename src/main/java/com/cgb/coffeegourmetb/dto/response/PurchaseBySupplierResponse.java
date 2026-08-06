package com.cgb.coffeegourmetb.dto.response;

import java.math.BigDecimal;

public class PurchaseBySupplierResponse {

    private String proveedor;
    private BigDecimal totalComprado;

    public PurchaseBySupplierResponse() {
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public BigDecimal getTotalComprado() {
        return totalComprado;
    }

    public void setTotalComprado(BigDecimal totalComprado) {
        this.totalComprado = totalComprado;
    }

}