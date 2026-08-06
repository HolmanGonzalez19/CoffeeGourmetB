package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.CashRegisterResponse;
import com.cgb.coffeegourmetb.entity.CashRegister;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir entidades de caja
 * en objetos de respuesta para la API.
 */
@Component
public class CashRegisterMapper {

    /**
     * Convierte una entidad CashRegister en un CashRegisterResponse.
     *
     * @param entity entidad de caja.
     * @return información de la caja para respuesta de API.
     */
    public CashRegisterResponse toResponse(
            CashRegister entity) {

        if (entity == null) {
            return null;
        }

        CashRegisterResponse response =
                new CashRegisterResponse();

        response.setId(
                entity.getId());

        response.setUsuarioAperturaId(
                entity.getUsuarioApertura().getId());

        response.setUsuarioAperturaNombre(
                entity.getUsuarioApertura().getNombre());

        response.setFechaApertura(
                entity.getFechaApertura());

        response.setMontoInicial(
                entity.getMontoInicial());

        response.setEstado(
                entity.getEstado().name());

        if (entity.getUsuarioCierre() != null) {

            response.setUsuarioCierreId(
                    entity.getUsuarioCierre().getId());

            response.setUsuarioCierreNombre(
                    entity.getUsuarioCierre().getNombre());
        }

        response.setFechaCierre(
                entity.getFechaCierre());

        response.setEfectivoEsperado(
                entity.getEfectivoEsperado());

        response.setEfectivoContado(
                entity.getEfectivoContado());

        response.setDiferencia(
                entity.getDiferencia());

        return response;
    }
}