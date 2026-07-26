package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreatePurchaseRequest;
import com.cgb.coffeegourmetb.dto.request.CancelPurchaseRequest;
import com.cgb.coffeegourmetb.dto.response.PurchaseResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseService {

    List<PurchaseResponse> findAll();

    PurchaseResponse findById(Long id);

    PurchaseResponse create(CreatePurchaseRequest request);

    PurchaseResponse findByReceipt(String numeroRecibo);

    List<PurchaseResponse> findBySupplier(Long supplierId);

    List<PurchaseResponse> findByUser(Long userId);

    List<PurchaseResponse> findBetweenDates(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

    List<PurchaseResponse> findToday();

    List<PurchaseResponse> findCurrentMonth();

    void cancel(Long purchaseId,
                CancelPurchaseRequest request);
}