package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CloseCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.request.OpenCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.response.CashRegisterResponse;

import java.util.List;

/**
 * Interfaz de servicio para la gestión de cajas.
 */
public interface CashRegisterService {

    /**
     * Consulta todas las cajas registradas.
     *
     * @return lista de cajas.
     */
    List<CashRegisterResponse> findAll();

    /**
     * Consulta una caja por su identificador.
     *
     * @param id identificador de la caja.
     * @return información de la caja.
     */
    CashRegisterResponse findById(Long id);

    /**
     * Abre una nueva caja.
     *
     * @param request información requerida para la apertura.
     * @return información de la caja creada.
     */
    CashRegisterResponse open(OpenCashRegisterRequest request);

    /**
     * Cierra la caja actualmente abierta.
     *
     * @param request información requerida para el cierre.
     * @return información de la caja cerrada.
     */
    CashRegisterResponse close(
            Long id,
            CloseCashRegisterRequest request);

    CashRegisterResponse findOpen();

    List<CashRegisterResponse> findClosed();
}