package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.entity.MovementType;
import com.cgb.coffeegourmetb.service.interfaces.InventoryTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cgb.coffeegourmetb.entity.InventoryMovement;
import com.cgb.coffeegourmetb.entity.MovementType;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.repository.InventoryMovementRepository;
import com.cgb.coffeegourmetb.repository.InventoryRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.entity.Inventory;

import java.time.LocalDateTime;

@Service
@Transactional
public class InventoryTransactionServiceImpl
        implements InventoryTransactionService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public InventoryTransactionServiceImpl(
            InventoryRepository inventoryRepository,
            InventoryMovementRepository movementRepository,
            ProductRepository productRepository,
            UserRepository userRepository) {

        this.inventoryRepository = inventoryRepository;
        this.movementRepository = movementRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void processMovement(
            Long productoId,
            Long usuarioId,
            MovementType tipoMovimiento,
            Integer cantidad,
            String motivo,
            String referencia) {

        Product product = productRepository.findById(productoId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado."));

        User user = userRepository.findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado."));

        Inventory inventory = inventoryRepository
                .findByProductoId(product.getId())
                .orElseGet(() -> {

                    Inventory inv = new Inventory();

                    inv.setProducto(product);
                    inv.setCantidadActual(0);

                    return inventoryRepository.save(inv);

                });

        switch (tipoMovimiento) {

            case ENTRADA ->

                    inventory.setCantidadActual(
                            inventory.getCantidadActual() + cantidad);

            case SALIDA -> {

                if (inventory.getCantidadActual() < cantidad) {

                    throw new BusinessException(
                            "Stock insuficiente.");

                }

                inventory.setCantidadActual(
                        inventory.getCantidadActual() - cantidad);

            }

            case AJUSTE ->

                    inventory.setCantidadActual(cantidad);

        }

        inventoryRepository.save(inventory);

        InventoryMovement movement = new InventoryMovement();

        movement.setProducto(product);
        movement.setUsuario(user);
        movement.setTipoMovimiento(tipoMovimiento);
        movement.setCantidad(cantidad);
        movement.setMotivo(motivo);
        movement.setReferencia(referencia);
        movement.setFecha(LocalDateTime.now());

        movementRepository.save(movement);

    }

}