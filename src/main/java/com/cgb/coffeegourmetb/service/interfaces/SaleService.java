package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CancelSaleRequest;
import com.cgb.coffeegourmetb.dto.request.CreateSaleRequest;
import com.cgb.coffeegourmetb.dto.response.SaleResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleService {

    List<SaleResponse> findAll();

    SaleResponse findById(Long id);

    SaleResponse create(CreateSaleRequest request);

    List<SaleResponse> findByUser(Long userId);

    List<SaleResponse> findByPaymentMethod(Long paymentMethodId);

    List<SaleResponse> findBetweenDates(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin);

    List<SaleResponse> findToday();

    List<SaleResponse> findCurrentMonth();

    void cancel(
            Long id,
            CancelSaleRequest request);
}