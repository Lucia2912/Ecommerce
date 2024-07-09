package com.abmccoder.abmc.services;

import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {
    @Autowired
    private ClientsRepository repository;

    public void saveClient (Client client){
        repository.save(client);
    }

    public List<Client> readClient() {
        return repository.findAll();
    }

    public Optional<Client> readOneClient(Integer id){
        return repository.findById(id);
    }

    public void destroyOneClient (Integer id){
        repository.deleteById(id);
    }
}
