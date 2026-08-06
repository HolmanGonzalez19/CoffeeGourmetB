package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;
import com.cgb.coffeegourmetb.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionResponse toResponse(Permission permission) {

        PermissionResponse response = new PermissionResponse();

        response.setId(permission.getId());
        response.setNombre(permission.getNombre());
        response.setDescripcion(permission.getDescripcion());
        response.setActivo(permission.getActivo());

        return response;
    }

    public Permission toEntity(CreatePermissionRequest request) {

        Permission permission = new Permission();

        permission.setNombre(request.getNombre());
        permission.setDescripcion(request.getDescripcion());

        return permission;
    }

    public void updateEntity(
            UpdatePermissionRequest request,
            Permission permission) {

        permission.setNombre(request.getNombre());
        permission.setDescripcion(request.getDescripcion());
    }
}