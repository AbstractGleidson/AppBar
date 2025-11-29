import "../Estilo/Cardapio.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();
  const [itens, setItens] = useState([]);

  async function getItens() {
    const response = await fetch(`http://localhost:8080/cardapio`)
    const data = response.json();
    return data;
  }

  useEffect(
    () => {
      async function loadData() {
        const data = await getItens();
        setItens(data)
      }
      loadData()
    },
    []
  );

  return (
    <div className="Cardapio-page">  
    <h2 className="lista-titulo"> Cardápio </h2>
      <div className="cabecalho">
        <h3 className="item"> Id </h3>
        <h3 className="item"> Nome </h3>
        <h3 className="item"> Tipo </h3>
        <h3 className="item"> Preço </h3>
      </div>
      <div className="lista-container">
        <ul className="lista">
          {itens.map(
            item => (
              <li className="lista-item">
                <strong>{item.number_item}</strong>
                <span className="nome">{item.name}</span>
                <span className="tipo">{item.type}</span>
                <span className="preco">{item.value.toFixed(2)}</span>
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
}
