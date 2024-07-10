package com.abmccoder.abmc.services;


import com.abmccoder.abmc.entities.InvoiceDetail;
import com.abmccoder.abmc.repositories.InvoiceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailService {
    @Autowired
    private InvoiceDetailsRepository repository;

    public void saveInvoiceDetail (InvoiceDetail invoiceDetail){
        repository.save(invoiceDetail);
    }

    public List<InvoiceDetail> readInvoiceDetail() {
        return repository.findAll();
    }


    public Optional<InvoiceDetail> readOneInvoiceDetail(Integer id){
        return repository.findById(id);
    }

    public void destroyOneInvoiceDetail (Integer id){
        repository.deleteById(id);
    }
}
