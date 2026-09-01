package com.cgb.coffeegourmetb.dto.response;

import com.cgb.coffeegourmetb.enums.SaleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SaleResponse {

    private Long id;

    private Long usuarioId;

    private String usuarioNombre;

    private Long metodoPagoId;

    private String metodoPagoNombre;

    private Long cajaId;

    private LocalDateTime fechaHora;

    private BigDecimal total;

    private String observacion;

    private SaleStatus estado;

    private LocalDateTime fechaAnulacion;

    private Long usuarioAnulacionId;

    private String usuarioAnulacionNombre;

    private String motivoAnulacion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private List<SaleDetailResponse> detalles;

    public SaleResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public String getMetodoPagoNombre() {
        return metodoPagoNombre;
    }

    public void setMetodoPagoNombre(String metodoPagoNombre) {
        this.metodoPagoNombre = metodoPagoNombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public SaleStatus getEstado() {
        return estado;
    }

    public void setEstado(SaleStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDateTime fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Long getUsuarioAnulacionId() {
        return usuarioAnulacionId;
    }

    public void setUsuarioAnulacionId(Long usuarioAnulacionId) {
        this.usuarioAnulacionId = usuarioAnulacionId;
    }

    public String getUsuarioAnulacionNombre() {
        return usuarioAnulacionNombre;
    }

    public void setUsuarioAnulacionNombre(String usuarioAnulacionNombre) {
        this.usuarioAnulacionNombre = usuarioAnulacionNombre;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<SaleDetailResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<SaleDetailResponse> detalles) {
        this.detalles = detalles;
    }

    public Long getCajaId() {
        return cajaId;
    }

    public void setCajaId(Long cajaId) {
        this.cajaId = cajaId;
    }
}