package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.services.ClientsService;
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
@RequestMapping(path="api/v1/auth")
public class ClientsController {
    @Autowired
    private ClientsService service;

    @Operation(summary = "Register a new client", description = "Registers a new client with the provided details")
    @ApiResponse(responseCode = "200", description = "Client registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/register")
    private ResponseEntity<Client> saveClient(@RequestBody Client client){
        try{
            service.saveClient(client);
            return ResponseEntity.ok(client);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear cliente");
        }
    }

    @Operation(summary = "Update a client", description = "Update a client")
    @ApiResponse(responseCode = "200", description = "Client updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PutMapping("/me")
    private ResponseEntity<Client> updateClient(@RequestBody Client client){
        try{
            service.updateClient(client.getId(), client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear cliente");
        }
    }

    @Operation(summary = "Get a list of clients", description = "Get a list of clients")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Client>> readClients() {
        try {
            List<Client> clientes = service.readClient();
            if(clientes.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(clientes);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer cliente");
        }
    }

    @Operation(summary = "Get a client", description = "Get a client")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{id}")
    public Optional<Client> readOneClient(@PathVariable("id") Integer id) {
        try {
            return service.readOneClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer cliente");
        }
    }

    @Operation(summary = "Delete a client", description = "Delete a client")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping("/{id}")
    public void destroyClient(@PathVariable("id") Integer id){
        try {
            service.destroyOneClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el cliente");
        }
    }

}
