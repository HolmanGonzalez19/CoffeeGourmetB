package com.cgb.coffeegourmetb.dto.request;

import com.cgb.coffeegourmetb.entity.ProductType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class UpdateProductRequest {

    @NotBlank(message = "El código es obligatorio.")
    @Size(max = 30)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 150)
    private String nombre;

    @NotNull(message = "La categoría es obligatoria.")
    private Long categoriaId;

    @NotNull(message = "El tipo de producto es obligatorio.")
    private ProductType tipoProducto;

    @Size(max = 50)
    private String codigoBarras;

    @NotNull(message = "El stock mínimo es obligatorio.")
    @Min(0)
    private Integer stockMinimo;

    @Size(max = 255)
    private String descripcion;

    @NotNull(message = "El estado es obligatorio.")
    private Boolean activo;

    public UpdateProductRequest() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public ProductType getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(ProductType tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}