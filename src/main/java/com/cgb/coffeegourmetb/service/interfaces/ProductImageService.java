package com.cgb.coffeegourmetb.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ProductImageService {

    String save(MultipartFile file);

    void delete(String fileName);
}