import "./AMesa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../components/Button"
import Input from "../components/Input"

export default function AMesa() {
  const [cpf, setCpf] = useState("");
  const [quant, setQuant] = useState("");
  const navigate = useNavigate();

  function abertura() {
    //Valida as informações
    alert("Mesa aberta com sucesso!")
    navigate(-1)
  }

  return (
    <div className="AMesa-page">
      <div className="AMesa-card">

        <Input
          type = "text"
          placeholder = "Cpf"
          value = {cpf}
          onChange={(e) => setCpf(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Quantidade de pessoas"
          value = {quant}
          onChange={(e) => setQuant(e.target.value)}
        />

        <Button
          texto = "Abrir mesa"
          onClick={() => abertura()}
        />
      </div>
    </div>
  );
}
