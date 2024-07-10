package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.Invoice;
import com.abmccoder.abmc.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository <Invoice, Integer>{
    @Query("select p from invoice p where p.client_id = ?1")
    List<Invoice> findByClient(Integer client);
}
