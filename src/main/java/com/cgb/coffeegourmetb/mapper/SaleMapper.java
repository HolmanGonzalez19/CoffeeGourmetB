package com.cgb.coffeegourmetb.mapper;

import com.cgb.coffeegourmetb.dto.response.SaleDetailResponse;
import com.cgb.coffeegourmetb.dto.response.SaleResponse;
import com.cgb.coffeegourmetb.entity.Sale;
import com.cgb.coffeegourmetb.entity.SaleDetail;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

    public SaleDetailResponse toResponse(SaleDetail detail) {

        SaleDetailResponse response = new SaleDetailResponse();

        response.setId(detail.getId());

        response.setProductoId(
                detail.getProducto().getId());

        response.setProductoNombre(
                detail.getProducto().getNombre());

        response.setCantidad(
                detail.getCantidad());

        response.setPrecioUnitario(
                detail.getPrecioUnitario());

        response.setSubtotal(
                detail.getSubtotal());

        return response;
    }

    public SaleResponse toResponse(Sale sale) {

        SaleResponse response = new SaleResponse();

        response.setId(
                sale.getId());

        response.setUsuarioId(
                sale.getUsuario().getId());

        response.setUsuarioNombre(
                sale.getUsuario().getNombre());

        response.setMetodoPagoId(
                sale.getMetodoPago().getId());

        response.setMetodoPagoNombre(
                sale.getMetodoPago().getNombre());

        response.setCajaId(
                sale.getCaja().getId());

        response.setFechaHora(
                sale.getFechaHora());

        response.setTotal(
                sale.getTotal());

        response.setObservacion(
                sale.getObservacion());

        response.setEstado(
                sale.getEstado());

        response.setFechaAnulacion(
                sale.getFechaAnulacion());

        if (sale.getUsuarioAnulacion() != null) {

            response.setUsuarioAnulacionId(
                    sale.getUsuarioAnulacion().getId());

            response.setUsuarioAnulacionNombre(
                    sale.getUsuarioAnulacion().getNombre());
        }

        response.setMotivoAnulacion(
                sale.getMotivoAnulacion());

        response.setFechaCreacion(
                sale.getFechaCreacion());

        response.setFechaActualizacion(
                sale.getFechaActualizacion());

        response.setDetalles(
                sale.getDetalles()
                        .stream()
                        .map(this::toResponse)
                        .toList());

        return response;
    }
}