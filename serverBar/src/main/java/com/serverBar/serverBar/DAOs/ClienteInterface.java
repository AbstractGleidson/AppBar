package com.serverBar.serverBar.DAOs;
import com.serverBar.serverBar.models.Cliente;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<Object, Primary key type>
// Abstract the requests with the database for this object
public interface ClienteInterface extends CrudRepository<Cliente, Integer> {
}
