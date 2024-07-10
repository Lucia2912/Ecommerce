package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository <InvoiceDetail, Integer>{
}
