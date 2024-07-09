package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository <Product, Integer>{
}
