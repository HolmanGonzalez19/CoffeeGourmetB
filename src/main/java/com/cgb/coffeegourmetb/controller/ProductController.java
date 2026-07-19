package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.service.interfaces.ProductService;
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
@RequestMapping(ApiPaths.PRODUCTS)
@Tag(name = "Productos", description = "API para la gestión de productos.")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @Operation(summary = "Listar productos activos")
    @GetMapping
    public List<ProductResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar productos inactivos")
    @GetMapping(ApiPaths.PRODUCTS_INACTIVE)
    public List<ProductResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar un producto por ID")
    @GetMapping(ApiPaths.PRODUCTS_BY_ID)
    public ProductResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(
            @Valid @RequestBody CreateProductRequest request) {

        return service.create(request);
    }

    @Operation(summary = "Actualizar un producto")
    @PutMapping(ApiPaths.PRODUCTS_BY_ID)
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        return service.update(id, request);
    }

    @Operation(summary = "Activar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping(ApiPaths.PRODUCTS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    @Operation(summary = "Desactivar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping(ApiPaths.PRODUCTS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}