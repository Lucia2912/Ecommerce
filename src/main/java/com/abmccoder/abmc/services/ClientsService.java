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

    public Client updateClient(Integer id, Client clientDetails) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            Client foundClient = client.get();
            if (clientDetails.getName() != null) {
                foundClient.setName(clientDetails.getName());
            }
            if (clientDetails.getDocnumber() != null) {
                foundClient.setDocnumber(clientDetails.getDocnumber());
            }
            if (clientDetails.getLastname() != null) {
                foundClient.setLastname(clientDetails.getLastname());
            }
            return repository.save(foundClient);
        } else {
            throw new RuntimeException("Client not found");
        }
    }
}
