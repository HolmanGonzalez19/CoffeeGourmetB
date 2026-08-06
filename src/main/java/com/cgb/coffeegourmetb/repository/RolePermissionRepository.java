package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.RolePermission;
import com.cgb.coffeegourmetb.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository
        extends JpaRepository<RolePermission, RolePermissionId> {

    List<RolePermission> findByRoleId(Long roleId);

    List<RolePermission> findByRoleIdAndPermissionActivoTrue(Long roleId);

    boolean existsByRoleIdAndPermissionId(
            Long roleId,
            Long permissionId);

    void deleteByRoleIdAndPermissionId(
            Long roleId,
            Long permissionId);
}