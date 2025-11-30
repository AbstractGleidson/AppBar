import { useLocation } from "react-router-dom";
import "../Estilo/Faturamento.css";
import { useState, useEffect } from "react";

export default function Cliente() {
  const [faturas, setFaturas] = useState([]);
  const [valorTotal, setValorTotal] = useState(0);

  const location = useLocation();
  const start = location.state?.start;
  const end = location.state?.end;

  function formatarData(data) {
    const [ano, mes, dia] = data.split("-");
    return `${dia}/${mes}/${ano}`;
  }

  const startData = formatarData(start);
  const endData = formatarData(end);

  useEffect(() => {
    async function carregarDados() {
      try {
        const response = await fetch(
          `http://localhost:8080/consumption/interval?startDate=${startData}&endDate=${endData}`
        );

        if (!response.ok) {
          throw new Error("Erro ao buscar dados do faturamento.");
        }

        const data = await response.json();

        // Ajuste para o novo formato da resposta
        setFaturas(data.consumptions || []);
        setValorTotal(data.revenue || 0);

      } catch (error) {
        console.error(error);
        alert("Erro ao carregar relatório.");
      }
    }

    carregarDados();
  }, []);

  if (faturas.length === 0) {
    return <h2>Carregando...</h2>;
  }

  return (
    <div className="Faturamento-page">  
      <h2 className="Faturamento-titulo">Faturamento</h2>

      <div className="cabecalho">
        <h3 className="item1">Nome</h3>
        <h3 className="item3">Quantidade</h3>
        <h3 className="item4">Data</h3>
        <h3 className="item5">CPF Cliente</h3>
      </div>

      <div className="Faturamento-container">
        <ul className="lista">
          {faturas.map((item, index) => (
            <li key={index} className="Faturamento-item">
              <strong>{item.nameItem}</strong>
              <span className="quant">{item.quantity}</span>
              <span className="data">{item.date}</span>
              <span className="cpf">{item.client_cpf}</span>
            </li>
          ))}
        </ul>
      </div>

      <h2 className="total">
        Valor Total: {valorTotal.toFixed(2)}
      </h2>
    </div>
  );
}
