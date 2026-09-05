package org.example.crochetbusiness.Exception;

public class ProductNotFoundException extends RuntimeException {

public ProductNotFoundException(long id){
    super("Product not found with id: " + id);
}

}
