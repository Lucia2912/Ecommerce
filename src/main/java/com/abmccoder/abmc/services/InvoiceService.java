package com.abmccoder.abmc.services;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.repositories.ClientsRepository;
import com.abmccoder.abmc.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired private InvoicesRepository repository;
    @Autowired private ClientsRepository clientsRepository;

    public void saveInvoice (Invoice invoice){
        repository.save(invoice);
    }

    public List<Invoice> readInvoice() {
        return repository.findAll();
    }

    public Optional<Invoice> readOneInvoice(Integer id){
        return repository.findById(id);
    }

    public void destroyOneInvoice (Integer id){
        repository.deleteById(id);
    }

    public Invoice addInvoice (Integer invoiceId, Integer clientId) throws Exception{
        Optional<Invoice> invoice = repository.findById(invoiceId);
        Optional<Client> client = clientsRepository.findById(clientId);
        if(!invoice.isPresent()){
            throw new Exception("No se encontro el comprobante");
        }
        if(!client.isPresent()){
            throw new Exception("No se encontro el cliente");
        }

        Invoice foundInvoice = invoice.get();
        Client foundClient = client.get();

        foundInvoice.setClient_id(foundClient);
        return repository.save(foundInvoice);
    }
}
