package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query("""
    SELECT
        p.proveedor.nombre,
        SUM(p.total)
    FROM Purchase p
    WHERE p.estado =
        com.cgb.coffeegourmetb.enums.PurchaseStatus.REGISTRADA
    GROUP BY p.proveedor.nombre
    ORDER BY SUM(p.total) DESC
    """)
    List<Object[]> comprasPorProveedor();
}