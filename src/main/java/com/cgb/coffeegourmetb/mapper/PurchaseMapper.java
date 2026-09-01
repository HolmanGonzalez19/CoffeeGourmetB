package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.PurchaseDetailResponse;
import com.cgb.coffeegourmetb.dto.response.PurchaseResponse;
import com.cgb.coffeegourmetb.entity.Purchase;
import com.cgb.coffeegourmetb.entity.PurchaseDetail;
import org.springframework.stereotype.Component;

@Component
public class PurchaseMapper {

    public PurchaseDetailResponse toResponse(
            PurchaseDetail detail) {

        PurchaseDetailResponse response =
                new PurchaseDetailResponse();

        response.setId(detail.getId());

        response.setProductoId(
                detail.getProducto().getId());

        response.setProductoNombre(
                detail.getProducto().getNombre());

        response.setCantidad(
                detail.getCantidad());

        response.setPrecioCompra(
                detail.getPrecioCompra());

        response.setSubtotal(
                detail.getSubtotal());

        return response;
    }


    public PurchaseResponse toResponse(
            Purchase purchase) {

        PurchaseResponse response =
                new PurchaseResponse();

        response.setId(
                purchase.getId());

        /*
         * Código interno generado por el sistema.
         */
        response.setCodigoCompra(
                purchase.getCodigoCompra());

        response.setProveedorId(
                purchase.getProveedor().getId());

        response.setProveedorNombre(
                purchase.getProveedor().getNombre());

        response.setUsuarioId(
                purchase.getUsuario().getId());

        response.setUsuarioNombre(
                purchase.getUsuario().getNombre());

        response.setFecha(
                purchase.getFecha());

        response.setTotal(
                purchase.getTotal());

        response.setObservacion(
                purchase.getObservacion());

        /*
         * Estado de la compra.
         */
        response.setEstado(
                purchase.getEstado());

        /*
         * Información de anulación.
         */
        response.setFechaAnulacion(
                purchase.getFechaAnulacion());

        if (purchase.getUsuarioAnulacion() != null) {

            response.setUsuarioAnulacionId(
                    purchase.getUsuarioAnulacion().getId());

            response.setUsuarioAnulacionNombre(
                    purchase.getUsuarioAnulacion().getNombre());
        }

        response.setMotivoAnulacion(
                purchase.getMotivoAnulacion());

        response.setFechaCreacion(
                purchase.getFechaCreacion());

        response.setFechaActualizacion(
                purchase.getFechaActualizacion());

        response.setDetalles(
                purchase.getDetalles()
                        .stream()
                        .map(this::toResponse)
                        .toList());

        return response;
    }
}