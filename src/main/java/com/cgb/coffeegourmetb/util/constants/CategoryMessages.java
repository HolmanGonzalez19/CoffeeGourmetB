package com.cgb.coffeegourmetb.util.constants;

/**
 * Mensajes utilizados en el módulo de categorías.
 */
public final class CategoryMessages {

    private CategoryMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CATEGORY_NOT_FOUND =
            "No existe una categoría con id: ";

    public static final String CATEGORY_ACTIVE_NOT_FOUND =
            "No existe una categoría activa con id: ";

    public static final String CATEGORY_ALREADY_EXISTS =
            "Ya existe una categoría con el nombre: ";

    public static final String CATEGORY_ALREADY_EXISTS_UPDATE =
            "Ya existe otra categoría con el nombre: ";

}