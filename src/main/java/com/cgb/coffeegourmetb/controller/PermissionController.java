package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;
import com.cgb.coffeegourmetb.service.interfaces.PermissionService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PERMISSION_DEACTIVATE;

@RestController
@RequestMapping(ApiPaths.PERMISSIONS)
@Tag(
        name = "Permisos",
        description = "API para la gestión de permisos."
)
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(
            PermissionService service) {

        this.service = service;
    }

    @Operation(summary = "Consultar todos los permisos activos")
    @PreAuthorize(PERMISSION_READ)
    @GetMapping
    public List<PermissionResponse> findAll() {

        return service.findAll();

    }

    @Operation(summary = "Consultar todos los permisos inactivos")
    @PreAuthorize(PERMISSION_READ)
    @GetMapping(ApiPaths.PERMISSIONS_INACTIVE)
    public List<PermissionResponse> findAllInactive() {

        return service.findAllInactive();

    }

    @Operation(summary = "Consultar permiso activo por identificador")
    @PreAuthorize(PERMISSION_READ)
    @GetMapping(ApiPaths.PERMISSIONS_BY_ID)
    public PermissionResponse findById(
            @PathVariable Long id) {

        return service.findById(id);

    }

    @Operation(summary = "Crear un nuevo permiso")
    @PreAuthorize(PERMISSION_CREATE)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissionResponse create(
            @Valid
            @RequestBody
            CreatePermissionRequest request) {

        return service.create(request);

    }

    @Operation(summary = "Actualizar un permiso")
    @PreAuthorize(PERMISSION_UPDATE)
    @PutMapping(ApiPaths.PERMISSIONS_BY_ID)
    public PermissionResponse update(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdatePermissionRequest request) {

        return service.update(id, request);

    }

    @Operation(summary = "Activar un permiso")
    @PreAuthorize(PERMISSION_ACTIVATE)
    @PatchMapping(ApiPaths.PERMISSIONS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(
            @PathVariable Long id) {

        service.activate(id);

    }

    @Operation(summary = "Desactivar un permiso")
    @PreAuthorize(PERMISSION_DEACTIVATE)
    @PatchMapping(ApiPaths.PERMISSIONS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(
            @PathVariable Long id) {

        service.deactivate(id);

    }

    @Operation(summary = "Consultar permisos de un usuario")
    @PreAuthorize(PERMISSION_READ)
    @GetMapping(ApiPaths.PERMISSIONS_BY_USER)
    public Set<String> getUserPermissions(
            @PathVariable Long userId) {

        return service.getUserPermissions(userId);

    }

    @Operation(summary = "Verificar si un usuario tiene un permiso")
    @PreAuthorize(PERMISSION_READ)
    @GetMapping(ApiPaths.PERMISSIONS_CHECK)
    public boolean userHasPermission(
            @PathVariable Long userId,
            @RequestParam String permissionName) {

        return service.userHasPermission(
                userId,
                permissionName);

    }

}