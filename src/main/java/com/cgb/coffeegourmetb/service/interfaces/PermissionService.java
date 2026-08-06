package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    List<PermissionResponse> findAll();

    List<PermissionResponse> findAllInactive();

    PermissionResponse findById(Long id);

    PermissionResponse create(CreatePermissionRequest request);

    PermissionResponse update(
            Long id,
            UpdatePermissionRequest request);

    void activate(Long id);

    void deactivate(Long id);

    Set<String> getUserPermissions(Long userId);

    boolean userHasPermission(
            Long userId,
            String permissionName);
}