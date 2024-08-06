package com.abmccoder.abmc.services;

import com.abmccoder.abmc.entities.Carts;
import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.repositories.CartsRepository;
import com.abmccoder.abmc.repositories.ClientsRepository;
import com.abmccoder.abmc.repositories.InvoicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class InvoiceService {
    @Autowired private InvoicesRepository repository;
    @Autowired private ClientsRepository clientsRepository;
    @Autowired private CartsRepository cartsRepository;

    public void saveInvoice (Invoice invoice){
        repository.save(invoice);
    }

    public List<Invoice> readInvoice() {
        return repository.findAll();
    }

    public Optional<Invoice> readOneInvoice(Integer id){
        return repository.findById(id);
    }

    public List<Invoice> readInvoiceClient(Integer idCliente) {
        return repository.findByClient(idCliente);
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

    public Invoice getLastInvoiceByClientId(Integer clientId) {
        Optional<Client> client = clientsRepository.findById(clientId);
        if (client.isPresent()) {
            List<Invoice> invoices = client.get().getInvoices();
            if (invoices.isEmpty()) {
                throw new RuntimeException("No invoices found for the client");
            }
            Invoice lastInvoice = invoices.get(invoices.size() - 1);
            return lastInvoice;
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public Invoice generateInvoice(Client client_id) {
        Optional<Client> client = clientsRepository.findById(client_id.getId());
        if (client.isPresent()) {
            List<Carts> carts = cartsRepository.findByClientidAndDelivered(client_id, false);
            if (carts.isEmpty()) {
                throw new RuntimeException("Not found products on cart");
            } else {
                Client foundClient = client.get();
                Invoice invoice = new Invoice();
                invoice.setClient_id(foundClient);
                LocalDateTime now = LocalDateTime.now();
                invoice.setCreated_at(now);
                double total = 0.0;
                for (Carts cart : carts) {
                    total += cart.getAmount() * cart.getPrice();
                    cart.setDelivered(true);
                }
                invoice.setTotal(total);
                return repository.save(invoice);
            }
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
