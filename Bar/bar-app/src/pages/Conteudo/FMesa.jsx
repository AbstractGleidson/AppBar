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

    if(mesa.trim() === "") {
      alert("O campo não pode estar vazio.")
    }
    if(!(/^[0-9]+$/.test(mesa.trim()))) {
      alert("O campo deve possuir apenas números.")
      return;
    }
    setMesa(mesa.trim()); //Exclui os espaços da string mesa
    const response = await fetch(
      `http://localhost:8080/account/close/${mesa}`, {
      method: "PUT",
    });

    if(!response.ok) //Erro se mesa não existe ou já está fechada
      alert("Mesa não existente!");
    else{
      alert("Mesa fechada.");
      navigate(-1);
    }
  }

  return (
    <div className="FMesa-page">
      <div className="FMesa-card">

        <Input
          type = "number"
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
