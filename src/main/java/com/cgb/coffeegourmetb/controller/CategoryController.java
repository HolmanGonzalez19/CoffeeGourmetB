package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateCategoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateCategoryRequest;
import com.cgb.coffeegourmetb.dto.response.CategoryResponse;
import com.cgb.coffeegourmetb.service.interfaces.CategoryService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.CATEGORY_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.CATEGORY_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.CATEGORY_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.CATEGORY_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.CATEGORY_DEACTIVATE;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@Tag(name = "Categorías", description = "API para la gestión de categorías.")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Listar categorías activas")
    @PreAuthorize(CATEGORY_READ)
    @GetMapping
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar categorías inactivas")
    @PreAuthorize(CATEGORY_READ)
    @GetMapping(ApiPaths.CATEGORIES_INACTIVE)
    public List<CategoryResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar una categoría por ID")
    @PreAuthorize(CATEGORY_READ)
    @GetMapping(ApiPaths.CATEGORIES_BY_ID)
    public CategoryResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear una categoría")
    @PreAuthorize(CATEGORY_CREATE)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse create(@Valid @RequestBody CreateCategoryRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Actualizar una categoría")
    @PreAuthorize(CATEGORY_UPDATE)
    @PutMapping(ApiPaths.CATEGORIES_BY_ID)
    public CategoryResponse update(@PathVariable Long id,
                                   @Valid @RequestBody UpdateCategoryRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Activar una categoría")
    @PreAuthorize(CATEGORY_ACTIVATE)
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
    @PreAuthorize(CATEGORY_DEACTIVATE)
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