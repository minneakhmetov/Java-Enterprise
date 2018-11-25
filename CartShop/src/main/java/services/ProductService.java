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
import repositories.Repository;

import java.util.List;

public class ProductService {

    private ProductRepository repository;
    private CartRepository cartRepository;

    public ProductService(Repository productRepository, Repository cartRepository) {
        this.repository = (ProductRepository) productRepository;
        this.cartRepository = (CartRepository) cartRepository;
    }

    public List<Product> getAll(){
        return repository.readAll();
    }

    public void addAll(List<LoginForm> forms){
        cartRepository.delete();
        repository.delete();
        repository.batchUpdate(forms);
    }

    public Product read(Long id){
        return repository.readOne(id);
    }

    public List<Product> find(String string){
        return repository.find(string);
    }

}