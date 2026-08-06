package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.response.RolePermissionResponse;

import java.util.List;

public interface RolePermissionService {

    List<RolePermissionResponse> findByRoleId(Long roleId);

    void assignPermission(
            Long roleId,
            Long permissionId);

    void removePermission(
            Long roleId,
            Long permissionId);
}