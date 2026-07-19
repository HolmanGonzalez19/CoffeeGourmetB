package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateInventoryMovementRequest;
import com.cgb.coffeegourmetb.dto.response.InventoryMovementResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryResponse;
import com.cgb.coffeegourmetb.service.interfaces.InventoryService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.INVENTORY)
@Tag(
        name = "Inventario",
        description = "API para la gestión del inventario."
)
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @Operation(summary = "Consultar todo el inventario")
    @GetMapping
    public List<InventoryResponse> findAll() {

        return service.findAll();

    }

    @Operation(summary = "Consultar inventario por producto")
    @GetMapping(ApiPaths.INVENTORY_BY_PRODUCT)
    public InventoryResponse findByProduct(
            @PathVariable Long productId){

        return service.findByProduct(productId);

    }

    @Operation(summary = "Consultar movimientos de un producto")
    @GetMapping(ApiPaths.INVENTORY_MOVEMENTS)
    public List<InventoryMovementResponse> movements(
            @PathVariable Long productId){

        return service.movements(productId);

    }

    @Operation(summary = "Registrar movimiento de inventario")
    @PostMapping(ApiPaths.INVENTORY_REGISTER_MOVEMENT)
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryMovementResponse createMovement(

            @Valid
            @RequestBody
            CreateInventoryMovementRequest request){

        return service.createMovement(request);

    }

}