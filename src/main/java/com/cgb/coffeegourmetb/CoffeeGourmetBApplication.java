package com.cgb.coffeegourmetb;

import com.cgb.coffeegourmetb.config.ProductImageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProductImageProperties.class)
public class CoffeeGourmetBApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeGourmetBApplication.class, args);
    }

}