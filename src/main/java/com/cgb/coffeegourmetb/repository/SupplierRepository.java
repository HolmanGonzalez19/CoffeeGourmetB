package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByIdAndActivoTrue(Long id);

    Optional<Supplier> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    List<Supplier> findByActivoTrue();

    List<Supplier> findByActivoFalse();

}