package com.motuma.security.services.impl;

import com.motuma.security.model.Product;
import com.motuma.security.payload.AuthenticationResponse;
import com.motuma.security.payload.ProductRequest;
import com.motuma.security.payload.ProductResponse;
import com.motuma.security.repository.ProductRepository;
import com.motuma.security.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
//        TODO: You can save also using instantiating the object
//        Product productData= new Product();
        var addProduct= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stockQuantity(productRequest.getStockQuantity())
                .category(productRequest.getCategory())
                .build();
        productRepository.save(addProduct);
        return ProductResponse.builder()
                .name(productRequest.getName())
                .message("Product created Successfully")
                .timeStamp(LocalDateTime.now().toString())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
