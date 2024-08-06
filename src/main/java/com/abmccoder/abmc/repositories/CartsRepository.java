package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.Carts;
import com.abmccoder.abmc.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartsRepository extends JpaRepository <Carts, Integer>{
    List<Carts> findByClientidAndDelivered(Client client_id, Boolean delivered);
}
