package me.gyuri.shoppingMall.modules.product.controller;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.modules.product.domain.Product;
import me.gyuri.shoppingMall.modules.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.modules.product.dto.ProductResponse;
import me.gyuri.shoppingMall.modules.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
        Product savedProduct = productService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> fetchProducts() {
        List<ProductResponse> products = productService.findAll()
                .stream()
                .map(ProductResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(products);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> fetchProduct(@PathVariable(value = "id") long id) {
        Product product = productService.findById(id);

        return ResponseEntity.ok()
                .body(new ProductResponse(product));
    }
}
