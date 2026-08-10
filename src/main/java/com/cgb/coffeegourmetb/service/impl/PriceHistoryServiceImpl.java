package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.dto.request.CreatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.request.UpdatePriceHistoryRequest;
import com.cgb.coffeegourmetb.dto.response.PriceHistoryResponse;
import com.cgb.coffeegourmetb.entity.PriceHistory;
import com.cgb.coffeegourmetb.entity.Product;
import com.cgb.coffeegourmetb.exception.ResourceNotFoundException;
import com.cgb.coffeegourmetb.mapper.PriceHistoryMapper;
import com.cgb.coffeegourmetb.repository.PriceHistoryRepository;
import com.cgb.coffeegourmetb.repository.ProductRepository;
import com.cgb.coffeegourmetb.service.interfaces.PriceHistoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Service
@Transactional
public class PriceHistoryServiceImpl implements PriceHistoryService {

    private final PriceHistoryRepository repository;
    private final ProductRepository productRepository;
    private final PriceHistoryMapper mapper;

    public PriceHistoryServiceImpl(
            PriceHistoryRepository repository,
            ProductRepository productRepository,
            PriceHistoryMapper mapper) {

        this.repository = repository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PriceHistoryResponse> findAll() {

        return repository.findByActivoTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<PriceHistoryResponse> findAllInactive() {

        return repository.findByActivoFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PriceHistoryResponse findById(Long id) {

        return mapper.toResponse(findActiveHistory(id));
    }

    @Override
    public List<PriceHistoryResponse> findByProduct(Long productId) {

        return repository.findByProductoIdOrderByFechaInicioDesc(productId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PriceHistoryResponse create(CreatePriceHistoryRequest request) {

        Product product = findProduct(request.getProductoId());

        repository.findByProductoIdAndActivoTrue(product.getId())
                .ifPresent(price -> {

                    price.setActivo(false);
                    price.setFechaFin(LocalDateTime.now());

                    repository.save(price);
                });

        PriceHistory history = mapper.toEntity(request, product);

        PriceHistory saved = repository.save(history);

        return mapper.toResponse(saved);
    }

    @Override
    public PriceHistoryResponse update(Long id,
                                       UpdatePriceHistoryRequest request) {

        PriceHistory history = findHistory(id);

        Product product = findProduct(request.getProductoId());

        mapper.updateEntity(request, history, product);

        PriceHistory updated = repository.save(history);

        return mapper.toResponse(updated);
    }

    @Override
    public void activate(Long id) {

        PriceHistory history = findHistory(id);

        repository.findByProductoIdAndActivoTrue(
                        history.getProducto().getId())
                .ifPresent(price -> {

                    if (!price.getId().equals(id)) {

                        price.setActivo(false);
                        price.setFechaFin(LocalDateTime.now());

                        repository.save(price);
                    }
                });

        try {
            history.setActivo(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        history.setFechaFin(null);

        repository.save(history);
    }

    @Override
    public void deactivate(Long id) {

        PriceHistory history = findHistory(id);

        history.setActivo(false);

        history.setFechaFin(LocalDateTime.now());

        repository.save(history);
    }

    @Override
    public void updatePurchasePrice(Long productoId,
                                    BigDecimal nuevoPrecioCompra) {

        Product product = findProduct(productoId);

        PriceHistory currentPrice = repository
                .findByProductoIdAndActivoTrue(productoId)
                .orElse(null);

        /*
         * Primera compra del producto.
         */
        if (currentPrice == null) {

            PriceHistory history = new PriceHistory();

            history.setProducto(product);

            history.setPrecioCompra(nuevoPrecioCompra);

            history.setPrecioVenta(BigDecimal.ZERO);

            history.setFechaInicio(LocalDateTime.now());

            history.setActivo(true);

            repository.save(history);

            return;
        }

        /*
         * Si el precio no cambió,
         * no hacemos nada.
         */
        if (currentPrice.getPrecioCompra()
                .compareTo(nuevoPrecioCompra) == 0) {

            return;
        }

        /*
         * Cerramos el precio anterior.
         */
        currentPrice.setActivo(false);

        currentPrice.setFechaFin(LocalDateTime.now());

        repository.save(currentPrice);

        /*
         * Creamos el nuevo precio.
         */
        PriceHistory newPrice = new PriceHistory();

        newPrice.setProducto(product);

        newPrice.setPrecioCompra(nuevoPrecioCompra);

        /*
         * Conservamos el precio de venta.
         */
        newPrice.setPrecioVenta(
                currentPrice.getPrecioVenta());

        newPrice.setFechaInicio(LocalDateTime.now());

        newPrice.setActivo(true);

        repository.save(newPrice);
    }

    private Product findProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un producto con id: " + id));
    }

    private PriceHistory findHistory(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un historial de precios con id: " + id));
    }

    private PriceHistory findActiveHistory(Long id) {

        return repository.findByIdAndActivoTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un historial de precios activo con id: " + id));
    }

}