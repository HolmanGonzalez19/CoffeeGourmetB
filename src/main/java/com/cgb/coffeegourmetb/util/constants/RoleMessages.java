package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en el módulo de Roles.
 */
public final class RoleMessages {

    private RoleMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ROLE_NOT_FOUND =
            "No existe un rol con id: ";

    public static final String ACTIVE_ROLE_NOT_FOUND =
            "No existe un rol activo con id: ";

    public static final String ROLE_ALREADY_EXISTS =
            "Ya existe un rol con el nombre: ";

    public static final String ROLE_ALREADY_EXISTS_FOR_UPDATE =
            "Ya existe otro rol con el nombre: ";

}