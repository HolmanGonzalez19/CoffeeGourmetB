package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.CashMovement;
import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.enums.CashMovementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CashMovementRepository
        extends JpaRepository<CashMovement, Long> {

    List<CashMovement> findByCajaIdOrderByFechaMovimientoDesc(
            Long cajaId
    );

    @Query("""
        SELECT COALESCE(SUM(cm.monto),0)
        FROM CashMovement cm
        WHERE cm.caja = :caja
        AND cm.tipoMovimiento = :tipo
        """)
    BigDecimal sumMontoByCajaAndTipoMovimiento(
            @Param("caja") CashRegister caja,
            @Param("tipo") CashMovementType tipo
    );
}