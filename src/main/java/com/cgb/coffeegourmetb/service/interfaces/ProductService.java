package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.dto.response.ProductPosResponse;

import java.util.List;

public interface ProductService {

    /**
     * Obtiene todos los productos activos.
     */
    List<ProductResponse> findAll();

    /**
     * Obtiene todos los productos inactivos.
     */
    List<ProductResponse> findAllInactive();

    /**
     * Obtiene un producto activo por su id.
     */
    ProductResponse findById(Long id);

    /**
     * Obtiene el catálogo de productos activos con su precio de venta vigente.
     */
    List<ProductPosResponse> findAllForPos();

    /**
     * Crea un producto.
     */
    ProductResponse create(CreateProductRequest request);

    /**
     * Actualiza un producto.
     */
    ProductResponse update(Long id, UpdateProductRequest request);

    /**
     * Activa un producto.
     */
    void activate(Long id);

    /**
     * Desactiva un producto.
     */
    void deactivate(Long id);

    List<ProductResponse> findAllProducts();
}