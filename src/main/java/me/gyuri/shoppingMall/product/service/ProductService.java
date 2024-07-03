package me.gyuri.shoppingMall.product.service;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.product.domain.Product;
import me.gyuri.shoppingMall.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(CreateProductRequest request) {
        return productRepository.save(request.toEntity());
    }
}
