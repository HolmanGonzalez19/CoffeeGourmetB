package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<Purchase> findByNumeroRecibo(String numeroRecibo);

    boolean existsByNumeroRecibo(String numeroRecibo);

    List<Purchase> findByProveedorIdOrderByFechaDesc(Long proveedorId);

    List<Purchase> findByUsuarioIdOrderByFechaDesc(Long usuarioId);

    List<Purchase> findByFechaBetweenOrderByFechaDesc(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);
}