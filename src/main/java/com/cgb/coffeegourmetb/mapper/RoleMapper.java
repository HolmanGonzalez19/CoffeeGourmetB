package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreateRoleRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateRoleRequest;
import com.cgb.coffeegourmetb.dto.response.RoleResponse;
import com.cgb.coffeegourmetb.entity.Role;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir objetos relacionados con Role.
 */
@Component
public class RoleMapper {

    /**
     * Convierte un CreateRoleRequest en una entidad Role.
     */
    public Role toEntity(CreateRoleRequest request) {

        Role role = new Role();
        role.setNombre(request.getNombre());
        role.setDescripcion(request.getDescripcion());
        role.setActivo(true);

        return role;
    }

    /**
     * Convierte una entidad Role en RoleResponse.
     */
    public RoleResponse toResponse(Role role) {

        RoleResponse response = new RoleResponse();

        response.setId(role.getId());
        response.setNombre(role.getNombre());
        response.setDescripcion(role.getDescripcion());
        response.setActivo(role.getActivo());
        response.setFechaCreacion(role.getFechaCreacion());
        response.setFechaActualizacion(role.getFechaActualizacion());

        return response;
    }

    /**
     * Actualiza una entidad existente con la información recibida.
     */
    public void updateEntity(UpdateRoleRequest request, Role role) {

        role.setNombre(request.getNombre());
        role.setDescripcion(request.getDescripcion());
        role.setActivo(request.getActivo());

    }

}