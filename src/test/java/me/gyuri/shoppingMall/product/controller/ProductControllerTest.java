package me.gyuri.shoppingMall.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.gyuri.shoppingMall.product.domain.Product;
import me.gyuri.shoppingMall.product.dto.CreateProductRequest;
import me.gyuri.shoppingMall.product.repository.ProductRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}