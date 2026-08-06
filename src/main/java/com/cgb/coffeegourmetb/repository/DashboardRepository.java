package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Inventory;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.entity.Purchase;
import com.cgb.coffeegourmetb.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

import java.math.BigDecimal;

public interface DashboardRepository
        extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT COUNT(s)
            FROM Sale s
            WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
            """)
    Long totalVentas();

    @Query("""
            SELECT COALESCE(SUM(s.total),0)
            FROM Sale s
            WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
            """)
    BigDecimal totalIngresos();

    @Query("""
            SELECT COUNT(p)
            FROM Purchase p
            WHERE p.estado = com.cgb.coffeegourmetb.enums.PurchaseStatus.REGISTRADA
            """)
    Long totalCompras();

    @Query("""
            SELECT COALESCE(SUM(p.total),0)
            FROM Purchase p
            WHERE p.estado = com.cgb.coffeegourmetb.enums.PurchaseStatus.REGISTRADA
            """)
    BigDecimal totalEgresos();

    @Query("""
            SELECT COUNT(pr)
            FROM Product pr
            WHERE pr.activo = true
            """)
    Long totalProductos();

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND s.fechaHora BETWEEN :inicio AND :fin
        """)
    BigDecimal totalVentasHoy(
            LocalDateTime inicio,
            LocalDateTime fin);

    @Query("""
        SELECT COALESCE(SUM(p.total),0)
        FROM Purchase p
        WHERE p.estado = com.cgb.coffeegourmetb.enums.PurchaseStatus.REGISTRADA
        AND p.fecha BETWEEN :inicio AND :fin
        """)
    BigDecimal totalComprasHoy(
            LocalDateTime inicio,
            LocalDateTime fin);

    @Query("""
        SELECT COUNT(i)
        FROM Inventory i
        WHERE i.cantidadActual <= i.producto.stockMinimo
        """)
    Long productosStockBajo();
}