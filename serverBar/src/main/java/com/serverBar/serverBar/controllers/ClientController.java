package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClientInterface;
import com.serverBar.serverBar.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    private ClientInterface clientDAO; // Automatically generates DAOs methods

    @GetMapping("/clients")
    public ArrayList<Client> getClients() // Recover all database clients
    {
        // Return List of Clients or Empty List
        return (ArrayList<Client>) clientDAO.findAll();
    }

    @PostMapping("/client")
    public Client postClient(@RequestBody Client client) // Save one new client
    {
        return clientDAO.save(client); // Return new client
    }

    @PutMapping("/client")
    public Client updateClient(@RequestBody Client client) // Updates a customer information
    {
        return clientDAO.save(client); // Return update client
    }

    @GetMapping("/client/{cpf}")
    public Optional<Client> searchClientCpf(@PathVariable("cpf") int cpf) // Search client by cpf
    {
        // Client or Null
        return clientDAO.findById(cpf); // Return the client if it exists in the database
    }

    @DeleteMapping("/clients")
    public void deleteAllClients() // Delete all clients on database
    {
        clientDAO.deleteAll();
    }

    @DeleteMapping("/client/{cpf}")
    public void deleteClient(@PathVariable("cpf") int cpf) // Delete client for cpf
    {
        clientDAO.deleteById(cpf);
    }
}