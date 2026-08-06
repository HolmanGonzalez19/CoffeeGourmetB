package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;
import com.cgb.coffeegourmetb.entity.Permission;
import com.cgb.coffeegourmetb.entity.RolePermission;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.PermissionMapper;
import com.cgb.coffeegourmetb.repository.PermissionRepository;
import com.cgb.coffeegourmetb.repository.RolePermissionRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.PermissionService;
import com.cgb.coffeegourmetb.util.constants.PermissionMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(
            PermissionRepository permissionRepository,
            UserRepository userRepository,
            RolePermissionRepository rolePermissionRepository,
            PermissionMapper permissionMapper) {

        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionMapper = permissionMapper;
    }

    /**
     * Obtiene todos los permisos activos.
     *
     * @return Lista de permisos activos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAll() {

        return permissionRepository.findByActivoTrue()
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene todos los permisos inactivos.
     *
     * @return Lista de permisos inactivos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAllInactive() {

        return permissionRepository.findByActivoFalse()
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    /**
     * Obtiene un permiso activo por su identificador.
     *
     * @param id Identificador del permiso.
     * @return Permiso encontrado.
     */
    @Override
    @Transactional(readOnly = true)
    public PermissionResponse findById(Long id) {

        Permission permission =
                permissionRepository.findByIdAndActivoTrue(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        PermissionMessages.ACTIVE_PERMISSION_NOT_FOUND + id));

        return permissionMapper.toResponse(permission);
    }

    /**
     * Crea un nuevo permiso.
     *
     * @param request Información del permiso.
     * @return Permiso creado.
     */
    @Override
    public PermissionResponse create(
            CreatePermissionRequest request) {

        validatePermissionName(request.getNombre());

        Permission permission =
                permissionMapper.toEntity(request);

        Permission savedPermission =
                permissionRepository.save(permission);

        return permissionMapper.toResponse(savedPermission);
    }

    /**
     * Actualiza un permiso existente.
     *
     * @param id Identificador del permiso.
     * @param request Información a actualizar.
     * @return Permiso actualizado.
     */
    @Override
    public PermissionResponse update(
            Long id,
            UpdatePermissionRequest request) {

        Permission permission =
                findPermissionById(id);

        validatePermissionNameForUpdate(
                request.getNombre(),
                id);

        permissionMapper.updateEntity(
                request,
                permission);

        Permission updatedPermission =
                permissionRepository.save(permission);

        return permissionMapper.toResponse(updatedPermission);
    }

    /**
     * Activa un permiso.
     *
     * @param id Identificador del permiso.
     */
    @Override
    public void activate(Long id) {

        Permission permission =
                findPermissionById(id);

        permission.setActivo(true);

        permissionRepository.save(permission);
    }

    /**
     * Desactiva un permiso.
     *
     * @param id Identificador del permiso.
     */
    @Override
    public void deactivate(Long id) {

        Permission permission =
                findPermissionById(id);

        permission.setActivo(false);

        permissionRepository.save(permission);
    }

    /**
     * Obtiene todos los permisos activos heredados
     * por el usuario a través de su rol.
     *
     * @param userId Identificador del usuario.
     * @return Conjunto de nombres de permisos.
     */
    @Override
    @Transactional(readOnly = true)
    public Set<String> getUserPermissions(Long userId) {

        User user =
                userRepository.findByIdAndActivoTrue(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        PermissionMessages.USER_NOT_FOUND_OR_INACTIVE));

        return rolePermissionRepository
                .findByRoleIdAndPermissionActivoTrue(
                        user.getRole().getId())
                .stream()
                .map(RolePermission::getPermission)
                .map(Permission::getNombre)
                .collect(Collectors.toSet());
    }

    /**
     * Verifica si un usuario posee un permiso específico.
     *
     * @param userId Identificador del usuario.
     * @param permissionName Nombre del permiso.
     * @return true si el usuario tiene el permiso.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean userHasPermission(
            Long userId,
            String permissionName) {

        return getUserPermissions(userId)
                .contains(permissionName);
    }

    /**
     * Busca un permiso por su identificador,
     * independientemente de su estado.
     */
    private Permission findPermissionById(Long id) {

        return permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                PermissionMessages.PERMISSION_NOT_FOUND + id));
    }

    /**
     * Valida que no exista un permiso con el mismo nombre.
     */
    private void validatePermissionName(
            String nombre) {

        if (permissionRepository.existsByNombre(nombre)) {

            throw new BusinessException(
                    PermissionMessages.PERMISSION_ALREADY_EXISTS + nombre);
        }
    }

    /**
     * Valida que no exista otro permiso
     * con el mismo nombre.
     */
    private void validatePermissionNameForUpdate(
            String nombre,
            Long id) {

        if (permissionRepository
                .existsByNombreAndIdNot(nombre, id)) {

            throw new BusinessException(
                    PermissionMessages.PERMISSION_ALREADY_EXISTS_FOR_UPDATE
                            + nombre);
        }
    }
}