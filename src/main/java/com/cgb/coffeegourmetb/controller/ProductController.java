package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.dto.request.CreateProductRequest;
import com.cgb.coffeegourmetb.dto.request.UpdateProductRequest;
import com.cgb.coffeegourmetb.dto.response.ProductResponse;
import com.cgb.coffeegourmetb.dto.response.ProductPosResponse;
import com.cgb.coffeegourmetb.dto.response.ProductTypeResponse;
import com.cgb.coffeegourmetb.enums.ProductType;
import com.cgb.coffeegourmetb.service.interfaces.ProductService;
import com.cgb.coffeegourmetb.util.constants.ApiPaths;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRODUCTS_READ;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRODUCTS_CREATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRODUCTS_UPDATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRODUCTS_ACTIVATE;
import static com.cgb.coffeegourmetb.security.SecurityExpressions.PRODUCTS_DEACTIVATE;


@RestController
@RequestMapping(ApiPaths.PRODUCTS)
@Tag(
        name = "Productos",
        description = "API para la gestión de productos."
)
@SecurityRequirement(name = "bearerAuth")
public class ProductController {

    private final ProductService service;


    public ProductController(
            ProductService service) {

        this.service = service;
    }


    // ============================================================
    // PRODUCTOS ACTIVOS
    // ============================================================

    @Operation(
            summary = "Listar productos activos"
    )
    @GetMapping
    public List<ProductResponse> findAll() {

        return service.findAll();
    }


    // ============================================================
    // PRODUCTOS INACTIVOS
    // ============================================================

    @Operation(
            summary = "Listar productos inactivos"
    )
    @PreAuthorize(PRODUCTS_READ)
    @GetMapping(ApiPaths.PRODUCTS_INACTIVE)
    public List<ProductResponse> findAllInactive() {

        return service.findAllInactive();
    }


    // ============================================================
    // TODOS LOS PRODUCTOS
    // ============================================================

    @Operation(
            summary = "Listar todos los productos"
    )
    @PreAuthorize(PRODUCTS_READ)
    @GetMapping("/all")
    public List<ProductResponse> findAllProducts() {

        return service.findAllProducts();
    }


    // ============================================================
    // CATÁLOGO PARA POS
    // ============================================================

    @Operation(
            summary = "Listar catálogo de productos para POS"
    )
    @GetMapping("/pos")
    public List<ProductPosResponse> findAllForPos() {

        return service.findAllForPos();
    }

    // ============================================================
    // OBTENER PRODUCTOPOR CODIGO DE BARRAS
    // ============================================================

    @Operation(
            summary = "Consultar producto por código de barras",
            description = "Consulta un producto utilizando su código de barras."
    )
    @GetMapping("/barcode/{codigoBarras}")
    public ProductResponse findByCodigoBarras(
            @PathVariable String codigoBarras) {

        return service.findByCodigoBarras(codigoBarras);
    }

    // ============================================================
    // CONSULTAR PRODUCTO POR ID
    // IMPORTANTE:
    // Esta ruta debe estar DESPUÉS de /inactive, /all y /pos.
    // ============================================================

    @Operation(
            summary = "Consultar un producto por ID"
    )
    @GetMapping(ApiPaths.PRODUCTS_BY_ID)
    public ProductResponse findById(
            @PathVariable Long id) {

        return service.findById(id);
    }


    // ============================================================
    // CREAR PRODUCTO
    // ============================================================

    @Operation(
            summary = "Crear un producto"
    )
    @PreAuthorize(PRODUCTS_CREATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Producto creado correctamente"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(
            @Valid
            @RequestBody
            CreateProductRequest request) {

        return service.create(request);
    }


    // ============================================================
    // ACTUALIZAR PRODUCTO
    // ============================================================

    @Operation(
            summary = "Actualizar un producto"
    )
    @PreAuthorize(PRODUCTS_UPDATE)
    @PutMapping(ApiPaths.PRODUCTS_BY_ID)
    public ProductResponse update(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateProductRequest request) {

        return service.update(
                id,
                request
        );
    }


    // ============================================================
    // ACTIVAR PRODUCTO
    // ============================================================

    @Operation(
            summary = "Activar un producto"
    )
    @PreAuthorize(PRODUCTS_ACTIVATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto activado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            )
    })
    @PutMapping(ApiPaths.PRODUCTS_ACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(
            @PathVariable Long id) {

        service.activate(id);
    }


    // ============================================================
    // DESACTIVAR PRODUCTO
    // ============================================================

    @Operation(
            summary = "Desactivar un producto"
    )
    @PreAuthorize(PRODUCTS_DEACTIVATE)
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto desactivado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            )
    })
    @PutMapping(ApiPaths.PRODUCTS_DEACTIVATE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(
            @PathVariable Long id) {

        service.deactivate(id);
    }
    @Operation(
            summary = "Desactivar un producto"
    )
    @PreAuthorize(PRODUCTS_READ)
    @GetMapping(ApiPaths.PRODUCTS_TYPES)
    public List<ProductTypeResponse> getProductTypes() {

        return Arrays.stream(ProductType.values())
                .map(type -> new ProductTypeResponse(
                        type.name(),
                        obtenerNombre(type)
                ))
                .toList();
    }

    private String obtenerNombre(ProductType type) {
        return switch (type) {
            case FABRICADO -> "FABRICADO";
            case COMPRADO -> "COMPRADO";
        };
    }
}
