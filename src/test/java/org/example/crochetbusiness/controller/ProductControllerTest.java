package org.example.crochetbusiness.controller;

import org.example.crochetbusiness.Exception.ProductNotFoundException;
import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;


    @Test
    void shouldGetAllProducts() throws Exception {

        Product product1 = new Product("Hat", 120.0, 2);
        Product product2 = new Product("Pouch", 100.0, 4);

        when(productService.findAllProducts())
                .thenReturn(List.of(product1, product2));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Hat"))
                .andExpect(jsonPath("$[1].name").value("Pouch"));
    }

    @Test
    void ShouldReturnProductWithID() throws Exception {
        Product product = new Product("Hair Band",50.0,10);
        when(productService.findProductByID(1l)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/products/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hair Band"))
                .andExpect(jsonPath("$.price").value(50.0))
                .andExpect(jsonPath("$.stockQuantity").value(10));
    }

    @Test
    void shouldReturn404WhenProductWithIDDoesNotExist() throws Exception {

        when(productService.findProductByID(99L))
                .thenThrow(new ProductNotFoundException(99L));

        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteProduct() throws Exception{

        when(productService.deleteProduct(1L)).thenReturn("product deleted");
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("product deleted"));

        verify(productService).deleteProduct(1L);
    }

    @Test
    void shouldReturn404WhenProductDoesNotExist() throws Exception {

        when(productService.deleteProduct(99L))
                .thenThrow(new ProductNotFoundException(99L));

        mockMvc.perform(delete("/products/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateProduct() throws Exception{
        String requestBody = """
            {
              "name": "Frock",
              "price": 205.0,
              "stockQuantity": 4
            }
            """;

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());

        verify(productService).updateProduct(1L,"Frock",205.0,4);

    }

    @Test
    void shouldUpdateProductName() throws Exception {

        mockMvc.perform(put("/products/1/name")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Hat"))
                .andExpect(status().isOk());

        verify(productService).updateProductName(1L,"Hat");
    }

}
