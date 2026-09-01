package com.cgb.coffeegourmetb.dto.response;

import com.cgb.coffeegourmetb.enums.PurchaseStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseResponse {

    private Long id;

    /*
     * Código interno de la compra generado
     * automáticamente por el sistema.
     *
     * Ejemplo:
     * CMP-000001
     */
    private String codigoCompra;

    private Long proveedorId;

    private String proveedorNombre;

    private Long usuarioId;

    private String usuarioNombre;

    private LocalDateTime fecha;

    private BigDecimal total;

    private String observacion;

    private PurchaseStatus estado;

    private LocalDateTime fechaAnulacion;

    private Long usuarioAnulacionId;

    private String usuarioAnulacionNombre;

    private String motivoAnulacion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    private List<PurchaseDetailResponse> detalles;


    public PurchaseResponse() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }


    public Long getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Long proveedorId) {
        this.proveedorId = proveedorId;
    }


    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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


    public PurchaseStatus getEstado() {
        return estado;
    }

    public void setEstado(PurchaseStatus estado) {
        this.estado = estado;
    }


    public LocalDateTime getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(
            LocalDateTime fechaAnulacion) {

        this.fechaAnulacion = fechaAnulacion;
    }


    public Long getUsuarioAnulacionId() {
        return usuarioAnulacionId;
    }

    public void setUsuarioAnulacionId(
            Long usuarioAnulacionId) {

        this.usuarioAnulacionId = usuarioAnulacionId;
    }


    public String getUsuarioAnulacionNombre() {
        return usuarioAnulacionNombre;
    }

    public void setUsuarioAnulacionNombre(
            String usuarioAnulacionNombre) {

        this.usuarioAnulacionNombre =
                usuarioAnulacionNombre;
    }


    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(
            String motivoAnulacion) {

        this.motivoAnulacion = motivoAnulacion;
    }


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(
            LocalDateTime fechaCreacion) {

        this.fechaCreacion = fechaCreacion;
    }


    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(
            LocalDateTime fechaActualizacion) {

        this.fechaActualizacion =
                fechaActualizacion;
    }


    public List<PurchaseDetailResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(
            List<PurchaseDetailResponse> detalles) {

        this.detalles = detalles;
    }
}