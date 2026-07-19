package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.response.PriceHistoryResponse;

import java.util.List;

public interface PriceHistoryService {

    List<PriceHistoryResponse> findAll();

    List<PriceHistoryResponse> findAllInactive();

    PriceHistoryResponse findById(Long id);

    List<PriceHistoryResponse> findByProduct(Long productId);

    PriceHistoryResponse create(CreatePriceHistoryRequest request);

    PriceHistoryResponse update(Long id,
                                UpdatePriceHistoryRequest request);

    void activate(Long id);

    void deactivate(Long id);

}