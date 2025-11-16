package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Consumption;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

// CrudRepository<Object, primary key type>
// Abstract the requests with the database for this object
public interface ConsumptionInterface extends CrudRepository<Consumption, Integer> {

    // Generates the query by signing the method, this signature being on the way to a "where"
    // FROM Consumo consumo JOIN Conta conta ON Consumo.conta_id = conta.id
    // Finds all the consumptions of the account that has the id that was passed as parameter
    ArrayList<Consumption> findByAccountId(int id);

    // Find consumptions by client name
    ArrayList<Consumption> findByAccountClientName(String nome);
}
