package com.serverBar.serverBar.DAOs;
import com.serverBar.serverBar.models.Client;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<Object, Primary key type>
// Abstract the requests with the database for this object
public interface ClientInterface extends CrudRepository<Client, String> {
}
