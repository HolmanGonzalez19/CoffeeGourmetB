package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateRoleRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateRoleRequest;
import com.cgb.coffeegourmetb.dto.response.RoleResponse;
import com.cgb.coffeegourmetb.service.interfaces.RoleService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de la gestión de roles.
 */
@RestController
@RequestMapping(ApiPaths.ROLES)
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
    @GetMapping
    public List<RoleResponse> findAll() {
        return roleService.findAll();
    }

    /**
     * Obtiene todos los roles inactivos.
     *
     * @return Lista de roles inactivos.
     */
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
    @PutMapping(ApiPaths.ROLES_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@PathVariable Long id) {
        roleService.deactivate(id);
    }

}