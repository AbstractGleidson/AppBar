package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Pay;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PayInterface extends CrudRepository<Pay, Integer> {

    // Finds payments by Account id
    public ArrayList<Pay> findByAccountId(int id);

    // Finds payments by author of payment
    public ArrayList<Pay> findByAuthor(String nome);
}
