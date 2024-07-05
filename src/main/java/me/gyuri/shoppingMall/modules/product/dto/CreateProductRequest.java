package me.gyuri.shoppingMall.modules.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.gyuri.shoppingMall.modules.product.domain.Product;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateProductRequest {
    private String name;
    private int price;
    private int quantity;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }
}
