package com.cgb.coffeegourmetb.service.impl;

import com.cgb.coffeegourmetb.config.ProductImageProperties;
import com.cgb.coffeegourmetb.service.interfaces.ProductImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            ".jpg",
            ".jpeg",
            ".png",
            ".webp"
    );

    private final ProductImageProperties properties;

    public ProductImageServiceImpl(
            ProductImageProperties properties) {

        this.properties = properties;
    }

    @Override
    public String save(MultipartFile file) {

        validate(file);

        try {

            Path directory = Paths
                    .get(properties.getProductsDirectory())
                    .toAbsolutePath()
                    .normalize();

            Files.createDirectories(directory);

            String extension =
                    getExtension(file.getOriginalFilename());

            String fileName =
                    UUID.randomUUID() + extension;

            Path target =
                    directory.resolve(fileName).normalize();

            if (!target.getParent().equals(directory)) {
                throw new IllegalArgumentException(
                        "Nombre de archivo no válido.");
            }

            Files.copy(
                    file.getInputStream(),
                    target);

            return fileName;

        } catch (IOException e) {

            throw new RuntimeException(
                    "No fue posible guardar la imagen del producto.",
                    e);
        }
    }

    @Override
    public void delete(String fileName) {

        if (fileName == null || fileName.isBlank()) {
            return;
        }

        try {

            Path directory = Paths
                    .get(properties.getProductsDirectory())
                    .toAbsolutePath()
                    .normalize();

            Path file =
                    directory.resolve(fileName).normalize();

            if (!file.getParent().equals(directory)) {
                throw new IllegalArgumentException(
                        "Nombre de archivo no válido.");
            }

            Files.deleteIfExists(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "No fue posible eliminar la imagen del producto.",
                    e);
        }
    }

    private void validate(MultipartFile file) {

        if (file == null || file.isEmpty()) {

            throw new IllegalArgumentException(
                    "La imagen del producto no puede estar vacía.");
        }

        if (file.getSize() > MAX_FILE_SIZE) {

            throw new IllegalArgumentException(
                    "La imagen no puede superar los 5 MB.");
        }

        if (!ALLOWED_CONTENT_TYPES.contains(
                file.getContentType())) {

            throw new IllegalArgumentException(
                    "El formato de imagen no es válido. "
                            + "Use JPG, PNG o WEBP.");
        }
    }

    private String getExtension(String fileName) {

        if (fileName == null || fileName.isBlank()) {

            throw new IllegalArgumentException(
                    "La imagen no tiene un nombre válido.");
        }

        int index = fileName.lastIndexOf('.');

        if (index < 0) {

            throw new IllegalArgumentException(
                    "La imagen no tiene una extensión válida.");
        }

        String extension =
                fileName.substring(index).toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(extension)) {

            throw new IllegalArgumentException(
                    "La extensión de la imagen no es válida.");
        }

        return extension;
    }
}