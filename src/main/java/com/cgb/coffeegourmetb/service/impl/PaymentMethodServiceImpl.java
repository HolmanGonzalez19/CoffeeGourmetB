package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.response.PaymentMethodResponse;
import com.cgb.coffeegourmetb.entity.PaymentMethod;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.PaymentMethodMapper;
import com.cgb.coffeegourmetb.repository.PaymentMethodRepository;
import com.cgb.coffeegourmetb.service.interfaces.PaymentMethodService;
import com.cgb.coffeegourmetb.util.constants.PaymentMethodMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    public PaymentMethodServiceImpl(PaymentMethodRepository repository,
                                    PaymentMethodMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethodResponse> findAll() {
        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethodResponse> findAllInactive() {
        return repository.findByActivoFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentMethodResponse findById(Long id) {

        PaymentMethod paymentMethod = repository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PaymentMethodMessages.ACTIVE_PAYMENT_METHOD_NOT_FOUND + id));

        return mapper.toResponse(paymentMethod);
    }

    @Override
    public PaymentMethodResponse create(CreatePaymentMethodRequest request) {

        validateName(request.getNombre());

        PaymentMethod paymentMethod = mapper.toEntity(request);

        return mapper.toResponse(repository.save(paymentMethod));
    }

    @Override
    public PaymentMethodResponse update(Long id, UpdatePaymentMethodRequest request) {

        PaymentMethod paymentMethod = findPaymentMethod(id);

        validateNameForUpdate(request.getNombre(), id);

        mapper.updateEntity(request, paymentMethod);

        return mapper.toResponse(repository.save(paymentMethod));
    }

    @Override
    public void activate(Long id) {

        PaymentMethod paymentMethod = findPaymentMethod(id);

        paymentMethod.setActivo(true);

        repository.save(paymentMethod);
    }

    @Override
    public void deactivate(Long id) {

        PaymentMethod paymentMethod = findPaymentMethod(id);

        paymentMethod.setActivo(false);

        repository.save(paymentMethod);
    }

    private PaymentMethod findPaymentMethod(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        PaymentMethodMessages.PAYMENT_METHOD_NOT_FOUND + id));
    }

    private void validateName(String nombre) {

        if (repository.existsByNombre(nombre)) {
            throw new BusinessException(
                    PaymentMethodMessages.PAYMENT_METHOD_ALREADY_EXISTS + nombre);
        }

    }

    private void validateNameForUpdate(String nombre, Long id) {

        if (repository.existsByNombreAndIdNot(nombre, id)) {
            throw new BusinessException(
                    PaymentMethodMessages.PAYMENT_METHOD_ALREADY_EXISTS_FOR_UPDATE + nombre);
        }

    }

}