package com.cgb.coffeegourmetb.exception;

/**
 * Representa el detalle de un error de validación.
 */
public record ValidationErrorDetail(
        String field,
        String message
) {
}