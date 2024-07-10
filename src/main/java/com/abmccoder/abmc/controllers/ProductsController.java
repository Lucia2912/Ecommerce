package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/products")
public class ProductsController {
    @Autowired
    private ProductService service;

    @PostMapping()
    private ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try{
            service.saveProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear producto");
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product){
        try{
            Optional<Product> productOptional = service.readOneProduct(id);
            if(!productOptional.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Product foundProduct = productOptional.get();
            service.saveProduct(product);
            return new ResponseEntity<>(foundProduct, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el modificar producto");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Product>> readProducts() {
        try {
            List<Product> productos = service.readProduct();
            if(productos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(productos);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer productos");
        }
    }

    @GetMapping("/{id}")
    public Optional<Product> readOneProduct(@PathVariable("id") Integer id) {
        try {
            return service.readOneProduct(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer producto");
        }
    }

    @DeleteMapping("/{id}")
    public void destroyProduct(@PathVariable("id") Integer id){
        try {
            service.destroyOneProduct(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el producto");
        }
    }

}
