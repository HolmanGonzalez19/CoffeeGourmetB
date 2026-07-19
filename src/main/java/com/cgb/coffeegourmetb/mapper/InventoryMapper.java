package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.InventoryMovementResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryResponse;
import com.cgb.coffeegourmetb.entity.Inventory;
import com.cgb.coffeegourmetb.entity.InventoryMovement;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryResponse toResponse(Inventory inventory){

        InventoryResponse response = new InventoryResponse();

        response.setId(inventory.getId());

        response.setProductoId(inventory.getProducto().getId());
        response.setProductoNombre(inventory.getProducto().getNombre());

        response.setCantidadActual(inventory.getCantidadActual());

        response.setFechaCreacion(inventory.getFechaCreacion());
        response.setFechaActualizacion(inventory.getFechaActualizacion());

        return response;
    }

    public InventoryMovementResponse toResponse(InventoryMovement movement){

        InventoryMovementResponse response =
                new InventoryMovementResponse();

        response.setId(movement.getId());

        response.setProductoId(movement.getProducto().getId());
        response.setProductoNombre(movement.getProducto().getNombre());

        response.setUsuarioId(movement.getUsuario().getId());
        response.setUsuarioNombre(movement.getUsuario().getNombre());

        response.setTipoMovimiento(movement.getTipoMovimiento());

        response.setCantidad(movement.getCantidad());

        response.setMotivo(movement.getMotivo());

        response.setReferencia(movement.getReferencia());

        response.setFecha(movement.getFecha());

        return response;
    }

}