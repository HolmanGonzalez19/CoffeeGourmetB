package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateInventoryMovementRequest;
import com.cgb.coffeegourmetb.dto.response.InventoryMovementResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> findAll();

    InventoryResponse findByProduct(Long productId);

    List<InventoryMovementResponse> movements(Long productId);

    InventoryMovementResponse createMovement(
            CreateInventoryMovementRequest request);

}