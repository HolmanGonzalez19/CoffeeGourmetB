package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en el módulo de Permisos.
 */
public final class PermissionMessages {

    private PermissionMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PERMISSION_NOT_FOUND =
            "No existe un permiso con id: ";

    public static final String ACTIVE_PERMISSION_NOT_FOUND =
            "No existe un permiso activo con id: ";

    public static final String PERMISSION_ALREADY_EXISTS =
            "Ya existe un permiso con el nombre: ";

    public static final String PERMISSION_ALREADY_EXISTS_FOR_UPDATE =
            "Ya existe otro permiso con el nombre: ";

    public static final String USER_NOT_FOUND_OR_INACTIVE =
            "El usuario no existe o se encuentra inactivo.";
}