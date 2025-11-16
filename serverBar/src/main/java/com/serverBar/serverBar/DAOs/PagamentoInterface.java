package com.serverBar.serverBar.DAOs;

import com.serverBar.serverBar.models.Pagamento;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PagamentoInterface extends CrudRepository<Pagamento, Integer> {

    public ArrayList<Pagamento> findByContaId(int id);
    public ArrayList<Pagamento> findByAutor(String nome);
}
