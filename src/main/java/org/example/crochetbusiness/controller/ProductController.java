package org.example.crochetbusiness.controller;

import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.service.ProductService;
import org.jspecify.annotations.NonNull;
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
    public void addProduct(@RequestBody Product product){
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

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product){
         productService.updateProduct(id,product.getName(),product.getPrice(), product.getStockQuantity());
    }

    @PutMapping("/{id}/name")
    public void updateProductName(@PathVariable long id, @RequestBody String name){
        productService.updateProductName(id, name);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id){
       return productService.deleteProduct(id);
    }
}