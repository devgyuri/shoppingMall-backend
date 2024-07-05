package me.gyuri.shoppingMall.modules.product.dto;

import lombok.Getter;
import me.gyuri.shoppingMall.modules.product.domain.Product;

@Getter
public class ProductResponse {
    private final String name;
    private final int price;
    private final int quantity;

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
