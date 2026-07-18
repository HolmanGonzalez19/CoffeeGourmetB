package com.cgb.coffeegourmetb.exception;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa la estructura estándar de error de la API.
 */
public record ApiError(

        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<ValidationErrorDetail> details

) {
}