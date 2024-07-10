package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.services.ClientsService;
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

    @PostMapping("/register")
    private ResponseEntity<Client> saveClient(@RequestBody Client client){
        try{
            service.saveClient(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear cliente");
        }
    }

    @PutMapping("/me")
    private ResponseEntity<Client> updateClient(@RequestBody Client client){
        try{
            service.saveClient(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear cliente");
        }
    }

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

    @GetMapping("/{id}")
    public Optional<Client> readOneClient(@PathVariable("id") Integer id) {
        try {
            return service.readOneClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer cliente");
        }
    }

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
