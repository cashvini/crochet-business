package org.example.crochetbusiness.controller;

import jakarta.validation.Valid;
import org.example.crochetbusiness.DTO.ProductResponse;
import org.example.crochetbusiness.entity.Product;
import org.example.crochetbusiness.service.ProductService;
import org.example.crochetbusiness.DTO.ProdcutRequest;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody ProdcutRequest productRequest){
        productService.addProduct(productRequest.getName(),productRequest.getPrice(),productRequest.getStockQuantity());
    }

    @GetMapping
    public List<Product> findAllProducts(){
        return  productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductByID(@PathVariable long id){
        Product product =  productService.findProductByID(id).orElseThrow(()->new IllegalArgumentException("No product with given ID"));
        return new ProductResponse(product.getId(),product.getName(),product.getPrice(), product.getStockQuantity());
    }

    @PutMapping("/{id}")
    public void updateProduct(@Valid @PathVariable long id, @RequestBody Product product){
         productService.updateProduct(id,product.getName(),product.getPrice(), product.getStockQuantity());
    }

    @PutMapping("/{id}/name")
    public void updateProductName(@PathVariable long id, @RequestBody String name){
        productService.updateProductName(id, name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }
}