package me.gyuri.shoppingMall.product.controller;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.product.domain.Product;
import me.gyuri.shoppingMall.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product savedProduct = productService.createProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProduct);
    }
}
