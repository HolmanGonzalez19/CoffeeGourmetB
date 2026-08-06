package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateCashMovementRequest;
import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;

import java.util.List;

public interface CashMovementService {

    CashMovementResponse create(
            CreateCashMovementRequest request);

    List<CashMovementResponse> findByCashRegister(
            Long cashRegisterId);

}