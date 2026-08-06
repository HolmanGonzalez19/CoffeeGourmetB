package com.cgb.coffeegourmetb.entity;

import com.cgb.coffeegourmetb.enums.CashMovementType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa un movimiento de efectivo realizado sobre una caja.
 */
@Entity
@Table(name = "movimientos_caja")
public class CashMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caja_id", nullable = false)
    private CashRegister caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false, length = 20)
    private CashMovementType tipoMovimiento;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDateTime fechaMovimiento;

    public CashMovement() {
    }

    public Long getId() {
        return id;
    }

    public CashRegister getCaja() {
        return caja;
    }

    public void setCaja(CashRegister caja) {
        this.caja = caja;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public CashMovementType getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(CashMovementType tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

}