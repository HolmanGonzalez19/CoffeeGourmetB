package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateSupplierRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateSupplierRequest;
import com.cgb.coffeegourmetb.dto.response.SupplierResponse;
import com.cgb.coffeegourmetb.service.interfaces.SupplierService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.SUPPLIERS_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SUPPLIERS_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SUPPLIERS_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SUPPLIERS_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SUPPLIERS_DEACTIVATE;

@RestController
@RequestMapping(ApiPaths.SUPPLIERS)
@Tag(name = "Proveedores", description = "API para la gestión de proveedores.")
@SecurityRequirement(name = "bearerAuth")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @Operation(summary = "Listar proveedores activos")
    @PreAuthorize(SUPPLIERS_READ)
    @GetMapping
    public List<SupplierResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar todos los proveedores")
    @PreAuthorize(SUPPLIERS_READ)
    @GetMapping("/all")
    public List<SupplierResponse> findAllSuppliers() {
        return service.findAllSuppliers();
    }

    @Operation(summary = "Listar proveedores inactivos")
    @PreAuthorize(SUPPLIERS_READ)
    @GetMapping(ApiPaths.SUPPLIERS_INACTIVE)
    public List<SupplierResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar un proveedor por ID")
    @PreAuthorize(SUPPLIERS_READ)
    @GetMapping(ApiPaths.SUPPLIERS_BY_ID)
    public SupplierResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear un proveedor")
    @PreAuthorize(SUPPLIERS_CREATE)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proveedor creado correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierResponse create(@Valid @RequestBody CreateSupplierRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Actualizar un proveedor")
    @PreAuthorize(SUPPLIERS_UPDATE)
    @PutMapping(ApiPaths.SUPPLIERS_BY_ID)
    public SupplierResponse update(@PathVariable Long id,
                                   @Valid @RequestBody UpdateSupplierRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Activar un proveedor")
    @PreAuthorize(SUPPLIERS_ACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Proveedor activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PutMapping(ApiPaths.SUPPLIERS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    @Operation(summary = "Desactivar un proveedor")
    @PreAuthorize(SUPPLIERS_DEACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Proveedor desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PutMapping(ApiPaths.SUPPLIERS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}