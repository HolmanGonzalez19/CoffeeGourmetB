package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.entity.Category;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.exception.BusinessException;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.ProductMapper;
import com.cgb.coffeegourmetb.repository.CategoryRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponse> findAll() {

        return productRepository.findByActivoTrue()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findAllInactive() {

        return productRepository.findByActivoFalse()
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {

        Product product = productRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un producto activo con id: " + id));

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse create(CreateProductRequest request) {

        validateCode(request.getCodigo());
        validateBarcode(request.getCodigoBarras());
        validateName(request.getNombre());

        Category category = findCategory(request.getCategoriaId());

        Product product = productMapper.toEntity(request, category);

        Product saved = productRepository.save(product);

        return productMapper.toResponse(saved);
    }

    @Override
    public ProductResponse update(Long id,
                                  UpdateProductRequest request) {

        Product product = findProduct(id);

        validateCodeForUpdate(request.getCodigo(), id);
        validateBarcodeForUpdate(request.getCodigoBarras(), id);
        validateNameForUpdate(request.getNombre(), id);

        Category category = findCategory(request.getCategoriaId());

        productMapper.updateEntity(request, product, category);

        Product updated = productRepository.save(product);

        return productMapper.toResponse(updated);
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

    // ==========================
    // Métodos privados
    // ==========================

    private Product findProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un producto con id: " + id));
    }

    private Category findCategory(Long id) {

        return categoryRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe una categoría activa con id: " + id));
    }

    private void validateCode(String codigo) {

        if (productRepository.existsByCodigo(codigo)) {
            throw new BusinessException(
                    "Ya existe un producto con el código: " + codigo);
        }
    }

    private void validateCodeForUpdate(String codigo,
                                       Long id) {

        if (productRepository.existsByCodigoAndIdNot(codigo, id)) {
            throw new BusinessException(
                    "Ya existe otro producto con el código: " + codigo);
        }
    }

    private void validateBarcode(String codigoBarras) {

        if (codigoBarras != null
                && !codigoBarras.isBlank()
                && productRepository.existsByCodigoBarras(codigoBarras)) {

            throw new BusinessException(
                    "Ya existe un producto con el código de barras: " + codigoBarras);
        }
    }

    private void validateBarcodeForUpdate(String codigoBarras,
                                          Long id) {

        if (codigoBarras != null
                && !codigoBarras.isBlank()
                && productRepository.existsByCodigoBarrasAndIdNot(codigoBarras, id)) {

            throw new BusinessException(
                    "Ya existe otro producto con el código de barras: " + codigoBarras);
        }
    }

    private void validateName(String nombre) {

        if (productRepository.existsByNombre(nombre)) {
            throw new BusinessException(
                    "Ya existe un producto con el nombre: " + nombre);
        }
    }

    private void validateNameForUpdate(String nombre,
                                       Long id) {

        if (productRepository.existsByNombreAndIdNot(nombre, id)) {
            throw new BusinessException(
                    "Ya existe otro producto con el nombre: " + nombre);
        }
    }

}