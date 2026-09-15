package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.service.interfaces.ReceiptService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_OPERAR_CAJA;

@RestController
@RequestMapping(ApiPaths.RECEIPTS)
@Tag(
        name = "Recibos",
        description = "Operaciones de impresión de tickets."
)
@SecurityRequirement(name = "bearerAuth")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(
            ReceiptService receiptService) {

        this.receiptService = receiptService;
    }

    @Operation(
            summary = "Imprimir ticket de una venta"
    )
    @PreAuthorize(PERMISSION_OPERAR_CAJA)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ticket enviado correctamente a la impresora"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Venta no encontrada"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No fue posible imprimir el ticket"
            )
    })
    @GetMapping(ApiPaths.RECEIPTS_PRINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void print(
            @PathVariable Long id) {

        receiptService.print(id);
    }
}