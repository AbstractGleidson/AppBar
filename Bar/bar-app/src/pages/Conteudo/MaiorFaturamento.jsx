import "../Estilo/MaiorFaturamento.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  //Vai receber o Json
  const mesas = [
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400},
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "faturamento" : 100},
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "faturamento" : 400}
  ];

 

  return (
    <div className="MaiorFaturamento-page">  
    <h2 className="MaiorFaturamento-titulo"> Itens com maior faturamento </h2>
      <div className="cabecalho">
        <h3 className="item1"> Id </h3>
        <h3 className="item2"> Nome </h3>
        <h3 className="item3"> Tipo </h3>
        <h3 className="item4"> Preço </h3>
        <h3 className="item5"> Faturamento </h3>
      </div>
      <div className="MaiorFaturamento-container">
        <ul className="lista">
          {mesas
            .map(item => (
              <li className="MaiorFaturamento-item">
                <strong>{item.id}</strong>
                <span className="nome">{item.nome}</span>
                <span className="tipo">{item.tipo}</span>
                <span className="preco">{item.preco.toFixed(2)}</span>
                <span className="quant"> {item.faturamento.toFixed(2)}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
