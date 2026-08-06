package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PinLoginRequest {

    @NotBlank(message = "El usuario es obligatorio.")
    private String usuario;

    @NotBlank(message = "El PIN es obligatorio.")
    private String pin;

    public PinLoginRequest() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}