package com.cgb.coffeegourmetb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Clase base para las entidades que manejan
 * estado activo/inactivo.
 */
@MappedSuperclass
public abstract class BaseStatusEntity extends BaseAuditEntity {

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}