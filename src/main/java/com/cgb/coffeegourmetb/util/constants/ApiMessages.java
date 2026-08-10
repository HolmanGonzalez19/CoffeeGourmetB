package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en toda la aplicación.
 */
public final class ApiMessages {

    private ApiMessages() {
        throw new IllegalStateException("Utility class");
    }

    // ==========================
    // Mensajes generales
    // ==========================

    public static final String RESOURCE_NOT_FOUND = "Recurso no encontrado.";
    public static final String INTERNAL_SERVER_ERROR = "Ha ocurrido un error interno en el servidor.";
    public static final String VALIDATION_ERROR = "Error de validación.";

    // =========================================================
    // CAJA
    // =========================================================

    public static final String CASH_REGISTER_NOT_FOUND = "Caja no encontrada.";
    public static final String CASH_REGISTER_ALREADY_OPEN = "Ya existe una caja abierta.";
    public static final String CASH_REGISTER_ALREADY_CLOSED = "La caja ya fue cerrada.";
    public static final String CASH_REGISTER_NOT_OPEN = "No existe una caja abierta.";
    public static final String CASH_REGISTER_OPEN = "Caja abierta correctamente.";
    public static final String CASH_REGISTER_CLOSED_SUCCESS = "Caja cerrada correctamente.";

    // =========================================================
    // MOVIMIENTO
    // =========================================================

    public static final String CASH_MOVEMENT_NOT_FOUND = "Movimiento de caja no encontrado.";
}