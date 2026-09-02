package org.example.crochetbusiness.service;

import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String name, double price,int stock ) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can not be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock can not be negative");
        }
        Product product = new Product(name, price, stock);
        productRepository.save(product);
    }

    }

