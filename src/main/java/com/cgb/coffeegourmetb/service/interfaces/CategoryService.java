package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateCategoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateCategoryRequest;
import com.cgb.coffeegourmetb.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    List<CategoryResponse> findAllInactive();

    CategoryResponse findById(Long id);

    CategoryResponse create(CreateCategoryRequest request);

    CategoryResponse update(Long id, UpdateCategoryRequest request);

    void activate(Long id);

    void deactivate(Long id);

}