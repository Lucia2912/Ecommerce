package com.abmccoder.abmc.repositories;

import com.abmccoder.abmc.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository <Client, Integer> {
}
