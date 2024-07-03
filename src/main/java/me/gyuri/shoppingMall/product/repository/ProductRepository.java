package me.gyuri.shoppingMall.product.repository;

import me.gyuri.shoppingMall.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
