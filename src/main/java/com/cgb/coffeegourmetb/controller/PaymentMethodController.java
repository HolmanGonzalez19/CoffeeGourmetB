package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.response.PaymentMethodResponse;
import com.cgb.coffeegourmetb.service.interfaces.PaymentMethodService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de la gestión de métodos de pago.
 */
@RestController
@RequestMapping(ApiPaths.PAYMENT_METHODS)
@Tag(name = "Métodos de Pago", description = "API para la gestión de métodos de pago.")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los métodos de pago activos.
     *
     * @return Lista de métodos de pago activos.
     */
    @Operation(summary = "Listar métodos de pago activos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<PaymentMethodResponse> findAll() {
        return service.findAll();
    }

    /**
     * Obtiene todos los métodos de pago inactivos.
     *
     * @return Lista de métodos de pago inactivos.
     */
    @Operation(summary = "Listar métodos de pago inactivos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping(ApiPaths.PAYMENT_METHODS_INACTIVE)
    public List<PaymentMethodResponse> findAllInactive() {
        return service.findAllInactive();
    }

    /**
     * Obtiene un método de pago por su identificador.
     *
     * @param id Identificador del método de pago.
     * @return Método de pago encontrado.
     */
    @Operation(summary = "Consultar un método de pago por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Método de pago encontrado"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @GetMapping(ApiPaths.PAYMENT_METHODS_BY_ID)
    public PaymentMethodResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Crea un nuevo método de pago.
     *
     * @param request Información del método de pago.
     * @return Método de pago creado.
     */
    @Operation(summary = "Crear un nuevo método de pago")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Método de pago creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponse create(@Valid @RequestBody CreatePaymentMethodRequest request) {
        return service.create(request);
    }

    /**
     * Actualiza un método de pago existente.
     *
     * @param id Identificador del método de pago.
     * @param request Información del método de pago.
     * @return Método de pago actualizado.
     */
    @Operation(summary = "Actualizar un método de pago")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Método de pago actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @PutMapping(ApiPaths.PAYMENT_METHODS_BY_ID)
    public PaymentMethodResponse update(@PathVariable Long id,
                                        @Valid @RequestBody UpdatePaymentMethodRequest request) {

        return service.update(id, request);
    }

    /**
     * Activa un método de pago.
     *
     * @param id Identificador del método de pago.
     */
    @Operation(summary = "Activar un método de pago")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Método de pago activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @PutMapping(ApiPaths.PAYMENT_METHODS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    /**
     * Desactiva un método de pago.
     *
     * @param id Identificador del método de pago.
     */
    @Operation(summary = "Desactivar un método de pago")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Método de pago desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Método de pago no encontrado")
    })
    @PutMapping(ApiPaths.PAYMENT_METHODS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}