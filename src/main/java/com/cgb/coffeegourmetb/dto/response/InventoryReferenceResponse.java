package com.cgb.coffeegourmetb.dto.response;

public class InventoryReferenceResponse {

    private String tipo;
    private Long id;
    private String referencia;
    private String descripcion;

    public InventoryReferenceResponse() {
    }

    public InventoryReferenceResponse(
            String tipo,
            Long id,
            String referencia,
            String descripcion) {

        this.tipo = tipo;
        this.id = id;
        this.referencia = referencia;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}