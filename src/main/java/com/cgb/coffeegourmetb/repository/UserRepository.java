package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndActivoTrue(Long id);

    Optional<User> findByUsuario(String usuario);

    boolean existsByUsuario(String usuario);

    boolean existsByUsuarioAndIdNot(String usuario, Long id);

    List<User> findByActivoTrue();

    List<User> findByActivoFalse();

}