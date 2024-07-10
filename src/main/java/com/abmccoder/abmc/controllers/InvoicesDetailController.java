package com.abmccoder.abmc.controllers;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.entities.InvoiceDetail;
import com.abmccoder.abmc.services.ClientsService;
import com.abmccoder.abmc.services.InvoiceDetailService;
import com.abmccoder.abmc.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/invoice")
public class InvoicesDetailController {
    @Autowired
    private InvoiceDetailService service;
    @Autowired
    private ClientsService serviceClient;

    @PostMapping()
    private ResponseEntity<InvoiceDetail> saveInvoice(@RequestBody InvoiceDetail invoice){
        try{
            service.saveInvoiceDetail(invoice);
            return new ResponseEntity<>(invoice, HttpStatus.CREATED);
        } catch (Exception exception){
            System.out.println(exception);
            throw new RuntimeException("Error el crear comprobante");
        }
    }

    @GetMapping()
    public ResponseEntity<List<InvoiceDetail>> readInvoiceDetail() {
        try {
            List<InvoiceDetail> invoices = service.readInvoiceDetail();
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


    @DeleteMapping()
    public void destroyComprobante(@RequestBody InvoiceDetail invoice){
        try {
            service.destroyOneInvoiceDetail(invoice.getId());
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("Error al eliminar el comprobante");
        }
    }

}
