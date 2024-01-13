package com.motuma.security.controller;

import com.motuma.security.exception.DuplicateOrAlreadyExistException;
import com.motuma.security.payload.AuthenticationResponse;
import com.motuma.security.payload.ProductRequest;
import com.motuma.security.payload.ProductResponse;
import com.motuma.security.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProducts(@RequestBody ProductRequest productRequest){
        try {
            return ResponseEntity.ok(productService.createProduct(productRequest));
        }catch (DuplicateOrAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    ProductResponse.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .message(e.getMessage())
                            .status(HttpStatus.CONFLICT)
                            .statusCode(HttpStatus.CONFLICT.value())
                            .build()
            );
    }
}
}
