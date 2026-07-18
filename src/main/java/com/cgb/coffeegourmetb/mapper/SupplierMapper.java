package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreateSupplierRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateSupplierRequest;
import com.cgb.coffeegourmetb.dto.response.SupplierResponse;
import com.cgb.coffeegourmetb.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(CreateSupplierRequest request) {

        Supplier supplier = new Supplier();

        supplier.setNombre(request.getNombre());
        supplier.setContacto(request.getContacto());
        supplier.setTelefono(request.getTelefono());
        supplier.setCorreo(request.getCorreo());
        supplier.setDireccion(request.getDireccion());
        supplier.setObservacion(request.getObservacion());
        supplier.setActivo(true);

        return supplier;
    }

    public SupplierResponse toResponse(Supplier supplier) {

        SupplierResponse response = new SupplierResponse();

        response.setId(supplier.getId());
        response.setNombre(supplier.getNombre());
        response.setContacto(supplier.getContacto());
        response.setTelefono(supplier.getTelefono());
        response.setCorreo(supplier.getCorreo());
        response.setDireccion(supplier.getDireccion());
        response.setObservacion(supplier.getObservacion());
        response.setActivo(supplier.getActivo());
        response.setFechaCreacion(supplier.getFechaCreacion());
        response.setFechaActualizacion(supplier.getFechaActualizacion());

        return response;
    }

    public void updateEntity(UpdateSupplierRequest request,
                             Supplier supplier) {

        supplier.setNombre(request.getNombre());
        supplier.setContacto(request.getContacto());
        supplier.setTelefono(request.getTelefono());
        supplier.setCorreo(request.getCorreo());
        supplier.setDireccion(request.getDireccion());
        supplier.setObservacion(request.getObservacion());
        supplier.setActivo(request.getActivo());
    }

}