import "../Estilo/Cadastradas.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  //Vai receber o Json
  const mesas = [
    { "id": 1, "cpf": "xxx.xxx.xxx-xx", "quantidade": "8", "couvert": "Habilitado",  "estado": "Aberta" },
    { "id": 2, "cpf": "xxx.xxx.xxx-xx", "quantidade": "4", "couvert": "Desabilitado",  "estado": "Aberta" },
    { "id": 3, "cpf": "xxx.xxx.xxx-xx", "quantidade": "5", "couvert": "Habilitado", "estado": "Fechada" }
  ];

  

  return (
    <div className="Cadastradas-page">  
    <h2 className="lista-titulo"> Mesas cadastradas </h2>
      <div className="cabecalho">
        <h3 className="item1"> Id </h3>
        <h3 className="item2"> Cpf </h3>
        <h3 className="item3"> Pessoas </h3>
        <h3 className="item4"> Couvert </h3>
        <h3 className="item5"> Conta </h3>
      </div>
      <div className="lista-container">
        <ul className="lista">
          {mesas
            .map(item => (
              <li className="lista-item">
                <strong>{item.id}</strong>
                <span className="cpf">{item.cpf}</span>
                <span className="quantidade">{item.quantidade}</span>
                <span className="couvert">{item.couvert}</span>
                <span className="estado">{item.estado}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
