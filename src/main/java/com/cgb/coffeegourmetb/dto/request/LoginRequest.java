package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "El usuario es obligatorio.")
    private String usuario;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;

    public LoginRequest() {
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