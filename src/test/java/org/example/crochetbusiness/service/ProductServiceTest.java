package org.example.crochetbusiness.service;

import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest
{
    @Mock
   private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void serviceShouldAddProductWithValidData(){
        productService.addProduct("frock",205.0, 4);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative(){
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.addProduct("Crochet Bag", -500.0, 5)
        );
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void shouldThrowExceptionWhenStockIsNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.addProduct("Crochet Bag", 500.0, -5)
        );

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void findProductWithGivenID(){
        Product product = new Product("frock",205.0, 4);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findProductByID(1L);

        assertTrue(result.isPresent());
        assertEquals("frock",result.get().getName());
    }
}
