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
    public static final String USERS_BY_ID = "/{id}";
    public static final String USERS_INACTIVE = "/inactivos";
    public static final String USERS_ACTIVATE = "/{id}/activate";
    public static final String USERS_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Productos
     * ========================== */
    public static final String PRODUCTS = API + "/products";
    public static final String PRODUCTS_BY_ID = "/{id}";
    public static final String PRODUCTS_INACTIVE = "/inactivos";
    public static final String PRODUCTS_ACTIVATE = "/{id}/activate";
    public static final String PRODUCTS_DEACTIVATE = "/{id}/deactivate";
    public static final String PRODUCTS_TYPES = "/product-types";

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
    public static final String PURCHASES_BY_ID = "/{id}";
    public static final String PURCHASES_BY_RECEIPT = "/receipt/{receipt}";
    public static final String PURCHASES_BY_SUPPLIER = "/supplier/{supplierId}";
    public static final String PURCHASES_BY_USER = "/user/{userId}";
    public static final String PURCHASES_TODAY = "/today";
    public static final String PURCHASES_MONTH = "/month";
    public static final String PURCHASES_BETWEEN = "/between";
    public static final String PURCHASES_CANCEL = "/{id}/cancel";

    /* ==========================
     * Ventas
     * ========================== */
    public static final String SALES = API + "/sales";
    public static final String SALES_BY_ID = "/{id}";
    public static final String SALES_BY_USER = "/user/{userId}";
    public static final String SALES_BY_PAYMENT_METHOD = "/payment-method/{paymentMethodId}";
    public static final String SALES_BETWEEN = "/between";
    public static final String SALES_TODAY = "/today";
    public static final String SALES_MONTH = "/month";
    public static final String SALES_CANCEL = "/{id}/cancel";

    /* ==========================
     * Inventario
     * ========================== */
    public static final String INVENTORY = API + "/inventory";
    public static final String INVENTORY_BY_PRODUCT = "/product/{productId}";
    public static final String INVENTORY_MOVEMENTS = "/product/{productId}/movements";
    public static final String INVENTORY_REGISTER_MOVEMENT = "/movements";

    // =========================================================
    // CAJA
    // =========================================================

    public static final String CASH_REGISTER = API + "/cash-register";
    public static final String CASH_REGISTER_BY_ID = "/{id}";
    public static final String CASH_REGISTER_OPEN = "/open";
    public static final String CASH_REGISTER_CLOSE = "/{id}/close";
    public static final String CASH_REGISTER_CURRENT = "/current";
    public static final String CASH_REGISTER_CLOSED = "/closed";
    /* ==========================
     * Historial de Precios
     * ========================== */
    public static final String PRICE_HISTORY = API + "/price-history";
    public static final String PRICE_HISTORY_BY_ID = "/{id}";
    public static final String PRICE_HISTORY_BY_PRODUCT = "/product/{productId}";
    public static final String PRICE_HISTORY_INACTIVE = "/inactivos";
    public static final String PRICE_HISTORY_ACTIVATE = "/{id}/activate";
    public static final String PRICE_HISTORY_DEACTIVATE = "/{id}/deactivate";

    /* ==========================
     * Permisos
     * ========================== */
    public static final String PERMISSIONS = API + "/permissions";
    public static final String PERMISSIONS_BY_ID = "/{id}";
    public static final String PERMISSIONS_INACTIVE = "/inactivos";
    public static final String PERMISSIONS_ACTIVATE = "/{id}/activate";
    public static final String PERMISSIONS_DEACTIVATE = "/{id}/deactivate";
    public static final String PERMISSIONS_BY_USER = "/user/{userId}";
    public static final String PERMISSIONS_CHECK = "/user/{userId}/check";

    /* ==========================
     * Roles y Permisos
     * ========================== */
    public static final String ROLE_PERMISSIONS = ROLES + "/{roleId}/permissions";
    public static final String ROLE_PERMISSIONS_ASSIGN = "/{roleId}/permissions";
    public static final String ROLE_PERMISSIONS_REMOVE = "/{roleId}/permissions/{permissionId}";

    /* ==========================
     * Movimientos
     * ========================== */

    public static final String CASH_MOVEMENTS = API + "/cash-movements";
    public static final String CASH_REGISTER_MOVEMENTS = "/movements";
    public static final String CASH_REGISTER_MOVEMENTS_BY_REGISTER = "/{cashRegisterId}/movements";
    public static final String CASH_MOVEMENTS_BY_CASH_REGISTER = "/cash-register/{cashRegisterId}";

    /* ==========================
     * DASHBOARD
     * ========================== */

    public static final String DASHBOARD = API + "/dashboard";

    /* ==========================
     * REPORTS
     * ========================== */

    public static final String REPORTS = API + "/reports";
    public static final String REPORTS_SALES = "/sales";

    /* ==========================
     * ESTADISTICAS
     * ========================== */

    public static final String STATISTICS = API + "/statistics";

    /* ==========================
     * RECIBOS
     * ========================== */

    public static final String RECEIPTS = API + "/receipt";
    public static final String RECEIPTS_PRINT = "/{id}";
}