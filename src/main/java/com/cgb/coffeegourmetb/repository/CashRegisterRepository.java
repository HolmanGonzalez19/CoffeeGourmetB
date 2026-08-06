package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.CashRegister;
import com.cgb.coffeegourmetb.enums.CashRegisterStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la gestión de cajas.
 */
public interface CashRegisterRepository
        extends JpaRepository<CashRegister, Long> {

    Optional<CashRegister> findByEstado(
            CashRegisterStatus estado
    );

    List<CashRegister> findAllByEstado(
            CashRegisterStatus estado
    );

    boolean existsByEstado(
            CashRegisterStatus estado
    );

    List<CashRegister> findAllByOrderByFechaAperturaDesc();

    List<CashRegister> findByEstadoOrderByFechaAperturaDesc(
            CashRegisterStatus estado
    );

}