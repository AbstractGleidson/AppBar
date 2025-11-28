import "../Estilo/Cadastradas.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();
  const [mesas, setMesas] = useState([]);

  async function getMesas() {
    const response = await fetch(`http://localhost:8080/accounts`);
    const data = await response.json();
    return data;
  }

   useEffect(() => {
      async function loadData() {
        const data = await getMesas();
        setMesas(data);
      }
      loadData();
    }, []);

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
                <span className="quantidade">{item.pessoas}</span>
                <span className="couvert">{item.couvert ? "Habilitado" : "Desabilitado"}</span>
                <span className="estado">{item.open ? "Aberta" : "Fechada"}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
