package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository
        extends JpaRepository<Permission, Long> {

    Optional<Permission> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(
            String nombre,
            Long id);

    Optional<Permission> findByIdAndActivoTrue(Long id);

    List<Permission> findByActivoTrue();

    List<Permission> findByActivoFalse();
}