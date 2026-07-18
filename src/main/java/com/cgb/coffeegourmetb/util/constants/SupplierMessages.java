package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en el módulo de proveedores.
 */
public final class SupplierMessages {

    private SupplierMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SUPPLIER_NOT_FOUND =
            "No existe un proveedor con id: ";

    public static final String SUPPLIER_ACTIVE_NOT_FOUND =
            "No existe un proveedor activo con id: ";

    public static final String SUPPLIER_ALREADY_EXISTS =
            "Ya existe un proveedor con el nombre: ";

    public static final String SUPPLIER_ALREADY_EXISTS_UPDATE =
            "Ya existe otro proveedor con el nombre: ";

}