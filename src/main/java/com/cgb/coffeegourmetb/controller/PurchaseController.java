package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CancelPurchaseRequest;
import com.cgb.coffeegourmetb.dto.request.CreatePurchaseRequest;
import com.cgb.coffeegourmetb.dto.response.PurchaseResponse;
import com.cgb.coffeegourmetb.service.interfaces.PurchaseService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.PURCHASES)
@Tag(
        name = "Compras",
        description = "API para la gestión de compras realizadas a proveedores."
)
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Operation(summary = "Listar todas las compras")
    @GetMapping
    public List<PurchaseResponse> findAll() {

        return purchaseService.findAll();

    }

    @Operation(summary = "Consultar una compra por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Compra encontrada"),
            @ApiResponse(responseCode = "404", description = "Compra no encontrada")
    })
    @GetMapping(ApiPaths.PURCHASES_BY_ID)
    public PurchaseResponse findById(
            @PathVariable Long id) {

        return purchaseService.findById(id);

    }

    @Operation(summary = "Registrar una nueva compra")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Compra registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Información inválida"),
            @ApiResponse(responseCode = "404", description = "Proveedor, usuario o producto no encontrado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseResponse create(
            @Valid @RequestBody CreatePurchaseRequest request) {

        return purchaseService.create(request);

    }

    @Operation(summary = "Consultar una compra por número de recibo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Compra encontrada"),
            @ApiResponse(responseCode = "404", description = "Compra no encontrada")
    })
    @GetMapping(ApiPaths.PURCHASES_BY_RECEIPT)
    public PurchaseResponse findByReceipt(
            @PathVariable String receipt) {

        return purchaseService.findByReceipt(receipt);

    }

    @Operation(summary = "Consultar compras de un proveedor")
    @GetMapping(ApiPaths.PURCHASES_BY_SUPPLIER)
    public List<PurchaseResponse> findBySupplier(
            @PathVariable Long supplierId) {

        return purchaseService.findBySupplier(supplierId);

    }

    @Operation(summary = "Consultar compras registradas por un usuario")
    @GetMapping(ApiPaths.PURCHASES_BY_USER)
    public List<PurchaseResponse> findByUser(
            @PathVariable Long userId) {

        return purchaseService.findByUser(userId);

    }

    @Operation(summary = "Consultar compras realizadas hoy")
    @GetMapping(ApiPaths.PURCHASES_TODAY)
    public List<PurchaseResponse> today() {

        return purchaseService.findToday();

    }

    @Operation(summary = "Consultar compras del mes actual")
    @GetMapping(ApiPaths.PURCHASES_MONTH)
    public List<PurchaseResponse> month() {

        return purchaseService.findCurrentMonth();

    }

    @Operation(summary = "Consultar compras entre dos fechas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
    })
    @GetMapping(ApiPaths.PURCHASES_BETWEEN)
    public List<PurchaseResponse> between(

            @RequestParam LocalDateTime inicio,

            @RequestParam LocalDateTime fin) {

        return purchaseService.findBetweenDates(
                inicio,
                fin);

    }

    @Operation(summary = "Anular una compra")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Compra anulada correctamente"),
            @ApiResponse(responseCode = "400", description = "La compra no puede anularse"),
            @ApiResponse(responseCode = "404", description = "Compra o usuario no encontrado")
    })
    @PatchMapping(ApiPaths.PURCHASES_CANCEL)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(
            @PathVariable Long id,
            @Valid @RequestBody CancelPurchaseRequest request) {

        purchaseService.cancel(
                id,
                request);

    }

}