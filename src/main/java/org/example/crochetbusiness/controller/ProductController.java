package org.example.crochetbusiness.controller;

import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void addProduct(Product product){
        productService.addProduct(product.getName(),product.getPrice(),product.getStockQuantity());
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return  productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable long id){
        return productService.findProductByID(id).orElseThrow(()->new IllegalArgumentException("No product with given ID"));
    }
}
