package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository <Invoice, Integer>{
    @Query("select p from invoices p where p.client_id = ?1")
    List<Invoice> findByClient(Integer client);
}
