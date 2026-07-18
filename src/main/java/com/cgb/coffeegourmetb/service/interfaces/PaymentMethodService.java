package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.response.PaymentMethodResponse;

import java.util.List;

/**
 * Servicio encargado de la gestión de métodos de pago.
 */
public interface PaymentMethodService {

    List<PaymentMethodResponse> findAll();

    List<PaymentMethodResponse> findAllInactive();

    PaymentMethodResponse findById(Long id);

    PaymentMethodResponse create(CreatePaymentMethodRequest request);

    PaymentMethodResponse update(Long id, UpdatePaymentMethodRequest request);

    void activate(Long id);

    void deactivate(Long id);

}