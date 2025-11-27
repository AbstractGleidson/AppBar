import "../Estilo/Cliente.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  //Vai receber o Json
  const mesas = [
    { "id": "Mesa", "Numero": 5 },
    { "id": "Total", "Numero": 100 },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  },
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2, "Subtotal" : "48,00" },
    { "id": 2, "nome": "Limonada", "tipo": "Bebida", "preco": 6.30 , "quantidade" : 4, "Subtotal" : "24,00"  }
  ];

  const mesa = mesas.find(m => m.id === "Mesa")
  const valor = mesas.find(m => m.id === "Total")

  

  return (
    <div className="Cliente-page">  
    <h2 className="lista-titulo"> Mesa {mesa.Numero} </h2>
      <div className="cabecalho">
        <h3 className="item1"> Id </h3>
        <h3 className="item2"> Nome </h3>
        <h3 className="item3"> Tipo </h3>
        <h3 className="item4"> Preço </h3>
        <h3 className="item5"> Quantidade </h3>
        <h3 className="item6"> Subtotal </h3>
      </div>
      <div className="lista-container">
        <ul className="lista">
          {mesas
            .filter(item => item.id !== "Mesa")
            .filter(item => item.id !== "Total")
            .map(item => (
              <li className="lista-item">
                <strong>{item.id}</strong>
                <span className="nome">{item.nome}</span>
                <span className="tipo">{item.tipo}</span>
                <span className="preco">{item.preco.toFixed(2)}</span>
                <span className="quant"> {item.quantidade}</span>
                <span className="sub"> {(item.preco*item.quantidade).toFixed(2)}</span>
              </li>
            ))}
        </ul>
      </div>
      <h2 className="total"> Valor Total: {valor.Numero.toFixed(2)} </h2>
    </div>
  );
}
