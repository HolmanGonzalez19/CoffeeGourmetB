package com.cgb.coffeegourmetb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventario")
public class Inventory extends BaseAuditEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false, unique = true)
    private Product producto;

    @Column(name = "cantidad_actual", nullable = false)
    private Integer cantidadActual = 0;

    public Inventory() {
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }
}