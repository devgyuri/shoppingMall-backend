package me.gyuri.shoppingMall.modules.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateProductRequest {
    private String name;
    private int price;
    private int quantity;
}
