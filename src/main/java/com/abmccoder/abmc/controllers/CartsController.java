package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Carts;
import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.services.ClientsService;
import com.abmccoder.abmc.services.CartsService;
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
@RequestMapping(path="api/v1/carts")
public class CartsController {
    @Autowired
    private CartsService service;
    @Autowired
    private ClientsService serviceClient;

    @Operation(summary = "Create a cart", description = "Create a cart")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping()
    private ResponseEntity<Carts> saveInvoice(@RequestBody Carts cart){
        try{
            service.saveCart(cart);
            return new ResponseEntity<>(cart, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear carrito");
        }
    }

    @Operation(summary = "Get a list of carts", description = "Get a list of carts")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Carts>> readCarts() {
        try {
            List<Carts> carts = service.readCarts();
            if(carts.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(carts);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer carritos");
        }
    }

    @Operation(summary = "Delete a cart", description = "Delete a cart")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping()
    public void destroyCarts(@RequestBody Carts carts){
        try {
            service.destroyOneCart(carts.getId());
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el carrito");
        }
    }

    @Operation(summary = "Add product to cart", description = "Add product to cart")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/{clid}/{pid}/{quantity}")
    public ResponseEntity<Carts> addProductToCart(@PathVariable Integer clid, @PathVariable Integer pid, @PathVariable Integer quantity) {
        try {
            Carts cart = service.addProductToCart(clid, pid, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete product to cart", description = "Delete product to cart")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCart(@PathVariable Integer id){
        try {
            service.removeProductFromCart(id);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a list of carts by client", description = "Get a list of carts by client")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Carts.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{clid}")
    public ResponseEntity<List<Carts>> findByClientIdAndDelivered(@PathVariable Integer clid) {
        try {
            Optional<Client> cliente = serviceClient.readOneClient(clid);
            Client foundClient = new Client();
            foundClient.setId(cliente.get().getId());
            List<Carts> carts = service.findByClient_IdAndDelivered(foundClient);
            return ResponseEntity.ok(carts);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
