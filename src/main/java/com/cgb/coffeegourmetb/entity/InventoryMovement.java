package com.cgb.coffeegourmetb.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "movimientos_inventario")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Product producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", columnDefinition = "coffeegourmet.tipo_movimiento", nullable = false)
    private MovementType tipoMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public InventoryMovement() {
    }

    public Long getId() {
        return id;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public MovementType getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(MovementType tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}