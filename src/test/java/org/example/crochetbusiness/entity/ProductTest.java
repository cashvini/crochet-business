package org.example.crochetbusiness.entity;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void shouldCreateProductObject(){
        Product testProduct = new Product("Frock", 200.00,3);
        assertEquals("Frock", testProduct.getName());
        assertEquals(200.00, testProduct.getPrice());
        assertEquals(3,testProduct.getStockQuantity());
    }
}

