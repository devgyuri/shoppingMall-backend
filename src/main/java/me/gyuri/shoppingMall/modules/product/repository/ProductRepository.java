package me.gyuri.shoppingMall.modules.product.repository;

import me.gyuri.shoppingMall.modules.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
