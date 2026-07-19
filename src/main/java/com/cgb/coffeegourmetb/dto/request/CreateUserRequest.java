package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {

    @NotNull(message = "El rol es obligatorio.")
    private Long rolId;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 150)
    private String nombre;

    @NotBlank(message = "El usuario es obligatorio.")
    @Size(max = 50)
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 255)
    private String password;

    public CreateUserRequest() {
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}