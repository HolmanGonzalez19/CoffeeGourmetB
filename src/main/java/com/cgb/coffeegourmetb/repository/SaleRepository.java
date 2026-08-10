package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.entity.Sale;
import com.cgb.coffeegourmetb.enums.SaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cgb.coffeegourmetb.entity.CashRegister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByOrderByFechaHoraDesc();

    List<Sale> findByUsuarioIdOrderByFechaHoraDesc(
            Long usuarioId);

    List<Sale> findByMetodoPagoIdOrderByFechaHoraDesc(
            Long metodoPagoId);

    List<Sale> findByFechaHoraBetweenOrderByFechaHoraDesc(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

    List<Sale> findByEstadoOrderByFechaHoraDesc(
            SaleStatus estado);

    List<Sale> findByUsuarioIdAndEstadoOrderByFechaHoraDesc(
            Long usuarioId,
            SaleStatus estado);

    List<Sale> findByMetodoPagoIdAndEstadoOrderByFechaHoraDesc(
            Long metodoPagoId,
            SaleStatus estado);

    List<Sale> findByFechaHoraBetweenAndEstadoOrderByFechaHoraDesc(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            SaleStatus estado);

    Optional<Sale> findByIdAndEstado(
            Long id,
            SaleStatus estado);

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE s.caja = :caja
        AND s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    """)
    BigDecimal sumTotalByCaja(
            @Param("caja") CashRegister caja
    );

    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE s.caja = :caja
        AND s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND s.metodoPago.nombre = :metodoPago
    """)
    BigDecimal sumTotalByCajaAndPaymentMethod(
            @Param("caja") CashRegister caja,
            @Param("metodoPago") String metodoPago
    );

    @Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE s.caja = :caja
        AND s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    """)
    BigDecimal sumTotalRegisteredByCaja(
            @Param("caja") CashRegister caja
    );

    @Query("""
        SELECT
            s.metodoPago.nombre,
            SUM(s.total)
        FROM Sale s
        WHERE s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        GROUP BY s.metodoPago.nombre
        ORDER BY SUM(s.total) DESC
    """)
    List<Object[]> ventasPorMetodoPago();

    @Query("""
        SELECT
            FUNCTION('TO_CHAR', s.fechaHora, 'YYYY-MM'),
            SUM(s.total)
        FROM Sale s
        WHERE s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        GROUP BY FUNCTION('TO_CHAR', s.fechaHora, 'YYYY-MM')
        ORDER BY FUNCTION('TO_CHAR', s.fechaHora, 'YYYY-MM')
    """)
    List<Object[]> ventasPorMes();

    @Query("""
        SELECT COUNT(s)
        FROM Sale s
        WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND s.fechaHora BETWEEN :inicio AND :fin
    """)
    Long countSales(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
        AND s.fechaHora BETWEEN :inicio AND :fin
    """)
    BigDecimal totalSales(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
        SELECT COUNT(s)
        FROM Sale s
        WHERE s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    """)
    Long totalSalesCount();

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE s.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    """)
    BigDecimal totalSalesAmount();


    @Query("""
    SELECT COUNT(s)
    FROM Sale s
    WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    AND s.fechaHora BETWEEN :inicio AND :fin
    """)
    Long countVentasRegistradas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
    SELECT COALESCE(SUM(s.total),0)
    FROM Sale s
    WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    AND s.fechaHora BETWEEN :inicio AND :fin
    """)
    BigDecimal totalVentasRegistradas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    @Query("""
    SELECT COUNT(s)
    FROM Sale s
    WHERE s.estado = com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    AND s.fechaHora BETWEEN :inicio AND :fin
    """)
    Long countVentasHoy(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}