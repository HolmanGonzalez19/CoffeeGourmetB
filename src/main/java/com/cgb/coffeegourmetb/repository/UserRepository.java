package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.dto.response.OperatorResponse;
import com.cgb.coffeegourmetb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndActivoTrue(Long id);

    Optional<User> findByUsuario(String usuario);

    Optional<User> findByUsuarioAndActivoTrue(String usuario);

    boolean existsByUsuario(String usuario);

    boolean existsByUsuarioAndIdNot(String usuario, Long id);

    List<User> findByActivoTrue();

    List<User> findByActivoFalse();

    @Query("""
        SELECT new com.cgb.coffeegourmetb.dto.response.OperatorResponse(
                u.id,
                u.nombre,
                u.usuario
            )
            FROM User u
            WHERE u.activo = true
            AND u.pinHash IS NOT NULL
            AND u.pinHash <> ''
            ORDER BY u.nombre ASC
    """)
    List<OperatorResponse> findActiveOperators();
}