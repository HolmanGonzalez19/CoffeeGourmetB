package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.CashMovementResponse;
import com.cgb.coffeegourmetb.entity.CashMovement;
import org.springframework.stereotype.Component;

@Component
public class CashMovementMapper {

    public CashMovementResponse toResponse(
            CashMovement entity) {

        CashMovementResponse dto =
                new CashMovementResponse();

        dto.setId(entity.getId());
        dto.setCajaId(entity.getCaja().getId());
        dto.setUsuarioId(entity.getUsuario().getId());
        dto.setTipoMovimiento(entity.getTipoMovimiento().name());
        dto.setMonto(entity.getMonto());
        dto.setDescripcion(entity.getDescripcion());
        dto.setFechaMovimiento(entity.getFechaMovimiento());

        return dto;
    }

}