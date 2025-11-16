package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Conta;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<Object, Primary key type>
// Abstract the request with the database for this object
public interface ContaInterface extends CrudRepository<Conta, Integer> {
}
