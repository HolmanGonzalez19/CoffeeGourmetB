package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CancelSaleRequest;
import com.cgb.coffeegourmetb.dto.request.CreateSaleRequest;
import com.cgb.coffeegourmetb.dto.response.SaleResponse;
import com.cgb.coffeegourmetb.service.interfaces.SaleService;
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
import com.cgb.coffeegourmetb.dto.request.SaleFilterRequest;
import com.cgb.coffeegourmetb.dto.response.PagedResponse;

import java.time.LocalDateTime;
import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.SALE_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SALE_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.SALE_CANCEL;

@RestController
@RequestMapping(ApiPaths.SALES)
@Tag(
        name = "Ventas",
        description = "API para la gestión de ventas realizadas a clientes."
)
@SecurityRequirement(name = "bearerAuth")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @Operation(summary = "Listar todas las ventas")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas consultadas correctamente"
            )
    })
    @GetMapping
    public List<SaleResponse> findAll() {

        return saleService.findAll();

    }

    @Operation(summary = "Consultar una venta por ID")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Venta encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Venta no encontrada"
            )
    })
    @GetMapping(ApiPaths.SALES_BY_ID)
    public SaleResponse findById(
            @PathVariable Long id) {

        return saleService.findById(id);

    }

    @Operation(summary = "Consultar ventas registradas por un usuario")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas consultadas correctamente"
            )
    })
    @GetMapping(ApiPaths.SALES_BY_USER)
    public List<SaleResponse> findByUser(
            @PathVariable Long userId) {

        return saleService.findByUser(userId);

    }

    @Operation(summary = "Consultar ventas realizadas con un método de pago")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas consultadas correctamente"
            )
    })
    @GetMapping(ApiPaths.SALES_BY_PAYMENT_METHOD)
    public List<SaleResponse> findByPaymentMethod(
            @PathVariable Long paymentMethodId) {

        return saleService.findByPaymentMethod(paymentMethodId);

    }

    @Operation(summary = "Consultar ventas realizadas entre dos fechas")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada correctamente"
            )
    })
    @GetMapping(ApiPaths.SALES_BETWEEN)
    public List<SaleResponse> findBetweenDates(
            @RequestParam LocalDateTime inicio,
            @RequestParam LocalDateTime fin) {

        return saleService.findBetweenDates(
                inicio,
                fin
        );

    }

    @Operation(summary = "Consultar ventas realizadas hoy")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas del día consultadas correctamente"
            )
    })
    @GetMapping(ApiPaths.SALES_TODAY)
    public List<SaleResponse> findToday() {

        return saleService.findToday();

    }

    @Operation(summary = "Consultar ventas del mes actual")
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas del mes consultadas correctamente"
            )
    })
    @GetMapping(ApiPaths.SALES_MONTH)
    public List<SaleResponse> findCurrentMonth() {

        return saleService.findCurrentMonth();

    }

    @Operation(summary = "Registrar una nueva venta")
    @PreAuthorize(SALE_CREATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Venta registrada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Información inválida o stock insuficiente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario, método de pago o producto no encontrado"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponse create(
            @Valid @RequestBody CreateSaleRequest request) {

        return saleService.create(request);

    }

    @Operation(summary = "Anular una venta")
    @PreAuthorize(SALE_CANCEL)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Venta anulada correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "La venta no puede anularse"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Venta o usuario no encontrado"
            )
    })
    @PatchMapping(ApiPaths.SALES_CANCEL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(
            @PathVariable Long id,
            @Valid @RequestBody CancelSaleRequest request) {

        saleService.cancel(
                id,
                request
        );

    }

    @Operation(
            summary = "Buscar ventas con filtros y paginación"
    )
    @PreAuthorize(SALE_READ)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ventas consultadas correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parámetros de consulta inválidos"
            )
    })
    @GetMapping("/search")
    public PagedResponse<SaleResponse> search(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "200")
            int size,

            @ModelAttribute
            SaleFilterRequest filter) {

        return saleService.search(
                filter,
                page,
                size
        );
    }

}