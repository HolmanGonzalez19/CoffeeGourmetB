package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleDetailRepository
        extends JpaRepository<SaleDetail, Long> {

    @Query("""
        SELECT COALESCE(SUM(sd.cantidad),0)
        FROM SaleDetail sd
        WHERE sd.venta.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND sd.venta.fechaHora BETWEEN :inicio AND :fin
    """)
    Long totalProductosVendidos(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
        SELECT
            sd.producto.id,
            sd.producto.nombre,
            SUM(sd.cantidad)
        FROM SaleDetail sd
        WHERE sd.venta.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        GROUP BY
            sd.producto.id,
            sd.producto.nombre
        ORDER BY SUM(sd.cantidad) DESC
    """)
    List<Object[]> topSellingProducts();

    @Query("""
        SELECT
            sd.producto.id,
            sd.producto.nombre,
            SUM(sd.cantidad)
        FROM SaleDetail sd
        WHERE sd.venta.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND sd.venta.fechaHora BETWEEN :inicio AND :fin
        GROUP BY
            sd.producto.id,
            sd.producto.nombre
        ORDER BY SUM(sd.cantidad) DESC
    """)
    List<Object[]> topSellingProductsBetweenDates(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
    SELECT COALESCE(SUM(sd.cantidad), 0)
    FROM SaleDetail sd
    WHERE sd.venta.id = :ventaId
    AND sd.venta.estado =
        com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
""")
    Long cantidadProductosPorVenta(
            @Param("ventaId") Long ventaId
    );

    @Query("""
    SELECT COALESCE(SUM(sd.cantidad), 0)
    FROM SaleDetail sd
    WHERE sd.venta.estado =
        com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
""")
    Long totalProductosVendidos();

}