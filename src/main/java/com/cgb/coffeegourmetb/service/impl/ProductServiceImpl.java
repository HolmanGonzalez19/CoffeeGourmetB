package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductPosResponse;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.entity.Category;
import com.cgb.coffeegourmetb.entity.Inventory;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.ProductMapper;
import com.cgb.coffeegourmetb.repository.CategoryRepository;
import com.cgb.coffeegourmetb.repository.InventoryRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.cgb.coffeegourmetb.entity.PriceHistory;
import com.cgb.coffeegourmetb.repository.PriceHistoryRepository;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductMapper productMapper;
    private final PriceHistoryRepository priceHistoryRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            InventoryRepository inventoryRepository,
            PriceHistoryRepository priceHistoryRepository,
            ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.inventoryRepository = inventoryRepository;
        this.priceHistoryRepository = priceHistoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> findAll() {

        return productRepository
                .findAllActiveWithCurrentPriceData()
                .stream()
                .map(row -> {

                    Product product = (Product) row[0];

                    BigDecimal precioCompra =
                            (BigDecimal) row[1];

                    BigDecimal precioVenta =
                            (BigDecimal) row[2];


                    return productMapper.toResponse(
                            product,
                            precioCompra,
                            precioVenta);
                })
                .toList();
    }

    @Override
    public List<ProductResponse> findAllInactive() {

        return productRepository
                .findAllInactiveWithCurrentPriceData()
                .stream()
                .map(row -> {

                    Product product = (Product) row[0];

                    BigDecimal precioCompra =
                            (BigDecimal) row[1];

                    BigDecimal precioVenta =
                            (BigDecimal) row[2];

                    return productMapper.toResponse(
                            product,
                            precioCompra,
                            precioVenta);
                })
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {

        Object[] row = productRepository
                .findByIdWithCurrentPriceData(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un producto activo con id: "
                                        + id));

        Product product =
                (Product) row[0];

        BigDecimal precioCompra =
                (BigDecimal) row[1];

        BigDecimal precioVenta =
                (BigDecimal) row[2];

        return productMapper.toResponse(
                product,
                precioCompra,
                precioVenta);
    }

    @Override
    public ProductResponse create(
            CreateProductRequest request) {

        validateBarcode(request.getCodigoBarras());
        validateName(request.getNombre());

        Category category =
                findCategory(request.getCategoriaId());

        // ==========================================
        // GENERAR CÓDIGO AUTOMÁTICAMENTE
        // ==========================================

        String codigo = generarCodigoProducto();

        // ==========================================
        // CREAR PRODUCTO
        // ==========================================

        Product product =
                productMapper.toEntity(
                        request,
                        category);

        product.setCodigo(codigo);

        Product savedProduct =
                productRepository.save(product);

        // ==========================================
        // CREAR INVENTARIO INICIAL
        // ==========================================

        Inventory inventory = new Inventory();

        inventory.setProducto(savedProduct);
        inventory.setCantidadActual(0);

        inventoryRepository.save(inventory);

        // ==========================================
        // CREAR PRECIO INICIAL
        // ==========================================
        if (
            request.getPrecioCompra() != null
            && request.getPrecioCompra().compareTo(BigDecimal.ZERO) > 0
            && request.getPrecioVenta() != null
            && request.getPrecioVenta().compareTo(BigDecimal.ZERO) > 0
        ) {
            PriceHistory priceHistory =
                    new PriceHistory();

            priceHistory.setProducto(savedProduct);

            priceHistory.setPrecioCompra(
                    request.getPrecioCompra());

            priceHistory.setPrecioVenta(
                    request.getPrecioVenta());

            priceHistory.setFechaInicio(
                    LocalDateTime.now());

            priceHistory.setFechaFin(null);

            priceHistory.setActivo(true);

            priceHistoryRepository.save(priceHistory);
        }

        // ==========================================
        // RESPUESTA
        // ==========================================

        return productMapper.toResponse(
                savedProduct,
                request.getPrecioCompra(),
                request.getPrecioVenta());
    }

    @Override
    public ProductResponse update(
            Long id,
            UpdateProductRequest request) {

        Product product = findProduct(id);

        validateBarcodeForUpdate(
                request.getCodigoBarras(),
                id);

        validateNameForUpdate(
                request.getNombre(),
                id);

        Category category =
                findCategory(
                        request.getCategoriaId());

        // ==========================================
        // ACTUALIZAR DATOS DEL PRODUCTO
        // ==========================================

        productMapper.updateEntity(
                request,
                product,
                category);

        Product updated =
                productRepository.save(product);

        // ==========================================
        // ACTUALIZAR HISTORIAL DE PRECIOS
        // ==========================================

        PriceHistory precioActual =
                priceHistoryRepository
                        .findByProductoIdAndActivoTrue(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe un precio activo para el producto con id: "
                                                + id));

        boolean cambioPrecio =
                !precioActual.getPrecioCompra()
                        .equals(request.getPrecioCompra())
                        ||
                        !precioActual.getPrecioVenta()
                                .equals(request.getPrecioVenta());

        if (cambioPrecio) {

            LocalDateTime ahora =
                    LocalDateTime.now();

            // Cerrar precio actual
            precioActual.setFechaFin(ahora);
            precioActual.setActivo(false);

            priceHistoryRepository.save(precioActual);

            // Crear nuevo historial
            PriceHistory nuevoPrecio =
                    new PriceHistory();

            nuevoPrecio.setProducto(updated);

            nuevoPrecio.setPrecioCompra(
                    request.getPrecioCompra());

            nuevoPrecio.setPrecioVenta(
                    request.getPrecioVenta());

            nuevoPrecio.setFechaInicio(ahora);
            nuevoPrecio.setFechaFin(null);
            nuevoPrecio.setActivo(true);

            priceHistoryRepository.save(nuevoPrecio);
        }

        // ==========================================
        // RESPUESTA
        // ==========================================

        return productMapper.toResponse(
                updated,
                request.getPrecioCompra(),
                request.getPrecioVenta());
    }

    @Override
    public void activate(Long id) {

        Product product = findProduct(id);

        product.setActivo(true);

        productRepository.save(product);
    }

    @Override
    public void deactivate(Long id) {

        Product product = findProduct(id);

        product.setActivo(false);

        productRepository.save(product);
    }

    @Override
    public List<ProductPosResponse> findAllForPos() {

        return productRepository.findAllForPos();
    }

    @Override
    public List<ProductResponse> findAllProducts() {

        return productRepository
                .findAllWithCurrentPriceData()
                .stream()
                .map(row -> {

                    Product product = (Product) row[0];

                    BigDecimal precioCompra =
                            (BigDecimal) row[1];

                    BigDecimal precioVenta =
                            (BigDecimal) row[2];

                    return productMapper.toResponse(
                            product,
                            precioCompra,
                            precioVenta);
                })
                .toList();
    }

    @Override
    @Transactional
    public ProductResponse findByCodigoBarras(String codigoBarras) {

        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new BusinessException(
                    "El código de barras es obligatorio.");
        }

        String codigoBarrasNormalizado =
                codigoBarras.trim();

        Product product =
                productRepository
                        .findByCodigoBarras(
                                codigoBarrasNormalizado)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No existe un producto con el código de barras: "
                                                + codigoBarrasNormalizado));

        BigDecimal precioCompra = null;
        BigDecimal precioVenta = null;

        var precioActual =
                priceHistoryRepository
                        .findByProductoIdAndActivoTrue(
                                product.getId());

        if (precioActual.isPresent()) {
            precioCompra =
                    precioActual.get().getPrecioCompra();

            precioVenta =
                    precioActual.get().getPrecioVenta();
        }

        return productMapper.toResponse(
                product,
                precioCompra,
                precioVenta);
    }
    // ==========================
    // Métodos privados
    // ==========================

    private Product findProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un producto con id: "
                                        + id));
    }

    private Category findCategory(Long id) {

        return categoryRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una categoría activa con id: "
                                        + id));
    }

    private void validateCode(String codigo) {

        if (productRepository.existsByCodigo(codigo)) {

            throw new BusinessException(
                    "Ya existe un producto con el código: "
                            + codigo);
        }
    }

    private void validateCodeForUpdate(
            String codigo,
            Long id) {

        if (productRepository
                .existsByCodigoAndIdNot(codigo, id)) {

            throw new BusinessException(
                    "Ya existe otro producto con el código: "
                            + codigo);
        }
    }

    private void validateBarcode(
            String codigoBarras) {

        if (codigoBarras != null
                && !codigoBarras.isBlank()
                && productRepository
                .existsByCodigoBarras(codigoBarras)) {

            throw new BusinessException(
                    "Ya existe un producto con el código de barras: "
                            + codigoBarras);
        }
    }

    private void validateBarcodeForUpdate(
            String codigoBarras,
            Long id) {

        if (codigoBarras != null
                && !codigoBarras.isBlank()
                && productRepository
                .existsByCodigoBarrasAndIdNot(
                        codigoBarras,
                        id)) {

            throw new BusinessException(
                    "Ya existe otro producto con el código de barras: "
                            + codigoBarras);
        }
    }

    private void validateName(String nombre) {

        if (productRepository.existsByNombre(nombre)) {

            throw new BusinessException(
                    "Ya existe un producto con el nombre: "
                            + nombre);
        }
    }

    private void validateNameForUpdate(
            String nombre,
            Long id) {

        if (productRepository
                .existsByNombreAndIdNot(
                        nombre,
                        id)) {

            throw new BusinessException(
                    "Ya existe otro producto con el nombre: "
                            + nombre);
        }
    }
    private String generarCodigoProducto() {

        Long siguienteId =
                productRepository.findNextId();

        return String.format(
                "PROD-%06d",
                siguienteId);
    }
}