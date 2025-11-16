package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Account;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<Object, Primary key type>
// Abstract the request with the database for this object
public interface AccountInterface extends CrudRepository<Account, Integer> {
}
