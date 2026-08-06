package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.response.RolePermissionResponse;
import com.cgb.coffeegourmetb.entity.Permission;
import com.cgb.coffeegourmetb.entity.Role;
import com.cgb.coffeegourmetb.entity.RolePermission;
import com.cgb.coffeegourmetb.entity.RolePermissionId;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.repository.PermissionRepository;
import com.cgb.coffeegourmetb.repository.RolePermissionRepository;
import com.cgb.coffeegourmetb.repository.RoleRepository;
import com.cgb.coffeegourmetb.service.interfaces.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl
        implements RolePermissionService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(
            RoleRepository roleRepository,
            PermissionRepository permissionRepository,
            RolePermissionRepository rolePermissionRepository) {

        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolePermissionResponse> findByRoleId(
            Long roleId) {

        findActiveRole(roleId);

        return rolePermissionRepository
                .findByRoleId(roleId)
                .stream()
                .filter(rolePermission ->
                        rolePermission
                                .getPermission()
                                .getActivo())
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void assignPermission(
            Long roleId,
            Long permissionId) {

        Role role = findActiveRole(roleId);

        Permission permission =
                findActivePermission(permissionId);

        if (rolePermissionRepository
                .existsByRoleIdAndPermissionId(
                        roleId,
                        permissionId)) {

            throw new BusinessException(
                    "El permiso ya está asignado al rol.");
        }

        RolePermission rolePermission =
                new RolePermission();

        rolePermission.setId(
                new RolePermissionId(
                        roleId,
                        permissionId));

        rolePermission.setRole(role);

        rolePermission.setPermission(permission);

        rolePermissionRepository.save(
                rolePermission);
    }

    @Override
    public void removePermission(
            Long roleId,
            Long permissionId) {

        findActiveRole(roleId);

        findPermission(permissionId);

        if (!rolePermissionRepository
                .existsByRoleIdAndPermissionId(
                        roleId,
                        permissionId)) {

            throw new ResourceNotFoundException(
                    "El permiso no está asignado al rol.");
        }

        rolePermissionRepository
                .deleteByRoleIdAndPermissionId(
                        roleId,
                        permissionId);
    }

    private Role findActiveRole(Long roleId) {

        return roleRepository
                .findByIdAndActivoTrue(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol activo con id: "
                                        + roleId));
    }

    private Permission findActivePermission(
            Long permissionId) {

        return permissionRepository
                .findByIdAndActivoTrue(permissionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un permiso activo con id: "
                                        + permissionId));
    }

    private Permission findPermission(
            Long permissionId) {

        return permissionRepository
                .findById(permissionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un permiso con id: "
                                        + permissionId));
    }

    private RolePermissionResponse toResponse(
            RolePermission rolePermission) {

        RolePermissionResponse response =
                new RolePermissionResponse();

        response.setRolId(
                rolePermission
                        .getRole()
                        .getId());

        response.setPermisoId(
                rolePermission
                        .getPermission()
                        .getId());

        response.setNombrePermiso(
                rolePermission
                        .getPermission()
                        .getNombre());

        response.setDescripcionPermiso(
                rolePermission
                        .getPermission()
                        .getDescripcion());

        return response;
    }
}