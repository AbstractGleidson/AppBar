package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Item;
import org.springframework.data.repository.CrudRepository;

// CrudRepository<Object, primary ley type>
// Abstract the requests with the database for this object
public interface ItemInterface extends CrudRepository<Item, Integer> {
}
