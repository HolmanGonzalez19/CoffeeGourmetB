package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.entity.Category;
import com.cgb.coffeegourmetb.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    /**
     * Convierte un CreateProductRequest en Product.
     */
    public Product toEntity(CreateProductRequest request, Category category) {

        Product product = new Product();

        product.setCodigo(request.getCodigo());
        product.setCodigoBarras(request.getCodigoBarras());
        product.setNombre(request.getNombre());
        product.setCategoria(category);
        product.setDescripcion(request.getDescripcion());
        product.setTipoProducto(request.getTipoProducto());
        product.setStockMinimo(request.getStockMinimo());
        product.setActivo(true);

        return product;
    }

    /**
     * Convierte Product en ProductResponse.
     */
    public ProductResponse toResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setId(product.getId());

        response.setCategoriaId(product.getCategoria().getId());
        response.setCategoriaNombre(product.getCategoria().getNombre());

        response.setCodigo(product.getCodigo());
        response.setCodigoBarras(product.getCodigoBarras());
        response.setNombre(product.getNombre());
        response.setDescripcion(product.getDescripcion());

        response.setTipoProducto(product.getTipoProducto());

        response.setStockMinimo(product.getStockMinimo());

        response.setActivo(product.getActivo());

        response.setFechaCreacion(product.getFechaCreacion());
        response.setFechaActualizacion(product.getFechaActualizacion());

        return response;
    }

    /**
     * Actualiza una entidad existente.
     */
    public void updateEntity(UpdateProductRequest request,
                             Product product,
                             Category category) {

        product.setCodigo(request.getCodigo());
        product.setCodigoBarras(request.getCodigoBarras());
        product.setNombre(request.getNombre());
        product.setCategoria(category);
        product.setDescripcion(request.getDescripcion());
        product.setTipoProducto(request.getTipoProducto());
        product.setStockMinimo(request.getStockMinimo());
        product.setActivo(request.getActivo());
    }

}