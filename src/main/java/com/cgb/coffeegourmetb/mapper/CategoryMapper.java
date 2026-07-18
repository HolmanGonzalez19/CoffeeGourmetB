package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreateCategoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateCategoryRequest;
import com.cgb.coffeegourmetb.dto.response.CategoryResponse;
import com.cgb.coffeegourmetb.entity.Category;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir objetos relacionados con Category.
 */
@Component
public class CategoryMapper {

    public Category toEntity(CreateCategoryRequest request) {

        Category category = new Category();
        category.setNombre(request.getNombre());
        category.setDescripcion(request.getDescripcion());
        category.setActivo(true);

        return category;
    }

    public CategoryResponse toResponse(Category category) {

        CategoryResponse response = new CategoryResponse();

        response.setId(category.getId());
        response.setNombre(category.getNombre());
        response.setDescripcion(category.getDescripcion());
        response.setActivo(category.getActivo());
        response.setFechaCreacion(category.getFechaCreacion());
        response.setFechaActualizacion(category.getFechaActualizacion());

        return response;
    }

    public void updateEntity(UpdateCategoryRequest request,
                             Category category) {

        category.setNombre(request.getNombre());
        category.setDescripcion(request.getDescripcion());
        category.setActivo(request.getActivo());
    }

}