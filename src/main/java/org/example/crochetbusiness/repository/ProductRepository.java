package org.example.crochetbusiness.repository;


import org.example.crochetbusiness.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}