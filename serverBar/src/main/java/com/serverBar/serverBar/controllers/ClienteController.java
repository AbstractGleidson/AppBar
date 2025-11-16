package com.serverBar.serverBar.controllers;

import com.serverBar.serverBar.DAOs.ClienteInterface;
import com.serverBar.serverBar.models.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteInterface DAO; // Automatically generates DAOs methods

    @GetMapping("/clients")
    public ArrayList<Cliente> getClients() // Recover all database clients
    {
        return (ArrayList<Cliente>) DAO.findAll();
    }

    @PostMapping("/client")
    public Cliente postClient(@RequestBody Cliente client) // Save one new client
    {
        return DAO.save(client); // Return new client
    }

    @PutMapping("/client")
    public Cliente updateClient(@RequestBody Cliente client) // Updates a customer information
    {
        return DAO.save(client); // Return update client
    }

    @GetMapping("/client/{cpf}")
    public Optional<Cliente> searchClientCpf(@PathVariable("cpf") int cpf) // Search client by cpf
    {
        // Client or Null
        return DAO.findById(cpf); // Return the client if it exists in the database
    }

    @DeleteMapping("/clients")
    public void deleteAllClients() // Delete all clients on database
    {
        DAO.deleteAll();
    }

    @DeleteMapping("/client/{cpf}")
    public void deleteClient(@PathVariable("cpf") int cpf) // Delete client for cpf
    {
        DAO.deleteById(cpf);
    }
}