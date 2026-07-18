package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en el módulo de Métodos de Pago.
 */
public final class PaymentMethodMessages {

    private PaymentMethodMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PAYMENT_METHOD_NOT_FOUND =
            "No existe un método de pago con id: ";

    public static final String ACTIVE_PAYMENT_METHOD_NOT_FOUND =
            "No existe un método de pago activo con id: ";

    public static final String PAYMENT_METHOD_ALREADY_EXISTS =
            "Ya existe un método de pago con el nombre: ";

    public static final String PAYMENT_METHOD_ALREADY_EXISTS_FOR_UPDATE =
            "Ya existe otro método de pago con el nombre: ";

}