package com.cgb.coffeegourmetb.entity;

import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa una sesión o jornada de operación de caja.
 *
 * Una caja registra la apertura, el monto inicial y,
 * posteriormente, la información correspondiente al cierre.
 */
@Entity
@Table(
        name = "cajas",
        schema = "coffeegourmet"
)
public class CashRegister {

    /**
     * Identificador único de la caja.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario responsable de realizar la apertura de la caja.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "usuario_apertura_id",
            nullable = false
    )
    private User usuarioApertura;

    /**
     * Fecha y hora en la que se realizó la apertura.
     */
    @Column(
            name = "fecha_apertura",
            nullable = false
    )
    private LocalDateTime fechaApertura;

    /**
     * Monto inicial de efectivo disponible en la caja.
     */
    @Column(
            name = "monto_inicial",
            nullable = false,
            precision = 15,
            scale = 2
    )
    private BigDecimal montoInicial;

    /**
     * Estado actual de la caja.
     */
    @Enumerated(EnumType.STRING)
    @Column(
            name = "estado",
            nullable = false,
            length = 20
    )
    private CashRegisterStatus estado;

    /**
     * Usuario responsable de realizar el cierre.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "usuario_cierre_id"
    )
    private User usuarioCierre;

    /**
     * Fecha y hora en la que se realizó el cierre.
     */
    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    /**
     * Efectivo que el sistema esperaba encontrar al cerrar la caja.
     */
    @Column(
            name = "efectivo_esperado",
            precision = 15,
            scale = 2
    )
    private BigDecimal efectivoEsperado;

    /**
     * Efectivo contado físicamente durante el cierre.
     */
    @Column(
            name = "efectivo_contado",
            precision = 15,
            scale = 2
    )
    private BigDecimal efectivoContado;

    /**
     * Diferencia entre el efectivo contado y el efectivo esperado.
     *
     * Un valor positivo representa un sobrante.
     * Un valor negativo representa un faltante.
     */
    @Column(
            name = "diferencia",
            precision = 15,
            scale = 2
    )
    private BigDecimal diferencia;

    public CashRegister() {
    }

    public Long getId() {
        return id;
    }

    public User getUsuarioApertura() {
        return usuarioApertura;
    }

    public void setUsuarioApertura(User usuarioApertura) {
        this.usuarioApertura = usuarioApertura;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public CashRegisterStatus getEstado() {
        return estado;
    }

    public void setEstado(CashRegisterStatus estado) {
        this.estado = estado;
    }

    public User getUsuarioCierre() {
        return usuarioCierre;
    }

    public void setUsuarioCierre(User usuarioCierre) {
        this.usuarioCierre = usuarioCierre;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public BigDecimal getEfectivoEsperado() {
        return efectivoEsperado;
    }

    public void setEfectivoEsperado(BigDecimal efectivoEsperado) {
        this.efectivoEsperado = efectivoEsperado;
    }

    public BigDecimal getEfectivoContado() {
        return efectivoContado;
    }

    public void setEfectivoContado(BigDecimal efectivoContado) {
        this.efectivoContado = efectivoContado;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }
}