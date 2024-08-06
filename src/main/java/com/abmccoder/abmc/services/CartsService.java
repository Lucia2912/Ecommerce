package com.abmccoder.abmc.services;


import com.abmccoder.abmc.entities.Carts;
import com.abmccoder.abmc.entities.Client;
import com.abmccoder.abmc.entities.Product;
import com.abmccoder.abmc.repositories.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abmccoder.abmc.repositories.ClientsRepository;
import com.abmccoder.abmc.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {
    @Autowired
    private CartsRepository repository;

    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private ClientsRepository clientRepository;

    public void saveCart (Carts carts){
        repository.save(carts);
    }

    public List<Carts> readCarts() {
        return repository.findAll();
    }

    public Optional<Carts> readOneCart(Integer id){
        return repository.findById(id);
    }

    public void destroyOneCart (Integer id){
        repository.deleteById(id);
    }

    public Carts removeProductFromCart(Integer cartId) {
        Optional<Carts> cart = repository.findById(cartId);
        if (cart.isPresent()) {
            repository.deleteById(cartId);
            return cart.get();
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    public List<Carts> findByClient_IdAndDelivered(Client client_id) {
        List<Carts> carts = repository.findByClientidAndDelivered(client_id, false);
        if (carts.isEmpty()) {
            throw new RuntimeException("Carts not found");
        } else {
            return carts;
        }
    }

    public Carts addProductToCart(Integer client_id, Integer productId, Integer amount) {
        Optional<Client> client = clientRepository.findById(client_id);
        Optional<Product> product = productRepository.findById(productId);
        if (client.isPresent() & product.isPresent()) {
            Carts cart = new Carts();
            cart.setClientid(client.get());
            cart.setProduct_id(product.get());
            cart.setPrice(product.get().getPrice());
            cart.setAmount(amount);
            cart.setDelivered(false);
            return repository.save(cart);
        } else {
            throw new RuntimeException("Client or Product not found");
        }
    }

}
