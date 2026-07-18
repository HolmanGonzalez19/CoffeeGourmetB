package com.cgb.coffeegourmetb.util.constants;

/**
 * Constantes con las rutas de la API REST.
 */
public final class ApiPaths {

    private ApiPaths() {
        throw new IllegalStateException("Utility class");
    }

    /* ==========================
     * Base API
     * ========================== */
    public static final String API = "/api";

    /* ==========================
     * Roles
     * ========================== */
    public static final String ROLES = API + "/roles";
    public static final String ROLES_BY_ID = "/{id}";
    public static final String ROLES_INACTIVE = "/inactivos";
    public static final String ROLES_ACTIVATE = "/{id}/activate";
    public static final String ROLES_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Usuarios
     * ========================== */
    public static final String USERS = API + "/users";

    /* ==========================
     * Productos
     * ========================== */
    public static final String PRODUCTS = API + "/products";

    /* ==========================
     * Categorías
     * ========================== */
    public static final String CATEGORIES = API + "/categories";
    public static final String CATEGORIES_BY_ID = "/{id}";
    public static final String CATEGORIES_INACTIVE = "/inactivos";
    public static final String CATEGORIES_ACTIVATE = "/{id}/activate";
    public static final String CATEGORIES_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Proveedores
     * ========================== */
    public static final String SUPPLIERS = API + "/suppliers";
    public static final String SUPPLIERS_BY_ID = "/{id}";
    public static final String SUPPLIERS_INACTIVE = "/inactivos";
    public static final String SUPPLIERS_ACTIVATE = "/{id}/activate";
    public static final String SUPPLIERS_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Métodos de Pago
     * ========================== */
    public static final String PAYMENT_METHODS = API + "/payment-methods";
    public static final String PAYMENT_METHODS_BY_ID = "/{id}";
    public static final String PAYMENT_METHODS_INACTIVE = "/inactivos";
    public static final String PAYMENT_METHODS_ACTIVATE = "/{id}/activate";
    public static final String PAYMENT_METHODS_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Compras
     * ========================== */
    public static final String PURCHASES = API + "/purchases";

    /* ==========================
     * Ventas
     * ========================== */
    public static final String SALES = API + "/sales";

    /* ==========================
     * Inventario
     * ========================== */
    public static final String INVENTORY = API + "/inventory";

    /* ==========================
     * Caja
     * ========================== */
    public static final String CASH_REGISTER = API + "/cash-register";

}