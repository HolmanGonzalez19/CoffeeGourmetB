package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateUserRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateUserRequest;
import com.cgb.coffeegourmetb.dto.response.UserResponse;
import com.cgb.coffeegourmetb.service.interfaces.UserService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.USERS)
@Tag(name = "Usuarios", description = "API para la gestión de usuarios.")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Listar usuarios activos")
    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Listar usuarios inactivos")
    @GetMapping(ApiPaths.USERS_INACTIVE)
    public List<UserResponse> findAllInactive() {
        return service.findAllInactive();
    }

    @Operation(summary = "Consultar usuario por ID")
    @GetMapping(ApiPaths.USERS_BY_ID)
    public UserResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Crear usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return service.create(request);
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping(ApiPaths.USERS_BY_ID)
    public UserResponse update(@PathVariable Long id,
                               @Valid @RequestBody UpdateUserRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Activar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping(ApiPaths.USERS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        service.activate(id);
    }

    @Operation(summary = "Desactivar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping(ApiPaths.USERS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        service.deactivate(id);
    }

}