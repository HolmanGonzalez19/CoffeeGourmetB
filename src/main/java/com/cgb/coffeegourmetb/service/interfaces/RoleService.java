package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateRoleRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateRoleRequest;
import com.cgb.coffeegourmetb.dto.response.RoleResponse;

import java.util.List;

/**
 * Servicio encargado de la gestión de roles.
 */
public interface RoleService {

    /**
     * Obtiene todos los roles activos.
     */
    List<RoleResponse> findAll();

    /**
     * Obtiene todos los roles Inactivos.
     */
    List<RoleResponse> findAllInactive();

    /**
     * Obtiene un rol por su identificador.
     */
    RoleResponse findById(Long id);

    /**
     * Crea un nuevo rol.
     */
    RoleResponse create(CreateRoleRequest request);

    /**
     * Actualiza un rol existente.
     */
    RoleResponse update(Long id, UpdateRoleRequest request);

    /**
     * Activa un rol.
     */
    void activate(Long id);

    /**
     * Desactiva un rol.
     */
    void deactivate(Long id);

}