package me.gyuri.shoppingMall.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gyuri.shoppingMall.modules.product.domain.Product;
import me.gyuri.shoppingMall.modules.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.modules.product.dto.UpdateProductRequest;
import me.gyuri.shoppingMall.modules.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        productRepository.deleteAll();
    }

    @DisplayName("createProduct: 상품 추가에 성공한다.")
    @Test
    public void createProduct() throws Exception {
        // given
        final String url = "/api/products";
        final String name = "product1";
        final int price = 3000;
        final int quantity = 20;
        final CreateProductRequest userRequest = new CreateProductRequest(name, price, quantity);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Product> products = productRepository.findAll();

        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getName()).isEqualTo(name);
        assertThat(products.get(0).getPrice()).isEqualTo(price);
        assertThat(products.get(0).getQuantity()).isEqualTo(quantity);
    }

    @DisplayName("fetchProducts: 상품 목록 조회에 성공한다.")
    @Test
    public void fetchProducts() throws Exception {
        // given
        final String url = "/api/products";
        final String name = "product1";
        final int price = 3000;
        final int quantity = 100;

        productRepository.save(Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].price").value(price))
                .andExpect(jsonPath("$[0].quantity").value(quantity));
    }

    @DisplayName("fetchProduct: 상품 조회에 성공한다.")
    @Test
    public void fetchProduct() throws Exception {
        // given
        final String url = "/api/products/{id}";
        final String name = "product1";
        final int price = 6000;
        final int quantity = 70;

        Product savedProduct = productRepository.save(Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedProduct.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.quantity").value(quantity));
    }

    @DisplayName("deleteProduct: 상품 삭제에 성공한다.")
    @Test
    public void deleteProduct() throws Exception {
        // given
        final String url = "/api/products/{id}";
        final String name = "product3";
        final int price = 5500;
        final int quantity = 55;

        Product savedProduct = productRepository.save(Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(delete(url, savedProduct.getId()))
                .andExpect(status().isOk());

        // then
        List<Product> products = productRepository.findAll();

        assertThat(products).isEmpty();
    }

    @DisplayName("updateProduct: 상품 수정에 성공한다.")
    @Test
    public void updateProduct() throws Exception {
        // given
        final String url = "/api/products/{id}";
        final String name = "product4";
        final int price = 100;
        final int quantity = 3;

        Product savedProduct = productRepository.save(Product.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build());

        final String newName = "new Product";
        final int newPrice = 10000;
        final int newQuantity = 100;

        UpdateProductRequest request = new UpdateProductRequest(newName, newPrice, newQuantity);

        // when
        final ResultActions resultActions = mockMvc.perform(put(url, savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk());

        Product product = productRepository.findById(savedProduct.getId()).get();

        assertThat(product.getName()).isEqualTo(newName);
        assertThat(product.getPrice()).isEqualTo(newPrice);
        assertThat(product.getQuantity()).isEqualTo(newQuantity);
    }
}