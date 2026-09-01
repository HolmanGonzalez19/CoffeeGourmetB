package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByProveedorIdOrderByFechaDesc(Long proveedorId);

    List<Purchase> findByUsuarioIdOrderByFechaDesc(Long usuarioId);

    List<Purchase> findAllByOrderByFechaDesc();

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

    @Query(value = """
        SELECT nextval(
            pg_get_serial_sequence(
                'coffeegourmet.compras',
                'id'
            )
        )
        """, nativeQuery = true)
    Long findNextId();

    @Query("""
    SELECT p
    FROM Purchase p
    WHERE p.estado =
        com.cgb.coffeegourmetb.enums.PurchaseStatus.REGISTRADA
      AND p.fecha >= :fechaDesde
    ORDER BY p.fecha DESC
    """)
    List<Purchase> findRecentRegisteredPurchases(
            @Param("fechaDesde") LocalDateTime fechaDesde);
}