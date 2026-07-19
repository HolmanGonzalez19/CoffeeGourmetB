package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByProductoIdOrderByFechaDesc(Long productoId);

}