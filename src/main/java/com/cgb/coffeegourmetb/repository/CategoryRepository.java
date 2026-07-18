package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio encargado del acceso a datos de las categorías.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByIdAndActivoTrue(Long id);

    Optional<Category> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    List<Category> findByActivoTrue();

    List<Category> findByActivoFalse();

}