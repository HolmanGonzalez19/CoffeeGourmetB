package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateSupplierRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateSupplierRequest;
import com.cgb.coffeegourmetb.dto.response.SupplierResponse;
import com.cgb.coffeegourmetb.entity.Supplier;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.SupplierMapper;
import com.cgb.coffeegourmetb.repository.SupplierRepository;
import com.cgb.coffeegourmetb.service.interfaces.SupplierService;
import com.cgb.coffeegourmetb.util.constants.SupplierMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    public SupplierServiceImpl(SupplierRepository repository,
                               SupplierMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findAll() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierResponse> findAllInactive() {

        return repository.findByActivoFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierResponse findById(Long id) {

        Supplier supplier = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        SupplierMessages.SUPPLIER_ACTIVE_NOT_FOUND + id));

        return mapper.toResponse(supplier);
    }

    @Override
    public SupplierResponse create(CreateSupplierRequest request) {

        validateSupplierName(request.getNombre());

        Supplier supplier = mapper.toEntity(request);

        return mapper.toResponse(repository.save(supplier));
    }

    @Override
    public SupplierResponse update(Long id, UpdateSupplierRequest request) {

        Supplier supplier = findSupplierById(id);

        validateSupplierNameForUpdate(request.getNombre(), id);

        mapper.updateEntity(request, supplier);

        return mapper.toResponse(repository.save(supplier));
    }

    @Override
    public void activate(Long id) {

        Supplier supplier = findSupplierById(id);

        supplier.setActivo(true);

        repository.save(supplier);
    }

    @Override
    public void deactivate(Long id) {

        Supplier supplier = findSupplierById(id);

        supplier.setActivo(false);

        repository.save(supplier);
    }

    private Supplier findSupplierById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        SupplierMessages.SUPPLIER_NOT_FOUND + id));
    }

    private void validateSupplierName(String nombre) {

        if (repository.existsByNombre(nombre)) {
            throw new BusinessException(
                    SupplierMessages.SUPPLIER_ALREADY_EXISTS + nombre);
        }
    }

    private void validateSupplierNameForUpdate(String nombre, Long id) {

        if (repository.existsByNombreAndIdNot(nombre, id)) {
            throw new BusinessException(
                    SupplierMessages.SUPPLIER_ALREADY_EXISTS_UPDATE + nombre);
        }
    }

}