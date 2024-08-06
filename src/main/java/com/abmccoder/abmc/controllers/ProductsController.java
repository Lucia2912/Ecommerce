package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Save a product", description = "Save a product")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Update a product", description = "Update a product")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Get a list of products", description = "Get a list of products")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Read a product", description = "Read a product")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{id}")
    public Optional<Product> readOneProduct(@PathVariable("id") Integer id) {
        try {
            return service.readOneProduct(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer producto");
        }
    }

    @Operation(summary = "Delete a product", description = "Delete a product")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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
