package com.cgb.coffeegourmetb.security;

import com.cgb.coffeegourmetb.util.constants.PermissionConstants;

/**
 * Expresiones de seguridad reutilizables para autorización
 * mediante permisos en los endpoints de la aplicación.
 *
 * Estas expresiones permiten evitar el uso directo de strings
 * con nombres de permisos dentro de los controladores.
 */
public final class SecurityExpressions {

    private SecurityExpressions() {
        // Evita la instanciación de esta clase.
    }

    // =========================================================
    // USUARIOS
    // =========================================================

    public static final String USERS_READ =
            "hasAuthority('" + PermissionConstants.USERS_READ + "')";

    public static final String USERS_CREATE =
            "hasAuthority('" + PermissionConstants.USERS_CREATE + "')";

    public static final String USERS_UPDATE =
            "hasAuthority('" + PermissionConstants.USERS_UPDATE + "')";

    public static final String USERS_ACTIVATE =
            "hasAuthority('" + PermissionConstants.USERS_ACTIVATE + "')";

    public static final String USERS_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.USERS_DEACTIVATE + "')";


    // =========================================================
    // ROLES
    // =========================================================

    public static final String ROLES_READ =
            "hasAuthority('" + PermissionConstants.ROLES_READ + "')";

    public static final String ROLES_CREATE =
            "hasAuthority('" + PermissionConstants.ROLES_CREATE + "')";

    public static final String ROLES_UPDATE =
            "hasAuthority('" + PermissionConstants.ROLES_UPDATE + "')";

    public static final String ROLES_ACTIVATE =
            "hasAuthority('" + PermissionConstants.ROLES_ACTIVATE + "')";

    public static final String ROLES_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.ROLES_DEACTIVATE + "')";


    // =========================================================
    // PERMISOS
    // =========================================================

    public static final String PERMISSION_READ =
            "hasAuthority('" + PermissionConstants.PERMISSION_READ + "')";

    public static final String PERMISSION_CREATE =
            "hasAuthority('" + PermissionConstants.PERMISSION_CREATE + "')";

    public static final String PERMISSION_UPDATE =
            "hasAuthority('" + PermissionConstants.PERMISSION_UPDATE + "')";

    public static final String PERMISSION_ACTIVATE =
            "hasAuthority('" + PermissionConstants.PERMISSION_ACTIVATE + "')";

    public static final String PERMISSION_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.PERMISSION_DEACTIVATE + "')";

    public static final String PERMISSION_OPERAR_CAJA =
            "hasAuthority('" + PermissionConstants.PERMISSION_OPERAR_CAJA + "')";

    // =========================================================
    // PERMISOS DE ROLES
    // =========================================================

    public static final String ROLE_PERMISSION_READ =
            "hasAuthority('" + PermissionConstants.ROLE_PERMISSION_READ + "')";

    public static final String ROLE_PERMISSION_ASSIGN =
            "hasAuthority('" + PermissionConstants.ROLE_PERMISSION_ASSIGN + "')";

    public static final String ROLE_PERMISSION_REMOVE =
            "hasAuthority('" + PermissionConstants.ROLE_PERMISSION_REMOVE + "')";


    // =========================================================
    // CATEGORÍAS
    // =========================================================

    public static final String CATEGORIES_READ =
            "hasAuthority('" + PermissionConstants.CATEGORIES_READ + "')";

    public static final String CATEGORIES_CREATE =
            "hasAuthority('" + PermissionConstants.CATEGORIES_CREATE + "')";

    public static final String CATEGORIES_UPDATE =
            "hasAuthority('" + PermissionConstants.CATEGORIES_UPDATE + "')";

    public static final String CATEGORIES_ACTIVATE =
            "hasAuthority('" + PermissionConstants.CATEGORIES_ACTIVATE + "')";

    public static final String CATEGORIES_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.CATEGORIES_DEACTIVATE + "')";


    // =========================================================
    // PRODUCTOS
    // =========================================================

    public static final String PRODUCTS_READ =
            "hasAuthority('" + PermissionConstants.PRODUCTS_READ + "')";

    public static final String PRODUCTS_CREATE =
            "hasAuthority('" + PermissionConstants.PRODUCTS_CREATE + "')";

    public static final String PRODUCTS_UPDATE =
            "hasAuthority('" + PermissionConstants.PRODUCTS_UPDATE + "')";

    public static final String PRODUCTS_ACTIVATE =
            "hasAuthority('" + PermissionConstants.PRODUCTS_ACTIVATE + "')";

    public static final String PRODUCTS_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.PRODUCTS_DEACTIVATE + "')";


    // =========================================================
    // PROVEEDORES
    // =========================================================

