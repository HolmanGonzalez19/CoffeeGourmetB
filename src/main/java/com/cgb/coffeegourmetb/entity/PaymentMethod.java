package com.cgb.coffeegourmetb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entidad que representa los métodos de pago del sistema.
 *
 * Corresponde a la tabla coffeegourmet.metodos_pago.
 */
@Entity
@Table(name = "metodos_pago", schema = "coffeegourmet")
public class PaymentMethod extends BaseStatusEntity {

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    public PaymentMethod() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}