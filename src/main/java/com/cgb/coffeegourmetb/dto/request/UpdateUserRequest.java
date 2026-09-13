package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @NotNull(message = "El rol es obligatorio.")
    private Long rolId;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 150)
    private String nombre;

    @NotBlank(message = "El usuario es obligatorio.")
    @Size(max = 50)
    private String usuario;

    /**
     * Opcional.
     * Si viene informado se actualizará la contraseña.
     * Si viene null o vacío se conservará la actual.
     */
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres.")
    private String password;

    private String pin;

    @NotNull(message = "El estado es obligatorio.")
    private Boolean activo;

    public UpdateUserRequest() {
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

    public String getPin() { return pin; }

    public void setPin(String pin) { this.pin = pin; }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}