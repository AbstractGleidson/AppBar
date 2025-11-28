import "../Estilo/Cliente.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  //Vai receber o Json
  const mesas = [
    { "id": "Mesa", "Numero": 5 },
    { "id": "Total", "Valor": 100 },
    { "id": "Cou", "Valor": 20 },
    { "id": "Bebi", "Valor": 50 },
    { "id": "Comi", "Valor": 60 },
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
  const total = mesas.find(m => m.id === "Total")
  const couv = mesas.find(m => m.id === "Cou")
  const bebi = mesas.find(m => m.id === "Bebi")
  const comi = mesas.find(m => m.id === "Comi")

  return (
    <div className="Cliente-page">  
    <h2 className="Cliente-titulo"> Mesa {mesa.Numero} </h2>
      <div className="cabecalho">
        <h3 className="item1"> Id </h3>
        <h3 className="item2"> Nome </h3>
        <h3 className="item3"> Tipo </h3>
        <h3 className="item4"> Preço </h3>
        <h3 className="item5"> Quantidade </h3>
        <h3 className="item6"> Subtotal </h3>
      </div>
      <div className="Cliente-container">
        <ul className="lista">
          {mesas
            .filter(item => item.id !== "Mesa")
            .filter(item => item.id !== "Total")
            .filter(item => item.id !== "Cou")
            .filter(item => item.id !== "Bebi")
            .filter(item => item.id !== "Comi")
            .map(item => (
              <li className="Cliente-item">
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
      <div className="infos">
        <h3 className="couvert"> Valor do Couvert: {couv.Valor.toFixed(2)} </h3>
        <h3 className="gorBebida"> Gorjeta da Bebida: {bebi.Valor.toFixed(2)} </h3>
        <h3 className="gorComida"> Gorjeta da Comida: {comi.Valor.toFixed(2)} </h3>
        <h2 className="total"> Valor Total: {total.Valor.toFixed(2)} </h2>
      </div>
    </div>
  );
}
