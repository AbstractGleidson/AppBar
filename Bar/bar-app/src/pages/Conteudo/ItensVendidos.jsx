import "../Estilo/ItensVendidos.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  //Vai receber o Json
  const mesas = [
    { "id": 1, "nome": "Churrasco", "tipo": "Comida",  "preco": 24.50, "quantidade" : 2},
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

 

  return (
    <div className="Vendidos-page">  
    <h2 className="Vendidos-titulo"> Itens mais vendidos </h2>
      <div className="cabecalho">
        <h3 className="item1"> Id </h3>
        <h3 className="item2"> Nome </h3>
        <h3 className="item3"> Tipo </h3>
        <h3 className="item4"> Preço </h3>
        <h3 className="item5"> Quantidade </h3>
      </div>
      <div className="Vendidos-container">
        <ul className="lista">
          {mesas
            .map(item => (
              <li className="Vendidos-item">
                <strong>{item.id}</strong>
                <span className="nome">{item.nome}</span>
                <span className="tipo">{item.tipo}</span>
                <span className="preco">{item.preco.toFixed(2)}</span>
                <span className="quant"> {item.quantidade}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
