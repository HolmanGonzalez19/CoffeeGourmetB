package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateRoleRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateRoleRequest;
import com.cgb.coffeegourmetb.dto.response.RoleResponse;
import com.cgb.coffeegourmetb.service.interfaces.RoleService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLES_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLES_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLES_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLES_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLES_DEACTIVATE;

/**
 * Controlador encargado de la gestión de roles.
 */
@RestController
@RequestMapping(ApiPaths.ROLES)
@Tag(name = "Roles", description = "API para la gestión de roles del sistema.")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Obtiene todos los roles activos.
     *
     * @return Lista de roles activos.
     */
    @Operation(summary = "Listar roles activos")
    @PreAuthorize(ROLES_READ)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public List<RoleResponse> findAll() {
        return roleService.findAll();
    }

    /**
     * Obtiene todos los roles inactivos.
     *
     * @return Lista de roles inactivos.
     */
    @Operation(summary = "Listar roles inactivos")
    @PreAuthorize(ROLES_READ)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping(ApiPaths.ROLES_INACTIVE)
    public List<RoleResponse> findAllInactive() {
        return roleService.findAllInactive();
    }

    /**
     * Obtiene un rol por su identificador.
     *
     * @param id Identificador del rol.
     * @return Rol encontrado.
     */
    @Operation(summary = "Consultar un rol por ID")
    @PreAuthorize(ROLES_READ)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping(ApiPaths.ROLES_BY_ID)
    public RoleResponse findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    /**
     * Crea un nuevo rol.
     *
     * @param request Información del rol.
     * @return Rol creado.
     */
    @Operation(summary = "Crear un nuevo rol")
    @PreAuthorize(ROLES_CREATE)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Rol creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse create(@Valid @RequestBody CreateRoleRequest request) {
        return roleService.create(request);
    }

    /**
     * Actualiza un rol existente.
     *
     * @param id Identificador del rol.
     * @param request Información del rol.
     * @return Rol actualizado.
     */
    @Operation(summary = "Actualizar un rol")
    @PreAuthorize(ROLES_UPDATE)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping(ApiPaths.ROLES_BY_ID)
    public RoleResponse update(@PathVariable Long id,
                               @Valid @RequestBody UpdateRoleRequest request) {

        return roleService.update(id, request);
    }

    /**
     * Activa un rol.
     *
     * @param id Identificador del rol.
     */
    @Operation(summary = "Activar un rol")
    @PreAuthorize(ROLES_ACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rol activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping(ApiPaths.ROLES_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable Long id) {
        roleService.activate(id);
    }

    /**
     * Desactiva un rol.
     *
     * @param id Identificador del rol.
     */
    @Operation(summary = "Desactivar un rol")
    @PreAuthorize(ROLES_DEACTIVATE)
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rol desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping(ApiPaths.ROLES_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        roleService.deactivate(id);
    }

}