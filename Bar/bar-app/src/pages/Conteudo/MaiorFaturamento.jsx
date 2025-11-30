import "../Estilo/MaiorFaturamento.css";
import { useState, useEffect } from "react";

export default function Cliente() {
  const [itens, setItens] = useState([]);

  useEffect(() => {
    async function carregarDados() {
      try {
        const response = await fetch("http://localhost:8080/bar/item/more/revenue");

        if (!response.ok) {
          throw new Error("Erro ao buscar dados do faturamento.");
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

  // Correção: verifica se o array ainda está vazio
  if (itens.length === 0) {
    return <h2>Carregando...</h2>;
  }

  return (
    <div className="MaiorFaturamento-page">  
      <h2 className="MaiorFaturamento-titulo">Itens com maior faturamento</h2>

      <div className="cabecalho">
        <h3 className="item1">Id</h3>
        <h3 className="item2">Nome</h3>
        <h3 className="item5">Faturamento</h3>
      </div>

      <div className="MaiorFaturamento-container">
        <ul className="lista">
          {iten.map(item => (
            <li key={item.item_id} className="MaiorFaturamento-item">
              <strong>{item.item_id}</strong>
              <span className="nome">{item.name}</span>
              <span className="quant">{item.revenue.toFixed(2)}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
