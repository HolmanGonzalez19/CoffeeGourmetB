package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio encargado del acceso a datos de los métodos de pago.
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    /**
     * Busca un método de pago activo por su identificador.
     *
     * @param id Identificador del método de pago.
     * @return Método de pago encontrado.
     */
    Optional<PaymentMethod> findByIdAndActivoTrue(Long id);

    /**
     * Busca un método de pago por su nombre.
     *
     * @param nombre Nombre del método de pago.
     * @return Método de pago encontrado.
     */
    Optional<PaymentMethod> findByNombre(String nombre);

    /**
     * Verifica si existe un método de pago con el nombre indicado.
     *
     * @param nombre Nombre del método de pago.
     * @return true si existe.
     */
    boolean existsByNombre(String nombre);

    /**
     * Verifica si existe otro método de pago con el mismo nombre.
     *
     * @param nombre Nombre del método de pago.
     * @param id Identificador del método de pago actual.
     * @return true si existe otro registro con ese nombre.
     */
    boolean existsByNombreAndIdNot(String nombre, Long id);

    /**
     * Obtiene todos los métodos de pago activos.
     *
     * @return Lista de métodos de pago activos.
     */
    List<PaymentMethod> findByActivoTrue();

    /**
     * Obtiene todos los métodos de pago inactivos.
     *
     * @return Lista de métodos de pago inactivos.
     */
    List<PaymentMethod> findByActivoFalse();

}