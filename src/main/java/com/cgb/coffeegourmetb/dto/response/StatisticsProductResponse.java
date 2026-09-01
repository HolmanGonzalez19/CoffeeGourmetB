package com.cgb.coffeegourmetb.dto.response;

public class StatisticsProductResponse {

    private Long productoId;

    private String productoNombre;

    private Long cantidadVendida;


    public StatisticsProductResponse() {
    }


    public StatisticsProductResponse(
            Long productoId,
            String productoNombre,
            Long cantidadVendida) {

        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.cantidadVendida = cantidadVendida;
    }


    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }


    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }


    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}