import "../Estilo/Cliente.css";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();
  const location = useLocation();
  
  const [dadosCliente, setDadosCliente] = useState(null);

  const cpf = location.state?.cpf;

  async function getClienteDados() {
    const response = await fetch(`http://localhost:8080/client/resume/${cpf}`);
    const data = await response.json();
    return data;
  }

  useEffect(() => {
    async function loadData() {
      const data = await getClienteDados();
      setDadosCliente(data);
    }
    loadData();
  }, []);

  // So deixa renderizar as paradas depois que carregar os valores
  if (!dadosCliente) {
    return <h2>Carregando...</h2>;
  }

  const accountNumber = dadosCliente.consumptions[0].account.id;

  return (
    <div className="Cliente-page">
      <h2 className="Cliente-titulo"> Número da mesa {accountNumber} </h2>

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
          {dadosCliente.consumptions.map(consumption => (
            <li key={consumption.id} className="Cliente-item">
              <strong>{consumption.id}</strong>

              <span className="nome">{consumption.item.name}</span>
              <span className="tipo">{consumption.item.type}</span>
              <span className="preco">
                {consumption.item.value.toFixed(2)}
              </span>
              <span className="quant">{consumption.quantity}</span>

              <span className="sub">
                {(consumption.item.value * consumption.quantity).toFixed(2)}
              </span>
            </li>
          ))}
        </ul>
      </div>

      <div className="infos">
        <h3 className="couvert">
          Valor do Couvert: {dadosCliente.covert.toFixed(2)}
        </h3>
        <h3 className="gorBebida">
          Gorjeta da Bebida: {dadosCliente.tipDrink.toFixed(2)}
        </h3>
        <h3 className="gorComida">
          Gorjeta da Comida: {dadosCliente.tipFood.toFixed(2)}
        </h3>
        <h2 className="total">
          Valor Total: {dadosCliente.accountValue.toFixed(2)}
        </h2>
      </div>
    </div>
  );
}
