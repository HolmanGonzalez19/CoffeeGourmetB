package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateRoleRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateRoleRequest;
import com.cgb.coffeegourmetb.dto.response.RoleResponse;
import com.cgb.coffeegourmetb.entity.Role;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.mapper.RoleMapper;
import com.cgb.coffeegourmetb.repository.RoleRepository;
import com.cgb.coffeegourmetb.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    /**
     * Obtiene todos los roles activos.
     *
     * @return Lista de roles activos.
     */
    @Override
    public List<RoleResponse> findAll() {

        return roleRepository.findByActivoTrue()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene todos los roles inactivos.
     *
     * @return Lista de roles inactivos.
     */
    @Override
    public List<RoleResponse> findAllInactive() {

        return roleRepository.findByActivoFalse()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un rol activo por su identificador.
     *
     * @param id Identificador del rol.
     * @return Rol encontrado.
     */
    @Override
    public RoleResponse findById(Long id) {

        Role role = roleRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol activo con id: " + id));

        return roleMapper.toResponse(role);
    }
    /**
     * Crea un nuevo rol.
     *
     * @param request Información del rol.
     * @return Rol creado.
     */
    @Override
    public RoleResponse create(CreateRoleRequest request) {

        validateRoleName(request.getNombre());

        Role role = roleMapper.toEntity(request);

        Role savedRole = roleRepository.save(role);

        return roleMapper.toResponse(savedRole);
    }

    /**
     * Actualiza un rol existente.
     *
     * @param id Identificador del rol.
     * @param request Información a actualizar.
     * @return Rol actualizado.
     */
    @Override
    public RoleResponse update(Long id, UpdateRoleRequest request) {

        Role role = findRoleById(id);

        validateRoleNameForUpdate(request.getNombre(), id);

        roleMapper.updateEntity(request, role);

        Role updatedRole = roleRepository.save(role);

        return roleMapper.toResponse(updatedRole);
    }

    /**
     * Activa un rol.
     *
     * @param id Identificador del rol.
     */
    @Override
    public void activate(Long id) {

        Role role = findRoleById(id);

        role.setActivo(true);

        roleRepository.save(role);
    }

    /**
     * Desactiva un rol.
     *
     * @param id Identificador del rol.
     */
    @Override
    public void deactivate(Long id) {

        Role role = findRoleById(id);

        role.setActivo(false);

        roleRepository.save(role);
    }

    /**
     * Obtiene un rol por su identificador.
     *
     * @param id Identificador del rol.
     * @return Rol encontrado.
     */
    private Role findRoleById(Long id) {

        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un rol con id: " + id));
    }

    /**
     * Valida que no exista un rol con el mismo nombre.
     *
     * @param nombre Nombre del rol.
     */
    private void validateRoleName(String nombre) {

        if (roleRepository.existsByNombre(nombre)) {
            throw new BusinessException(
                    "Ya existe un rol con el nombre: " + nombre);
        }

    }

    /**
     * Valida que no exista otro rol con el mismo nombre.
     *
     * @param nombre Nombre del rol.
     * @param id Identificador del rol.
     */
    private void validateRoleNameForUpdate(String nombre, Long id) {

        if (roleRepository.existsByNombreAndIdNot(nombre, id)) {
            throw new BusinessException(
                    "Ya existe otro rol con el nombre: " + nombre);
        }

    }

}