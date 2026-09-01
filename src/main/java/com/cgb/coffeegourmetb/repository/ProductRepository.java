package com.cgb.coffeegourmetb.repository;

import com.cgb.coffeegourmetb.dto.response.ProductPosResponse;
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

    List<Product> findByActivoTrueOrderByNombreAsc();

    List<Product> findByActivoFalse();

    boolean existsByCodigoBarras(String codigoBarras);

    boolean existsByCodigoBarrasAndIdNot(
            String codigoBarras,
            Long id);

    List<Product> findAllByOrderByNombreAsc();

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

    @Query("""
        SELECT p
        FROM Product p
        LEFT JOIN FETCH PriceHistory ph
            ON ph.producto = p
            AND ph.activo = true
        WHERE p.activo = true
        ORDER BY p.nombre
        """)
    List<Product> findAllActiveWithCurrentPrice();

    @Query("""
    SELECT p, ph.precioCompra, ph.precioVenta
    FROM Product p
    LEFT JOIN PriceHistory ph
        ON ph.producto = p
        AND ph.activo = true
    WHERE p.activo = true
    ORDER BY p.nombre ASC
    """)
    List<Object[]> findAllActiveWithCurrentPriceData();

    @Query("""
        SELECT new com.cgb.coffeegourmetb.dto.response.ProductPosResponse(
                p.id,
                p.codigo,
                p.codigoBarras,
                p.nombre,
                c.id,
                c.nombre,
                p.tipoProducto,
                ph.precioVenta
            )
            FROM Product p
            JOIN p.categoria c
            JOIN PriceHistory ph
                ON ph.producto = p
                AND ph.activo = true
                AND ph.precioVenta > 0
            WHERE p.activo = true
            ORDER BY p.nombre
        """)
    List<ProductPosResponse> findAllForPos();

    @Query("""
    SELECT p, ph.precioCompra, ph.precioVenta
    FROM Product p
    LEFT JOIN PriceHistory ph
        ON ph.producto = p
        AND ph.activo = true
    WHERE p.activo = false
    ORDER BY p.nombre ASC
    """)
    List<Object[]> findAllInactiveWithCurrentPriceData();


    @Query("""
    SELECT p, ph.precioCompra, ph.precioVenta
    FROM Product p
    LEFT JOIN PriceHistory ph
        ON ph.producto = p
        AND ph.activo = true
    ORDER BY p.nombre ASC
    """)
    List<Object[]> findAllWithCurrentPriceData();


    @Query(
            value = """
        SELECT COALESCE(MAX(id), 0) + 1
        FROM coffeegourmet.productos
        """,
            nativeQuery = true
    )
    Long findNextId();


    @Query("""
    SELECT p, ph.precioCompra, ph.precioVenta
    FROM Product p
    LEFT JOIN PriceHistory ph
        ON ph.producto = p
        AND ph.activo = true
    WHERE p.id = :id
    AND p.activo = true
    """)
    Optional<Object[]> findByIdWithCurrentPriceData(Long id);
}