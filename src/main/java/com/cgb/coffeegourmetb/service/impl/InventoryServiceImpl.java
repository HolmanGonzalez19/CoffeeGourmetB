package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateInventoryMovementRequest;
import com.cgb.coffeegourmetb.dto.response.InventoryMovementResponse;
import com.cgb.coffeegourmetb.dto.response.InventoryResponse;
import com.cgb.coffeegourmetb.entity.Inventory;
import com.cgb.coffeegourmetb.entity.InventoryMovement;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.entity.User;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.InventoryMapper;
import com.cgb.coffeegourmetb.repository.InventoryMovementRepository;
import com.cgb.coffeegourmetb.repository.InventoryRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.repository.UserRepository;
import com.cgb.coffeegourmetb.service.interfaces.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository movementRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final InventoryMapper mapper;


    public InventoryServiceImpl(
            InventoryRepository inventoryRepository,
            InventoryMovementRepository movementRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            InventoryMapper mapper) {

        this.inventoryRepository = inventoryRepository;
        this.movementRepository = movementRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public List<InventoryResponse> findAll() {

        return inventoryRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    @Override
    public InventoryResponse findByProduct(Long productId) {

        Inventory inventory = inventoryRepository
                .findByProductoId(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El producto no posee inventario."));

        return mapper.toResponse(inventory);
    }


    @Override
    public List<InventoryMovementResponse> movements(Long productId) {

        return movementRepository
                .findByProductoIdOrderByFechaDesc(productId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }


    @Override
    public InventoryMovementResponse createMovement(
            CreateInventoryMovementRequest request) {


        Product product = productRepository.findById(request.getProductoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto no encontrado."));


        User user = userRepository.findById(request.getUsuarioId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado."));


        Inventory inventory =
                inventoryRepository.findByProductoId(product.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "El producto no tiene inventario asociado."));


        switch (request.getTipoMovimiento()) {


            case ENTRADA -> {

                inventory.setCantidadActual(
                        inventory.getCantidadActual()
                                + request.getCantidad()
                );
            }


            case SALIDA -> {

                if (inventory.getCantidadActual()
                        < request.getCantidad()) {

                    throw new BusinessException(
                            "Stock insuficiente.");
                }


                inventory.setCantidadActual(
                        inventory.getCantidadActual()
                                - request.getCantidad()
                );
            }


            case AJUSTE -> {

                inventory.setCantidadActual(
                        request.getCantidad()
                );
            }

        }


        inventoryRepository.save(inventory);



        InventoryMovement movement =
                new InventoryMovement();


        movement.setProducto(product);

        movement.setUsuario(user);

        movement.setTipoMovimiento(
                request.getTipoMovimiento()
        );

        movement.setCantidad(
                request.getCantidad()
        );

        movement.setMotivo(
                request.getMotivo()
        );

        movement.setReferencia(
                request.getReferencia()
        );

        movement.setFecha(
                LocalDateTime.now()
        );


        return mapper.toResponse(
                movementRepository.save(movement)
        );
    }

}