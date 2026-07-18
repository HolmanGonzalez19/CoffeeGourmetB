package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateCategoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateCategoryRequest;
import com.cgb.coffeegourmetb.dto.response.CategoryResponse;
import com.cgb.coffeegourmetb.entity.Category;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.CategoryMapper;
import com.cgb.coffeegourmetb.repository.CategoryRepository;
import com.cgb.coffeegourmetb.service.interfaces.CategoryService;
import com.cgb.coffeegourmetb.util.constants.CategoryMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository,
                               CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAllInactive() {

        return repository.findByActivoFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {

        Category category = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CategoryMessages.CATEGORY_ACTIVE_NOT_FOUND + id));

        return mapper.toResponse(category);
    }

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        validateCategoryName(request.getNombre());

        Category category = mapper.toEntity(request);

        return mapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest request) {

        Category category = findCategoryById(id);

        validateCategoryNameForUpdate(request.getNombre(), id);

        mapper.updateEntity(request, category);

        return mapper.toResponse(repository.save(category));
    }

    @Override
    public void activate(Long id) {

        Category category = findCategoryById(id);

        category.setActivo(true);

        repository.save(category);
    }

    @Override
    public void deactivate(Long id) {

        Category category = findCategoryById(id);

        category.setActivo(false);

        repository.save(category);
    }

    private Category findCategoryById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        CategoryMessages.CATEGORY_NOT_FOUND + id));
    }

    private void validateCategoryName(String nombre) {

        if (repository.existsByNombre(nombre)) {
            throw new BusinessException(
                    CategoryMessages.CATEGORY_ALREADY_EXISTS + nombre);
        }
    }

    private void validateCategoryNameForUpdate(String nombre, Long id) {

        if (repository.existsByNombreAndIdNot(nombre, id)) {
            throw new BusinessException(
                    CategoryMessages.CATEGORY_ALREADY_EXISTS_UPDATE + nombre);
        }
    }

}