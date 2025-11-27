package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// CrudRepository<Object, Primary key type>
// Abstract the request with the database for this object
public interface AccountInterface extends CrudRepository<Account, Integer> {

    // Find consumptions by client name
    ArrayList<Account> findByClientCpf(int cpf);
}
