package com.cgb.coffeegourmetb.dto.response;

public class ProductoMasVendidoResponse {

    private Long productoId;
    private String producto;
    private Long cantidadVendida;

    public ProductoMasVendidoResponse() {
    }

    public ProductoMasVendidoResponse(
            Long productoId,
            String producto,
            Long cantidadVendida) {

        this.productoId = productoId;
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}