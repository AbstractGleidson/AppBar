import "./FMesa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../components/Button"
import Input from "../components/Input"

export default function FMesa() {
  const [mesa, setMesa] = useState("");
  const navigate = useNavigate();

  function fechamento() {
    //Valida as informações
    alert("Mesa fechada com sucesso!")
    navigate(-1)
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
