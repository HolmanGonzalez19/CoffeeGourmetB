package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndActivoTrue(Long id);

    Optional<Product> findByCodigo(String codigo);

    Optional<Product> findByNombre(String nombre);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, Long id);

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

    List<Product> findByActivoTrue();

    List<Product> findByActivoFalse();

    boolean existsByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarrasAndIdNot(String codigoBarras, Long id);

    @Query("""
    SELECT p
    FROM Product p
    WHERE p.activo = true
    AND p.id NOT IN (
        SELECT DISTINCT d.producto.id
        FROM SaleDetail d
        WHERE d.venta.estado =
            com.cgb.coffeegourmetb.enums.SaleStatus.REGISTRADA
    )
    ORDER BY p.nombre
    """)
    List<Product> productosSinVentas();

}