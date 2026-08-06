package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;
import com.cgb.coffeegourmetb.entity.Permission;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.PermissionMapper;
import com.cgb.coffeegourmetb.repository.PermissionRepository;
import com.cgb.coffeegourmetb.service.interfaces.PermissionCrudService;
import com.cgb.coffeegourmetb.util.constants.PermissionMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionCrudServiceImpl
        implements PermissionCrudService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionCrudServiceImpl(
            PermissionRepository permissionRepository,
            PermissionMapper permissionMapper) {

        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAll() {

        return permissionRepository.findByActivoTrue()
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponse> findAllInactive() {

        return permissionRepository.findByActivoFalse()
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

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

        return permissionMapper.toResponse(
                updatedPermission);
    }

    @Override
    public void activate(Long id) {

        Permission permission =
                findPermissionById(id);

        permission.setActivo(true);

        permissionRepository.save(permission);
    }

    @Override
    public void deactivate(Long id) {

        Permission permission =
                findPermissionById(id);

        permission.setActivo(false);

        permissionRepository.save(permission);
    }

    private Permission findPermissionById(Long id) {

        return permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                PermissionMessages.PERMISSION_NOT_FOUND + id));
    }

    private void validatePermissionName(
            String nombre) {

        if (permissionRepository
                .existsByNombre(nombre)) {

            throw new BusinessException(
                    PermissionMessages.PERMISSION_ALREADY_EXISTS
                            + nombre);
        }
    }

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