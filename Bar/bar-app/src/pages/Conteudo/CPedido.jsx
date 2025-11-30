import "../Estilo/CPedido.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Pedido() {
  const [id, setId] = useState("");
  const [motivo, setMotivo] = useState("");
  const navigate = useNavigate();

  async function cancelar() {

    if(id.trim() === "" || motivo.trim() == "") {
      alert("Os campos não podem ser vazios.")
      return;
    }

    if(!(/^[0-9]+$/.test(ids.trim()))) {
      alert("O campo de ID deve conter apenas números.")
      return;
    }

    const dados = {
      pedido_id: id.trim(),
    }

    const response = await fetch(
      `http://localhost:8080/consumption`, 
      {
        method: "DELETE",
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
      alert("Cancelamento bem sucedido!");
      navigate(-1);
    }
  }

  return (
    <div className="CPedido-page">
      <div className="CPedido-card">

        <Input
          type = "number"
          placeholder = "Id do pedido"
          value = {id}
          onChange={(e) => setId(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Motivo do cancelamento."
          value = {motivo}
          onChange={(e) => setMotivo(e.target.value)}
        />

        <Button 
          texto = "Confirmar"
          onClick={() => cancelar()} 
        />

      </div>
    </div>
  );
}
