package com.cgb.coffeegourmetb.controller;

import com.cgb.coffeegourmetb.config.ProductImageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/products/images")
public class ProductImageController {

    private final ProductImageProperties properties;

    public ProductImageController(
            ProductImageProperties properties) {

        this.properties = properties;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(
            @PathVariable String fileName) {

        try {

            Path directory = Paths
                    .get(properties.getProductsDirectory())
                    .toAbsolutePath()
                    .normalize();

            Path file =
                    directory.resolve(fileName).normalize();

            if (!file.getParent().equals(directory)) {
                return ResponseEntity.badRequest().build();
            }

            Resource resource =
                    new UrlResource(file.toUri());

            if (!resource.exists()
                    || !resource.isReadable()) {

                return ResponseEntity.notFound().build();
            }

            MediaType mediaType =
                    MediaType.APPLICATION_OCTET_STREAM;

            String contentType =
                    Files.probeContentType(file);

            if (contentType != null) {
                mediaType =
                        MediaType.parseMediaType(contentType);
            }

            return ResponseEntity
                    .ok()
                    .contentType(mediaType)
                    .body(resource);

        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }
}