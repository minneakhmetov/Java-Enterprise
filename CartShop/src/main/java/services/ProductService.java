/*
 * Developed by Razil Minneakhmetov on 10/28/18 2:47 PM.
 * Last modified 10/28/18 2:47 PM.
 * Copyright © 2018. All rights reserved.
 */

package services;

import forms.LoginForm;
import models.Product;
import repositories.CartRepository;
import repositories.ProductRepository;

import java.util.List;

public class ProductService {

    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public ProductService(CartRepository cartRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public List<Product> getAll(){
        return productRepository.readAll();
    }

    public void addAll(List<LoginForm> forms){
        cartRepository.delete();
        productRepository.delete();
        productRepository.batchUpdate(forms);
    }

    public Product read(Long id){
        return productRepository.readOne(id);
    }

    public List<Product> find(String string){
        return productRepository.find(string);
    }

}