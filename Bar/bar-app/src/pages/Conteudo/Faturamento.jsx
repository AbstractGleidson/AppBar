import "../Estilo/Faturamento.css";
import { useState, useEffect } from "react";

export default function Cliente() {
//   const [faturas, setFaturas] = useState([]);

//   useEffect(() => {
//     async function carregarDados() {
//       try {
//         const response = await fetch("http://localhost:8080/bar/item/more/revenue");

//         if (!response.ok) {
//           throw new Error("Erro ao buscar dados do faturamento.");
//         }

//         const data = await response.json();
//         setItens(data);
//       } catch (error) {
//         console.error(error);
//         alert("Erro ao carregar relatório.");
//       }
//     }

//     carregarDados();
//   }, []);

//   // Correção: verifica se o array ainda está vazio
//   if (faturas.length === 0) {
//     return <h2>Carregando...</h2>;
//   }
  const faturas = [
    {"valor": 24},
    {"item_id": 1, "name": "Espetinho", "preco": 12, "quantidade": 2}
  ]

  //Uma fatura vai ter ID do item, nome do item, preço, quantidade e faturamento daquele pedido (quantidade * preço)

  const valorTotal = faturas.find(item => "valor" in item);
  return (
    <div className="Faturamento-page">  
      <h2 className="Faturamento-titulo">Faturamento</h2>

      <div className="cabecalho">
        <h3 className="item1">Id</h3>
        <h3 className="item2">Nome</h3>
        <h3 className="item3">Preço</h3>
        <h3 className="item4">Quantidade</h3>
        <h3 className="item5">Faturamento</h3>
      </div>

      <div className="Faturamento-container">
        <ul className="lista">
          {faturas
          .filter(item => item.item_id)
          .map(item => (
            <li key={item.item_id} className="Faturamento-item">
              <strong>{item.item_id}</strong>
              <span className="nome">{item.name}</span>
              <span className="preco">{item.preco.toFixed(2)}</span>
              <span className="quant">{item.quantidade}</span>
              <span className="fatu">{(item.quantidade*item.preco).toFixed(2)}</span>
            </li>
          ))}
        </ul>
      </div>
      <h2 className="total">
        Valor Total: {valorTotal.valor.toFixed(2)}
      </h2>
    </div>
  );
}
