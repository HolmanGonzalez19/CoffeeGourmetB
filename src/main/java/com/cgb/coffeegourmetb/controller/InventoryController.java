package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateInventoryMovementRequest;
import com.cgb.coffeegourmetb.dto.response.InventoryMovementResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryReferenceResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryResponse;
import com.cgb.coffeegourmetb.service.interfaces.InventoryService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.INVENTORY_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.INVENTORY_MOVEMENT_CREATE;

@RestController
@RequestMapping(ApiPaths.INVENTORY)
@Tag(
        name = "Inventario",
        description = "API para la gestión del inventario."
)
@SecurityRequirement(name = "bearerAuth")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @Operation(summary = "Consultar todo el inventario")
    @PreAuthorize(INVENTORY_READ)
    @GetMapping
    public List<InventoryResponse> findAll() {

        return service.findAll();

    }

    @Operation(summary = "Consultar inventario por producto")
    @PreAuthorize(INVENTORY_READ)
    @GetMapping(ApiPaths.INVENTORY_BY_PRODUCT)
    public InventoryResponse findByProduct(
            @PathVariable Long productId){

        return service.findByProduct(productId);

    }

    @Operation(summary = "Consultar movimientos de un producto")
    @PreAuthorize(INVENTORY_READ)
    @GetMapping(ApiPaths.INVENTORY_MOVEMENTS)
    public List<InventoryMovementResponse> movements(
            @PathVariable Long productId){

        return service.movements(productId);

    }

    @Operation(summary = "Registrar movimiento de inventario")
    @PreAuthorize(INVENTORY_MOVEMENT_CREATE)
    @PostMapping(ApiPaths.INVENTORY_REGISTER_MOVEMENT)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryMovementResponse createMovement(

            @Valid
            @RequestBody
            CreateInventoryMovementRequest request){

        return service.createMovement(request);

    }

    @Operation(summary = "Consultar referencias disponibles para movimientos")
    @PreAuthorize(INVENTORY_READ)
    @GetMapping("/references")
    public List<InventoryReferenceResponse> references() {

        return service.references();

    }

}