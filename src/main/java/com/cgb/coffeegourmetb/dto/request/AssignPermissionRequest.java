package com.cgb.coffeegourmetb.dto.request;

import jakarta.validation.constraints.NotNull;

public class AssignPermissionRequest {

    @NotNull(message = "El identificador del permiso es obligatorio.")
    private Long permisoId;

    public AssignPermissionRequest() {
    }

    public Long getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(Long permisoId) {
        this.permisoId = permisoId;
    }
}