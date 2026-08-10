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

    public static final String USER_READ =
            "hasAuthority('" + PermissionConstants.USER_READ + "')";

    public static final String USER_CREATE =
            "hasAuthority('" + PermissionConstants.USER_CREATE + "')";

    public static final String USER_UPDATE =
            "hasAuthority('" + PermissionConstants.USER_UPDATE + "')";

    public static final String USER_ACTIVATE =
            "hasAuthority('" + PermissionConstants.USER_ACTIVATE + "')";

    public static final String USER_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.USER_DEACTIVATE + "')";


    // =========================================================
    // ROLES
    // =========================================================

    public static final String ROLE_READ =
            "hasAuthority('" + PermissionConstants.ROLE_READ + "')";

    public static final String ROLE_CREATE =
            "hasAuthority('" + PermissionConstants.ROLE_CREATE + "')";

    public static final String ROLE_UPDATE =
            "hasAuthority('" + PermissionConstants.ROLE_UPDATE + "')";

    public static final String ROLE_ACTIVATE =
            "hasAuthority('" + PermissionConstants.ROLE_ACTIVATE + "')";

    public static final String ROLE_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.ROLE_DEACTIVATE + "')";


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

    public static final String CATEGORY_READ =
            "hasAuthority('" + PermissionConstants.CATEGORY_READ + "')";

    public static final String CATEGORY_CREATE =
            "hasAuthority('" + PermissionConstants.CATEGORY_CREATE + "')";

    public static final String CATEGORY_UPDATE =
            "hasAuthority('" + PermissionConstants.CATEGORY_UPDATE + "')";

    public static final String CATEGORY_ACTIVATE =
            "hasAuthority('" + PermissionConstants.CATEGORY_ACTIVATE + "')";

    public static final String CATEGORY_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.CATEGORY_DEACTIVATE + "')";


    // =========================================================
    // PRODUCTOS
    // =========================================================

    public static final String PRODUCT_READ =
            "hasAuthority('" + PermissionConstants.PRODUCT_READ + "')";

    public static final String PRODUCT_CREATE =
            "hasAuthority('" + PermissionConstants.PRODUCT_CREATE + "')";

    public static final String PRODUCT_UPDATE =
            "hasAuthority('" + PermissionConstants.PRODUCT_UPDATE + "')";

    public static final String PRODUCT_ACTIVATE =
            "hasAuthority('" + PermissionConstants.PRODUCT_ACTIVATE + "')";

    public static final String PRODUCT_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.PRODUCT_DEACTIVATE + "')";


    // =========================================================
    // PROVEEDORES
    // =========================================================

    public static final String SUPPLIER_READ =
            "hasAuthority('" + PermissionConstants.SUPPLIER_READ + "')";

    public static final String SUPPLIER_CREATE =
            "hasAuthority('" + PermissionConstants.SUPPLIER_CREATE + "')";

    public static final String SUPPLIER_UPDATE =
            "hasAuthority('" + PermissionConstants.SUPPLIER_UPDATE + "')";

    public static final String SUPPLIER_ACTIVATE =
            "hasAuthority('" + PermissionConstants.SUPPLIER_ACTIVATE + "')";

    public static final String SUPPLIER_DEACTIVATE =
            "hasAuthority('" + PermissionConstants.SUPPLIER_DEACTIVATE + "')";


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

    public static final String PURCHASE_READ =
            "hasAuthority('" + PermissionConstants.PURCHASE_READ + "')";

    public static final String PURCHASE_CREATE =
            "hasAuthority('" + PermissionConstants.PURCHASE_CREATE + "')";

    public static final String PURCHASE_CANCEL =
            "hasAuthority('" + PermissionConstants.PURCHASE_CANCEL + "')";


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