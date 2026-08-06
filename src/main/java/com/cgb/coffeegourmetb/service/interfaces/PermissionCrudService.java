package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreatePermissionRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePermissionRequest;
import com.cgb.coffeegourmetb.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionCrudService {

    List<PermissionResponse> findAll();

    List<PermissionResponse> findAllInactive();

    PermissionResponse findById(Long id);

    PermissionResponse create(CreatePermissionRequest request);

    PermissionResponse update(
            Long id,
            UpdatePermissionRequest request);

    void activate(Long id);

    void deactivate(Long id);
}