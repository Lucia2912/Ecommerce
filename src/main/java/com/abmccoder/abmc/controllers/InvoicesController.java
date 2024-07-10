package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.entities.InvoiceDetail;
import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.services.ClientsService;
import com.abmccoder.abmc.services.InvoiceService;
import com.abmccoder.abmc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/carts")
public class InvoicesController {
    @Autowired
    private InvoiceService service;
    @Autowired
    private ClientsService serviceClient;

    @PostMapping()
    private ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice){
        try{
            service.saveInvoice(invoice);
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear comprobante");
        }
    }

    @GetMapping()
    public ResponseEntity<List<Invoice>> readInvoice() {
        try {
            List<Invoice> invoices = service.readInvoice();
            if(invoices.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return ResponseEntity.ok(invoices);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobantes");
        }
    }

    @GetMapping("/{id}")
    public Optional<Invoice> readOneInvoice(@PathVariable("id") Integer id) {
        try {
            return service.readOneInvoice(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobante");
        }
    }

    @DeleteMapping()
    public void destroyComprobante(@RequestBody Invoice invoice){
        try {
            service.destroyOneInvoice(invoice.getId());
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el comprobante");
        }
    }

    @GetMapping("/{id}")
    public List<Invoice> readInvoiceDetailClient(@PathVariable("id") Integer id) {
        try {

            return service.readInvoiceClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobante");
        }
    }

}
