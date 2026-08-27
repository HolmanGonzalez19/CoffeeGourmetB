package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.CashMovement;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.enums.CashMovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CashMovementRepository
        extends JpaRepository<CashMovement, Long> {

    List<CashMovement>
    findByCajaOrderByFechaMovimientoDesc(
            CashRegister caja
    );

    @Query("""
            SELECT COALESCE(SUM(m.monto), 0)
            FROM CashMovement m
            WHERE m.caja = :caja
              AND m.tipoMovimiento = :tipo
            """)
    BigDecimal sumMontoByCajaAndTipoMovimiento(
            @Param("caja") CashRegister caja,
            @Param("tipo") CashMovementType tipo
    );
}