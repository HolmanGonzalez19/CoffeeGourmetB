package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio encargado del acceso a datos de los roles.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Busca un rol activo por su identificador.
     *
     * @param id Identificador del rol.
     * @return Rol encontrado.
     */
    Optional<Role> findByIdAndActivoTrue(Long id);

    /**
     * Busca un rol por su nombre.
     *
     * @param nombre Nombre del rol.
     * @return Rol encontrado.
     */
    Optional<Role> findByNombre(String nombre);

    /**
     * Verifica si existe un rol con el nombre indicado.
     *
     * @param nombre Nombre del rol.
     * @return true si existe.
     */
    boolean existsByNombre(String nombre);

    /**
     * Verifica si existe otro rol con el mismo nombre.
     *
     * @param nombre Nombre del rol.
     * @param id     Id del rol actual.
     * @return true si existe otro registro con ese nombre.
     */
    boolean existsByNombreAndIdNot(String nombre, Long id);

    /**
     * Obtiene todos los roles activos.
     *
     * @return Lista de roles activos.
     */
    List<Role> findByActivoTrue();

    /**
     * Obtiene todos los roles Inactivos.
     *
     * @return Lista de roles Inactivos.
     */
    List<Role> findByActivoFalse();

}