package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductoId(Long productoId);

    boolean existsByProductoId(Long productoId);

}