package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.request.CreatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.response.PriceHistoryResponse;
import com.cgb.coffeegourmetb.entity.PriceHistory;
import com.cgb.coffeegourmetb.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper encargado de convertir objetos relacionados con PriceHistory.
 */
@Component
public class PriceHistoryMapper {

    /**
     * Convierte un CreatePriceHistoryRequest en una entidad PriceHistory.
     */
    public PriceHistory toEntity(CreatePriceHistoryRequest request,
                                 Product product) {

        PriceHistory history = new PriceHistory();

        history.setProducto(product);
        history.setPrecioCompra(request.getPrecioCompra());
        history.setPrecioVenta(request.getPrecioVenta());
        history.setFechaInicio(request.getFechaInicio());
        history.setFechaFin(null);
        history.setActivo(true);

        return history;
    }

    /**
     * Convierte una entidad PriceHistory en PriceHistoryResponse.
     */
    public PriceHistoryResponse toResponse(PriceHistory history) {

        PriceHistoryResponse response = new PriceHistoryResponse();

        response.setId(history.getId());

        response.setProductoId(history.getProducto().getId());
        response.setProductoNombre(history.getProducto().getNombre());

        response.setPrecioCompra(history.getPrecioCompra());
        response.setPrecioVenta(history.getPrecioVenta());

        response.setFechaInicio(history.getFechaInicio());
        response.setFechaFin(history.getFechaFin());

        response.setActivo(history.getActivo());

        response.setFechaCreacion(history.getFechaCreacion());
        response.setFechaActualizacion(history.getFechaActualizacion());

        return response;
    }

    /**
     * Actualiza una entidad existente.
     */
    public void updateEntity(UpdatePriceHistoryRequest request,
                             PriceHistory history,
                             Product product) {

        history.setProducto(product);
        history.setPrecioCompra(request.getPrecioCompra());
        history.setPrecioVenta(request.getPrecioVenta());
        history.setFechaInicio(request.getFechaInicio());
        history.setFechaFin(request.getFechaFin());
        history.setActivo(request.getActivo());
    }

}