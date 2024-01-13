package com.motuma.security.services;

import com.motuma.security.payload.ProductRequest;
import com.motuma.security.payload.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
}
