package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePaymentMethodRequest;
import com.cgb.coffeegourmetb.dto.response.PaymentMethodResponse;
import com.cgb.coffeegourmetb.entity.PaymentMethod;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir objetos relacionados con PaymentMethod.
 */
@Component
public class PaymentMethodMapper {

    public PaymentMethod toEntity(CreatePaymentMethodRequest request) {

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setNombre(request.getNombre());
        paymentMethod.setDescripcion(request.getDescripcion());
        paymentMethod.setActivo(true);

        return paymentMethod;
    }

    public PaymentMethodResponse toResponse(PaymentMethod paymentMethod) {

        PaymentMethodResponse response = new PaymentMethodResponse();

        response.setId(paymentMethod.getId());
        response.setNombre(paymentMethod.getNombre());
        response.setDescripcion(paymentMethod.getDescripcion());
        response.setActivo(paymentMethod.getActivo());
        response.setFechaCreacion(paymentMethod.getFechaCreacion());
        response.setFechaActualizacion(paymentMethod.getFechaActualizacion());

        return response;
    }

    public void updateEntity(UpdatePaymentMethodRequest request,
                             PaymentMethod paymentMethod) {

        paymentMethod.setNombre(request.getNombre());
        paymentMethod.setDescripcion(request.getDescripcion());
        paymentMethod.setActivo(request.getActivo());
    }

}