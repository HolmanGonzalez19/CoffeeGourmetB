package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateCashMovementRequest;
import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;
import com.cgb.coffeegourmetb.service.interfaces.CashMovementService;
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

import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_OPERAR_CAJA;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.CASH_REGISTER_READ;

@RestController
@RequestMapping("/api/cash-movements")
@Tag(
        name = "Movimientos de caja",
        description = "API para gestionar ingresos y retiros de efectivo."
)
@SecurityRequirement(name = "bearerAuth")
public class CashMovementController {

    private final CashMovementService service;

    public CashMovementController(
            CashMovementService service) {

        this.service = service;
    }

    @Operation(summary = "Registrar movimiento de caja")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Movimiento registrado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos o caja cerrada"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(PERMISSION_OPERAR_CAJA)
    public CashMovementResponse create(
            @Valid
            @RequestBody
            CreateCashMovementRequest request) {

        return service.create(request);
    }

    @Operation(summary = "Consultar movimientos de una caja")
    @GetMapping("/cash-register/{cajaId}")
    @PreAuthorize(CASH_REGISTER_READ)
    public List<CashMovementResponse> findByCaja(
            @PathVariable Long cajaId) {

        return service.findByCaja(cajaId);
    }
}