    public static final String SUPPLIERS_READ =
            "hasAuthority('" + PermissionConstants.SUPPLIERS_READ + "')";

    public static final String SUPPLIERS_CREATE =
            "hasAuthority('" + PermissionConstants.SUPPLIERS_CREATE + "')";

    public static final String SUPPLIERS_UPDATE =
            "hasAuthority('" + PermissionConstants.SUPPLIERS_UPDATE + "')";

    public static final String SUPPLIERS_ACTIVATE =
            "hasAuthority('" + PermissionConstants.SUPPLIERS_ACTIVATE + "')";

    public static final String SUPPLIERS_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.SUPPLIERS_DEACTIVATE + "')";


    // =========================================================
    // MÉTODOS DE PAGO
    // =========================================================

    public static final String PAYMENT_METHOD_READ =
            "hasAuthority('" + PermissionConstants.PAYMENT_METHOD_READ + "')";

    public static final String PAYMENT_METHOD_CREATE =
            "hasAuthority('" + PermissionConstants.PAYMENT_METHOD_CREATE + "')";

    public static final String PAYMENT_METHOD_UPDATE =
            "hasAuthority('" + PermissionConstants.PAYMENT_METHOD_UPDATE + "')";

    public static final String PAYMENT_METHOD_ACTIVATE =
            "hasAuthority('" + PermissionConstants.PAYMENT_METHOD_ACTIVATE + "')";

    public static final String PAYMENT_METHOD_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.PAYMENT_METHOD_DEACTIVATE + "')";


    // =========================================================
    // HISTORIAL DE PRECIOS
    // =========================================================

    public static final String PRICE_HISTORY_READ =
            "hasAuthority('" + PermissionConstants.PRICE_HISTORY_READ + "')";

    public static final String PRICE_HISTORY_CREATE =
            "hasAuthority('" + PermissionConstants.PRICE_HISTORY_CREATE + "')";

    public static final String PRICE_HISTORY_UPDATE =
            "hasAuthority('" + PermissionConstants.PRICE_HISTORY_UPDATE + "')";

    public static final String PRICE_HISTORY_ACTIVATE =
            "hasAuthority('" + PermissionConstants.PRICE_HISTORY_ACTIVATE + "')";

    public static final String PRICE_HISTORY_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.PRICE_HISTORY_DEACTIVATE + "')";


    // =========================================================
    // INVENTARIO
    // =========================================================

    public static final String INVENTORY_READ =
            "hasAuthority('" + PermissionConstants.INVENTORY_READ + "')";

    public static final String INVENTORY_MOVEMENT_CREATE =
            "hasAuthority('" + PermissionConstants.INVENTORY_MOVEMENT_CREATE + "')";


    // =========================================================
    // COMPRAS
    // =========================================================

    public static final String PURCHASES_READ =
            "hasAuthority('" + PermissionConstants.PURCHASES_READ + "')";

    public static final String PURCHASES_CREATE =
            "hasAuthority('" + PermissionConstants.PURCHASES_CREATE + "')";

    public static final String PURCHASES_CANCEL =
            "hasAuthority('" + PermissionConstants.PURCHASES_CANCEL + "')";


    // =========================================================
    // VENTAS
    // =========================================================

    public static final String SALE_READ =
            "hasAuthority('" + PermissionConstants.SALE_READ + "')";

    public static final String SALE_CREATE =
            "hasAuthority('" + PermissionConstants.SALE_CREATE + "')";

    public static final String SALE_CANCEL =
            "hasAuthority('" + PermissionConstants.SALE_CANCEL + "')";


    // =========================================================
    // CAJA
    // =========================================================

    public static final String CASH_REGISTER_READ =
            "hasAuthority('" + PermissionConstants.CASH_REGISTER_READ + "')";

    public static final String CASH_REGISTER_OPEN =
            "hasAuthority('" + PermissionConstants.CASH_REGISTER_OPEN + "')";

    public static final String CASH_REGISTER_CLOSE =
            "hasAuthority('" + PermissionConstants.CASH_REGISTER_CLOSE + "')";

    public static final String CASH_REGISTER_OPERATION =
            "hasAuthority('" + PermissionConstants.CASH_REGISTER_OPERATION + "')";

    // =========================================================
    // DASHBOARD
    // =========================================================

    public static final String DASHBOARD_READ =
            "hasAuthority('DASHBOARD_READ')";

    // =========================================================
    // REPORTS
    // =========================================================

    public static final String REPORTS_READ =
            "hasAuthority('REPORTS_READ')";

    // =========================================================
    // ESTADISTICAS
    // =========================================================

    public static final String STATISTICS_READ =
            "hasAuthority('STATISTICS_READ')";

}