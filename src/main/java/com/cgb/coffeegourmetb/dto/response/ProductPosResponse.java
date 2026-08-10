package com.cgb.coffeegourmetb.dto.response;

import com.cgb.coffeegourmetb.enums.ProductType;

import java.math.BigDecimal;

public class ProductPosResponse {

    private Long id;
    private String codigo;
    private String codigoBarras;
    private String nombre;

    private Long categoriaId;
    private String categoriaNombre;

    private ProductType tipoProducto;

    private BigDecimal precioVenta;

    public ProductPosResponse() {
    }

    public ProductPosResponse(
            Long id,
            String codigo,
            String codigoBarras,
            String nombre,
            Long categoriaId,
            String categoriaNombre,
            ProductType tipoProducto,
            BigDecimal precioVenta) {

        this.id = id;
        this.codigo = codigo;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.tipoProducto = tipoProducto;
        this.precioVenta = precioVenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public ProductType getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(ProductType tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}