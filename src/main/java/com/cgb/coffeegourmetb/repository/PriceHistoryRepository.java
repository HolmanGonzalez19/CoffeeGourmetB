package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {

    List<PriceHistory> findByActivoTrue();

    List<PriceHistory> findByActivoFalse();

    Optional<PriceHistory> findByIdAndActivoTrue(Long id);

    List<PriceHistory> findByProductoIdOrderByFechaInicioDesc(Long productoId);

    Optional<PriceHistory> findByProductoIdAndActivoTrue(Long productoId);

}