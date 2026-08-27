package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;
import com.cgb.coffeegourmetb.entity.CashMovement;
import org.springframework.stereotype.Component;

@Component
public class CashMovementMapper {

    public CashMovementResponse toResponse(
            CashMovement entity) {

        if (entity == null) {
            return null;
        }

        CashMovementResponse response =
                new CashMovementResponse();

        response.setId(entity.getId());

        response.setCajaId(
                entity.getCaja().getId());

        response.setUsuarioId(
                entity.getUsuario().getId());

        response.setUsuarioNombre(
                entity.getUsuario().getNombre());

        response.setTipoMovimiento(
                entity.getTipoMovimiento());

        response.setMonto(
                entity.getMonto());

        response.setDescripcion(
                entity.getDescripcion());

        response.setFechaMovimiento(
                entity.getFechaMovimiento());

        return response;
    }
}