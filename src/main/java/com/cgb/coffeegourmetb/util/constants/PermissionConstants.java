package com.cgb.coffeegourmetb.util.constants;

/**
 * Constantes que representan los permisos disponibles
 * dentro del sistema CoffeeGourmet.
 *
 * Los valores deben coincidir exactamente con los nombres
 * de permisos registrados en la base de datos.
 */
public final class PermissionConstants {

    private PermissionConstants() {
        // Evita la instanciación de esta clase.
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    public static final String USER_READ =
            "USER_READ";

    public static final String USER_CREATE =
            "USER_CREATE";

    public static final String USER_UPDATE =
            "USER_UPDATE";

    public static final String USER_ACTIVATE =
            "USER_ACTIVATE";

    public static final String USER_DEACTIVATE =
            "USER_DEACTIVATE";


    // =========================================================
    // ROLES
    // =========================================================

    public static final String ROLE_READ =
            "ROLE_READ";

    public static final String ROLE_CREATE =
            "ROLE_CREATE";

    public static final String ROLE_UPDATE =
            "ROLE_UPDATE";

    public static final String ROLE_ACTIVATE =
            "ROLE_ACTIVATE";

    public static final String ROLE_DEACTIVATE =
            "ROLE_DEACTIVATE";


    // =========================================================
    // PERMISOS
    // =========================================================

    public static final String PERMISSION_READ =
            "PERMISSION_READ";

    public static final String PERMISSION_CREATE =
            "PERMISSION_CREATE";

    public static final String PERMISSION_UPDATE =
            "PERMISSION_UPDATE";

    public static final String PERMISSION_ACTIVATE =
            "PERMISSION_ACTIVATE";

    public static final String PERMISSION_DEACTIVATE =
            "PERMISSION_DEACTIVATE";

    public static final String PERMISSION_OPERAR_CAJA =
            "OPERAR_CAJA";


    // =========================================================
    // PERMISOS DE ROLES
    // =========================================================

    public static final String ROLE_PERMISSION_READ =
            "ROLE_PERMISSION_READ";

    public static final String ROLE_PERMISSION_ASSIGN =
            "ROLE_PERMISSION_ASSIGN";

    public static final String ROLE_PERMISSION_REMOVE =
            "ROLE_PERMISSION_REMOVE";


    // =========================================================
    // CATEGORÍAS
    // =========================================================

    public static final String CATEGORY_READ =
            "CATEGORY_READ";

    public static final String CATEGORY_CREATE =
            "CATEGORY_CREATE";

    public static final String CATEGORY_UPDATE =
            "CATEGORY_UPDATE";

    public static final String CATEGORY_ACTIVATE =
            "CATEGORY_ACTIVATE";

    public static final String CATEGORY_DEACTIVATE =
            "CATEGORY_DEACTIVATE";


    // =========================================================
    // PRODUCTOS
    // =========================================================

    public static final String PRODUCTS_READ =
            "PRODUCTS_READ";

    public static final String PRODUCTS_CREATE =
            "PRODUCTS_CREATE";

    public static final String PRODUCTS_UPDATE =
            "PRODUCTS_UPDATE";

    public static final String PRODUCTS_ACTIVATE =
            "PRODUCTS_ACTIVATE";

    public static final String PRODUCTS_DEACTIVATE =
            "PRODUCTS_DEACTIVATE";


    // =========================================================
    // PROVEEDORES
    // =========================================================

    public static final String SUPPLIERS_READ =
            "SUPPLIERS_READ";

    public static final String SUPPLIERS_CREATE =
            "SUPPLIERS_CREATE";

    public static final String SUPPLIERS_UPDATE =
            "SUPPLIERS_UPDATE";

    public static final String SUPPLIERS_ACTIVATE =
            "SUPPLIERS_ACTIVATE";

    public static final String SUPPLIERS_DEACTIVATE =
            "SUPPLIERS_DEACTIVATE";


    // =========================================================
    // MÉTODOS DE PAGO
    // =========================================================

    public static final String PAYMENT_METHOD_READ =
            "PAYMENT_METHODS_READ";

    public static final String PAYMENT_METHOD_CREATE =
            "PAYMENT_METHODS_CREATE";

    public static final String PAYMENT_METHOD_UPDATE =
            "PAYMENT_METHODS_UPDATE";

    public static final String PAYMENT_METHOD_ACTIVATE =
            "PAYMENT_METHODS_ACTIVATE";

    public static final String PAYMENT_METHOD_DEACTIVATE =
            "PAYMENT_METHODS_DEACTIVATE";


    // =========================================================
    // HISTORIAL DE PRECIOS
    // =========================================================

    public static final String PRICE_HISTORY_READ =
            "PRICE_HISTORY_READ";

    public static final String PRICE_HISTORY_CREATE =
            "PRICE_HISTORY_CREATE";

    public static final String PRICE_HISTORY_UPDATE =
            "PRICE_HISTORY_UPDATE";

    public static final String PRICE_HISTORY_ACTIVATE =
            "PRICE_HISTORY_ACTIVATE";

    public static final String PRICE_HISTORY_DEACTIVATE =
            "PRICE_HISTORY_DEACTIVATE";


    // =========================================================
    // INVENTARIO
    // =========================================================

    public static final String INVENTORY_READ =
            "INVENTORY_READ";

    public static final String INVENTORY_MOVEMENT_CREATE =
            "INVENTORY_MOVEMENT_CREATE";

    // =========================================================
    // COMPRAS
    // =========================================================

    public static final String PURCHASES_READ =
            "PURCHASES_READ";

    public static final String PURCHASES_CREATE =
            "PURCHASES_CREATE";

    public static final String PURCHASES_CANCEL =
            "PURCHASES_CANCEL";

    // =========================================================
    // VENTAS
    // =========================================================

    public static final String SALE_READ =
            "SALES_READ";

    public static final String SALE_CREATE =
            "SALES_CREATE";

    public static final String SALE_CANCEL =
            "SALES_CANCEL";


    // =========================================================
    // CAJA
    // =========================================================

    public static final String CASH_REGISTER_READ =
            "CASH_REGISTER_READ";

    public static final String CASH_REGISTER_OPEN =
            "CASH_REGISTER_OPEN";

    public static final String CASH_REGISTER_CLOSE =
            "CASH_REGISTER_CLOSE";

    public static final String CASH_REGISTER_OPERATION =
            "CASH_REGISTER_OPERATION";
}