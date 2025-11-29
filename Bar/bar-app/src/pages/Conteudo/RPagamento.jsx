import "../Estilo/RPagamento.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function RPagamento() {
  const [mesa, setMesa] = useState("");
  const [valor, setValor] = useState("");
  const navigate = useNavigate();

  async function pagamento() {
    
    const dados = {
      conta_id: mesa,
      valor: valor
    }

    const response = await fetch(
        `http://localhost:8080/pay`, 
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify(dados)
        }
    );

    if(!response.ok){
        const msg = await response.text();
        alert(msg)
      }
      else{
        alert("Cadastro bem sucedido!");
        navigate(-1);
    }
  }

  return (
    <div className="RPagamento-page">
      <div className="RPagamento-card">

        <Input
          type = "text"
          placeholder = "Id da Mesa"
          value = {mesa}
          onChange={(e) => setMesa(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Valor do pagamento"
          value = {valor}
          onChange={(e) => setValor(e.target.value)}
        />

        <Button
          texto = "Pagar"
          onClick={() => pagamento()}
        />
      </div>
    </div>
  );
}
