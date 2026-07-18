package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateCategoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateCategoryRequest;
import com.cgb.coffeegourmetb.dto.response.CategoryResponse;
import com.cgb.coffeegourmetb.service.interfaces.CategoryService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@Tag(name = "Categorías", description = "API para la gestión de categorías.")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Listar categorías activas")
    @GetMapping
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar categorías inactivas")
    @GetMapping(ApiPaths.CATEGORIES_INACTIVE)
    public List<CategoryResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar una categoría por ID")
    @GetMapping(ApiPaths.CATEGORIES_BY_ID)
    public CategoryResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CreateCategoryRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Actualizar una categoría")
    @PutMapping(ApiPaths.CATEGORIES_BY_ID)
    public CategoryResponse update(@PathVariable Long id,
                                   @Valid @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Activar una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría activada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping(ApiPaths.CATEGORIES_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    @Operation(summary = "Desactivar una categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoría desactivada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    @PutMapping(ApiPaths.CATEGORIES_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}