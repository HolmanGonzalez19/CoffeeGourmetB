package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.response.PriceHistoryResponse;
import com.cgb.coffeegourmetb.service.interfaces.PriceHistoryService;
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

import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRICE_HISTORY_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRICE_HISTORY_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRICE_HISTORY_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRICE_HISTORY_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRICE_HISTORY_DEACTIVATE;

@RestController
@RequestMapping(ApiPaths.PRICE_HISTORY)
@Tag(name = "Historial de Precios", description = "API para la gestión del historial de precios.")
@SecurityRequirement(name = "bearerAuth")
public class PriceHistoryController {

    private final PriceHistoryService service;

    public PriceHistoryController(PriceHistoryService service) {
        this.service = service;
    }

    @Operation(summary = "Listar historiales de precios activos")
    @PreAuthorize(PRICE_HISTORY_READ)
    @GetMapping
    public List<PriceHistoryResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar historiales de precios inactivos")
    @PreAuthorize(PRICE_HISTORY_READ)
    @GetMapping(ApiPaths.PRICE_HISTORY_INACTIVE)
    public List<PriceHistoryResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar historial por ID")
    @PreAuthorize(PRICE_HISTORY_READ)
    @GetMapping(ApiPaths.PRICE_HISTORY_BY_ID)
    public PriceHistoryResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Consultar historial de un producto")
    @PreAuthorize(PRICE_HISTORY_READ)
    @GetMapping(ApiPaths.PRICE_HISTORY_BY_PRODUCT)
    public List<PriceHistoryResponse> findByProduct(@PathVariable Long productId) {
        return service.findByProduct(productId);
    }

    @Operation(summary = "Crear un nuevo registro de precios")
    @PreAuthorize(PRICE_HISTORY_CREATE)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Historial creado correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceHistoryResponse create(
            @Valid @RequestBody CreatePriceHistoryRequest request) {

        return service.create(request);
    }

    @Operation(summary = "Actualizar un historial de precios")
    @PreAuthorize(PRICE_HISTORY_UPDATE)
    @PutMapping(ApiPaths.PRICE_HISTORY_BY_ID)
    public PriceHistoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePriceHistoryRequest request) {

        return service.update(id, request);
    }

    @Operation(summary = "Activar un historial de precios")
    @PreAuthorize(PRICE_HISTORY_ACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Historial activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    @PutMapping(ApiPaths.PRICE_HISTORY_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    @Operation(summary = "Desactivar un historial de precios")
    @PreAuthorize(PRICE_HISTORY_DEACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Historial desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    @PutMapping(ApiPaths.PRICE_HISTORY_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}