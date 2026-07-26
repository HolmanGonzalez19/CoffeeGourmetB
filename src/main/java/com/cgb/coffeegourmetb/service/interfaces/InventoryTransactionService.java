package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.entity.MovementType;

public interface InventoryTransactionService {

    void processMovement(
            Long productoId,
            Long usuarioId,
            MovementType tipoMovimiento,
            Integer cantidad,
            String motivo,
            String referencia
    );

}