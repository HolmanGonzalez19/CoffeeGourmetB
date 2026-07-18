package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateSupplierRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateSupplierRequest;
import com.cgb.coffeegourmetb.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    List<SupplierResponse> findAll();

    List<SupplierResponse> findAllInactive();

    SupplierResponse findById(Long id);

    SupplierResponse create(CreateSupplierRequest request);

    SupplierResponse update(Long id, UpdateSupplierRequest request);

    void activate(Long id);

    void deactivate(Long id);

}