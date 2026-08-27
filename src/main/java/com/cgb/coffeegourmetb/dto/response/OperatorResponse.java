package com.cgb.coffeegourmetb.dto.response;

public class OperatorResponse {

    private Long id;

    private String nombre;

    private String usuario;

    public OperatorResponse() {
    }

    public OperatorResponse(
            Long id,
            String nombre,
            String usuario) {

        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}