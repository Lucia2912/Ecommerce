package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Carts;
import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.services.ClientsService;
import com.abmccoder.abmc.services.InvoiceService;
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
@RequestMapping(path="api/v1/invoices")
public class InvoicesController {
    @Autowired
    private InvoiceService service;
    @Autowired
    private ClientsService serviceClient;

    @Operation(summary = "Save invoices", description = "Save invoices")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping()
    private ResponseEntity<Invoice> saveInvoice(@RequestParam Integer clientId){
        try{
            Optional<Client> cliente = serviceClient.readOneClient(clientId);
            Client foundClient = new Client();
            foundClient.setId(cliente.get().getId());
            Invoice invoice = service.generateInvoice(foundClient);
            return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get a list of invoices", description = "Get a list of invoices")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
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

    @Operation(summary = "Get a invoice", description = "Get a invoice")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/invoice/{id}")
    public Optional<Invoice> readOneInvoice(@PathVariable("id") Integer id) {
        try {
            return service.readOneInvoice(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobante");
        }
    }

    @Operation(summary = "Delete a invoice", description = "Delete a invoice")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @DeleteMapping()
    public void destroyComprobante(@RequestBody Invoice invoice){
        try {
            service.destroyOneInvoice(invoice.getId());
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el comprobante");
        }
    }

    @Operation(summary = "Get a list of invoices by client", description = "Get a list of invoices by client")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/client/{cid}")
    public List<Invoice> readInvoicesClient(@PathVariable("cid") Integer id) {
        try {
            return service.readInvoiceClient(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobante");
        }
    }

    @Operation(summary = "Get last invoice by client", description = "Get last invoice by client")
    @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Invoice.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{clid}")
    public Invoice readLastInvoicesClient(@PathVariable("clid") Integer id) {
        try {
            return service.getLastInvoiceByClientId(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al leer comprobante");
        }
    }


}
