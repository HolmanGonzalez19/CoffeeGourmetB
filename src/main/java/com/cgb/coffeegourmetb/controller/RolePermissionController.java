package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.AssignPermissionRequest;
import com.cgb.coffeegourmetb.dto.response.RolePermissionResponse;
import com.cgb.coffeegourmetb.service.interfaces.RolePermissionService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLE_PERMISSION_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLE_PERMISSION_ASSIGN;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.ROLE_PERMISSION_REMOVE;

/**
 * Controlador REST para la gestión de permisos asignados a roles.
 */
@RestController
@RequestMapping(ApiPaths.ROLES)
@Tag(
        name = "Permisos de Roles",
        description = "API para la asignación y gestión de permisos asociados a roles."
)
@SecurityRequirement(name = "bearerAuth")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    public RolePermissionController(
            RolePermissionService rolePermissionService) {

        this.rolePermissionService =
                rolePermissionService;
    }

    /**
     * Obtiene todos los permisos asignados a un rol.
     *
     * @param roleId Identificador del rol.
     * @return Lista de permisos asignados al rol.
     */
    @Operation(summary = "Consultar permisos asignados a un rol")
    @PreAuthorize(ROLE_PERMISSION_READ)
    @GetMapping(ApiPaths.ROLE_PERMISSIONS)
    public ResponseEntity<List<RolePermissionResponse>>
    findByRoleId(
            @PathVariable Long roleId) {

        return ResponseEntity.ok(
                rolePermissionService
                        .findByRoleId(roleId));
    }

    /**
     * Asigna un permiso a un rol.
     *
     * @param roleId Identificador del rol.
     * @param request Información del permiso a asignar.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Asignar un permiso a un rol")
    @PreAuthorize(ROLE_PERMISSION_ASSIGN)
    @PostMapping(ApiPaths.ROLE_PERMISSIONS_ASSIGN)
    public ResponseEntity<Void> assignPermission(
            @PathVariable Long roleId,
            @Valid
            @RequestBody
            AssignPermissionRequest request) {

        rolePermissionService.assignPermission(
                roleId,
                request.getPermisoId());

        return ResponseEntity.noContent()
                .build();
    }

    /**
     * Elimina un permiso asignado a un rol.
     *
     * @param roleId Identificador del rol.
     * @param permissionId Identificador del permiso.
     * @return Respuesta sin contenido.
     */
    @Operation(summary = "Eliminar un permiso de un rol")
    @PreAuthorize(ROLE_PERMISSION_REMOVE)
    @DeleteMapping(ApiPaths.ROLE_PERMISSIONS_REMOVE)
    public ResponseEntity<Void> removePermission(
            @PathVariable Long roleId,
            @PathVariable Long permissionId) {

        rolePermissionService.removePermission(
                roleId,
                permissionId);

        return ResponseEntity.noContent()
                .build();
    }
}