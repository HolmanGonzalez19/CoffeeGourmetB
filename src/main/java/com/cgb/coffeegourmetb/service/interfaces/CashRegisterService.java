package com.cgb.coffeegourmetb.service.interfaces;

import com.cgb.coffeegourmetb.dto.request.CloseCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.request.OpenCashRegisterRequest;
import com.cgb.coffeegourmetb.dto.response.CashRegisterResponse;
import com.cgb.coffeegourmetb.dto.response.CashRegisterStatusResponse;
import com.cgb.coffeegourmetb.entity.CashRegister;

import java.util.List;
import java.util.Optional;

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
     * @return información completa de la caja.
     */
    CashRegisterResponse findById(Long id);

    /**
     * Abre una nueva caja.
     *
     * @param request información requerida para la apertura.
     * @return información de la caja creada.
     */
    CashRegisterResponse open(
            OpenCashRegisterRequest request);

    /**
     * Cierra una caja.
     *
     * @param id identificador de la caja.
     * @param request información requerida para el cierre.
     * @return información de la caja cerrada.
     */
    CashRegisterResponse close(
            Long id,
            CloseCashRegisterRequest request);

    /**
     * Consulta únicamente el estado de la caja actual.
     *
     * Operación pública.
     *
     * @return estado actual de la caja.
     */
    CashRegisterStatusResponse findOpen();

    /**
     * Consulta la caja abierta con información completa.
     *
     * Operación administrativa.
     *
     * @return información completa de la caja abierta.
     */
    CashRegisterResponse findOpenAdministrative();

    /**
     * Consulta todas las cajas cerradas.
     *
     * Operación administrativa.
     *
     * @return lista de cajas cerradas.
     */
    List<CashRegisterResponse> findClosed();

    /**
     * Obtiene la caja actualmente abierta para operaciones
     * internas del backend.
     *
     * @return caja abierta, si existe.
     */
    Optional<CashRegister> obtenerCajaAbierta();
}