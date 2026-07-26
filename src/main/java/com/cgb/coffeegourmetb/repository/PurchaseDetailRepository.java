package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    List<PurchaseDetail> findByCompraId(Long compraId);

}