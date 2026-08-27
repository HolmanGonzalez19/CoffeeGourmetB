package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CloseCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.request.OpenCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.response.CashRegisterResponse;
import com.cgb.coffeegourmetb.dto.response.CashRegisterStatusResponse;
import com.cgb.coffeegourmetb.service.interfaces.CashRegisterService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import com.cgb.coffeegourmetb.security.SecurityExpressions;
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

@RestController
@RequestMapping(ApiPaths.CASH_REGISTER)
@Tag(
        name = "Caja",
        description = "API para la gestión de cajas."
)
@SecurityRequirement(name = "bearerAuth")
public class CashRegisterController {

    private final CashRegisterService service;

    public CashRegisterController(
            CashRegisterService service) {

        this.service = service;
    }

    @Operation(summary = "Abrir una caja")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Caja abierta correctamente")
    })
    @PostMapping(ApiPaths.CASH_REGISTER_OPEN)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(SecurityExpressions.PERMISSION_OPERAR_CAJA)
    public CashRegisterResponse open(
            @Valid
            @RequestBody
            OpenCashRegisterRequest request) {

        return service.open(request);
    }

    @Operation(summary = "Cerrar una caja")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caja cerrada correctamente")
    })
    @PatchMapping(ApiPaths.CASH_REGISTER_CLOSE)
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_CLOSE)
    public CashRegisterResponse close(
            @PathVariable Long id,
            @Valid
            @RequestBody
            CloseCashRegisterRequest request) {

        return service.close(
                id,
                request);
    }


    @Operation(
            summary = "Consultar estado de la caja actual",
            description = "Consulta pública que devuelve únicamente si existe una caja abierta."
    )
    @GetMapping(ApiPaths.CASH_REGISTER_CURRENT)
    public CashRegisterStatusResponse findOpen() {

        return service.findOpen();
    }

    @Operation(
            summary = "Consultar caja abierta",
            description = "Consulta administrativa de la caja abierta con información completa."
    )
    @GetMapping("/open")
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_READ)
    public CashRegisterResponse findOpenAdministrative() {

        return service.findOpenAdministrative();
    }

    @Operation(summary = "Consultar caja por id")
    @GetMapping(ApiPaths.CASH_REGISTER_BY_ID)
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_READ)
    public CashRegisterResponse findById(
            @PathVariable Long id) {

        return service.findById(id);
    }

    @Operation(summary = "Consultar todas las cajas")
    @GetMapping
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_READ)
    public List<CashRegisterResponse> findAll() {

        return service.findAll();
    }

    @Operation(summary = "Consultar cajas cerradas")
    @GetMapping(ApiPaths.CASH_REGISTER_CLOSED)
    @PreAuthorize(SecurityExpressions.CASH_REGISTER_READ)
    public List<CashRegisterResponse> findClosed() {

        return service.findClosed();
    }



}