import "../Estilo/FMesa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function FMesa() {
  const [mesa, setMesa] = useState("");
  const navigate = useNavigate();

  async function fechamento() {

    const dados = {
      client_cpf: null,
      account_id: mesa,
      open: false,
      peoples: null
    }

    const response = await fetch(
      `http://localhost:8080/account`, {
      method: "PUT",
      headers: {
          "Content-Type": "application/json"
      },

      body: JSON.stringify(dados)
    });

    if(!response.ok)
      alert("Mesa não existente!");
    else{
      const data = await response.json();

      alert("Mesa fechada.");
      navigate(-1);
    }
  }

  return (
    <div className="FMesa-page">
      <div className="FMesa-card">

        <Input
          type = "text"
          placeholder = "Id da Mesa"
          value = {mesa}
          onChange={(e) => setMesa(e.target.value)}
        />

        <Button
          texto = "Fechar mesa"
          onClick={() => fechamento()}
        />
      </div>
    </div>
  );
}
