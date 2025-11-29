import "../Estilo/ItensVendidos.css";
import { useState, useEffect } from "react";

export default function Cliente() {
  const [itens, setItens] = useState([]);

  useEffect(() => {
    async function carregarDados() {
      try {
        const response = await fetch("http://localhost:8080/bar/item/more/sale");

        if (!response.ok) {
          throw new Error("Erro ao buscar dados dos itens vendidos.");
        }

        const data = await response.json();
        setItens(data);
      } catch (error) {
        console.error(error);
        alert("Erro ao carregar relatório.");
      }
    }

    carregarDados();
  }, []);

  // Enquanto não carregou nada, mostra carregando
  if (itens.length === 0) {
    return <h2>Carregando...</h2>;
  }

  return (
    <div className="Vendidos-page">  
      <h2 className="Vendidos-titulo">Itens mais vendidos</h2>
      
      <div className="cabecalho">
        <h3 className="item1">Id</h3>
        <h3 className="item2">Nome</h3>
        <h3 className="item5">Quantidade</h3>
      </div>

      <div className="Vendidos-container">
        <ul className="lista">
          {itens.map((item) => (
            <li key={item.item_id} className="Vendidos-item">
              <strong>{item.item_id}</strong>
              <span className="nome">{item.name}</span>
              <span className="quant">{item.quantity}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
