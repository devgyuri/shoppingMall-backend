package me.gyuri.shoppingMall.modules.product.service;

import lombok.RequiredArgsConstructor;
import me.gyuri.shoppingMall.modules.product.domain.Product;
import me.gyuri.shoppingMall.modules.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(CreateProductRequest request) {
        return productRepository.save(request.toEntity());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }
}
