import { useLocation } from "react-router-dom";
import "../Estilo/Faturamento.css";
import { useState, useEffect } from "react";

export default function Faturamento() {
  const location = useLocation();
  const start = location.state?.start;
  const end = location.state?.end;

  const [faturas, setFaturas] = useState([]);
  const [valorTotal, setValorTotal] = useState(0);
  const [carregando, setCarregando] = useState(true);

  // Converte yyyy-MM-dd → dd/MM/yyyy
  function formatarData(data) {
    const [ano, mes, dia] = data.split("-");
    return `${dia}/${mes}/${ano}`;
  }

  const startDataFormatada = formatarData(start);
  const endDataFormatada = formatarData(end);

  useEffect(() => {
    async function carregarDados() {
      try {
        const response = await fetch(
          `http://localhost:8080/consumption/interval?startDate=${startDataFormatada}&endDate=${endDataFormatada}`
        );

        if (!response.ok) {
          alert(await response.text())
          throw new Error("Erro ao buscar dados do faturamento.");
        }

        const data = await response.json();

        // Garante que consumptions é um array
        const lista = Array.isArray(data.consumptions) ? data.consumptions : [];

        setFaturas(lista);
        setValorTotal(data.revenue ?? 0);
        setCarregando(false);
      } catch (error) {
        console.error(error);
        alert(error);
        setCarregando(false);
      }
    }

    carregarDados();
  }, [start, end]);

  if (carregando) {
    return <h2>Carregando...</h2>;
  }

  return (
    <div className="Faturamento-page">
      <h2 className="Faturamento-titulo">Faturamento</h2>

      <p>
        Período: <strong>{startDataFormatada}</strong> até{" "}
        <strong>{endDataFormatada}</strong>
      </p>

      <div className="cabecalho">
        <h3 className="item1">Nome</h3>
        <h3 className="item3">Quantidade</h3>
        <h3 className="item4">Data</h3>
        <h3 className="item5">CPF Cliente</h3>
      </div>

      <div className="Faturamento-container">
        {faturas.length > 0 ? (
          <ul className="lista">
            {faturas.map((item, index) => (
              <li key={index} className="Faturamento-item">
                <strong>{item?.nameItem ?? "—"}</strong>
                <span className="quant">{item?.quantity ?? "—"}</span>
                <span className="data">{item?.date ?? "—"}</span>
                <span className="cpf">{item?.client_cpf ?? "—"}</span>
              </li>
            ))}
          </ul>
        ) : (
          <h3 style={{ marginTop: "20px" }}>Nenhuma fatura encontrada.</h3>
        )}
      </div>

      <h2 className="total">
        Valor Total: R$ {valorTotal.toFixed(2)}
      </h2>
    </div>
  );
}