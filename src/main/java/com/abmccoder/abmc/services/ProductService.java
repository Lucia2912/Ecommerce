package com.abmccoder.abmc.services;


import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository repository;

    public void saveProduct (Product product){
        repository.save(product);
    }

    public List<Product> readProduct() {
        return repository.findAll();
    }

    public Optional<Product> readOneProduct(Integer id){
        return repository.findById(id);
    }

    public void destroyOneProduct (Integer id){
        repository.deleteById(id);
    }
}
