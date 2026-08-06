package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateCashMovementRequest;
import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;
import com.cgb.coffeegourmetb.service.interfaces.CashMovementService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import com.cgb.coffeegourmetb.security.SecurityExpressions;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.CASH_MOVEMENTS)
@Tag(
        name = "Movimientos de Caja",
        description = "API para la gestión de movimientos de efectivo."
)
@SecurityRequirement(name = "bearerAuth")
public class CashMovementController {

    private final CashMovementService service;

    public CashMovementController(
            CashMovementService service) {

        this.service = service;
    }

    @Operation(summary = "Registrar movimiento de caja")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_OPERATION)
    public CashMovementResponse create(
            @Valid
            @RequestBody
            CreateCashMovementRequest request) {

        return service.create(request);
    }

    @Operation(summary = "Consultar movimientos de una caja")
    @GetMapping(ApiPaths.CASH_MOVEMENTS_BY_CASH_REGISTER)
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_READ)
    public List<CashMovementResponse> findByCashRegister(
            @PathVariable Long cashRegisterId) {

        return service.findByCashRegister(
                cashRegisterId);
    }

}