package com.cgb.coffeegourmetb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coffee-gourmet.images")
public class ProductImageProperties {

    private String productsDirectory;

    public String getProductsDirectory() {
        return productsDirectory;
    }

    public void setProductsDirectory(String productsDirectory) {
        this.productsDirectory = productsDirectory;
    }
}