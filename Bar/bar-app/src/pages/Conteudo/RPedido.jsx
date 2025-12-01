import "../Estilo/RPedido.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Pedido() {
  const [mesa, setMesa] = useState("");
  const [id, setId] = useState("");
  const [quant, setQuant] = useState("");
  const navigate = useNavigate();

  async function registrar() {

    if(id.trim() === "" || quant.trim() == "" || mesa.trim() == "") {
      alert("Os campos não podem ser vazios.")
      return;
    }

    if(!(/^[0-9]+$/.test(mesa.trim())) || !(/^[0-9]+$/.test(quant.trim())) || !(/^[0-9]+$/.test(id.trim()))) {
      alert("Os campos devem conter apenas números.")
      return;
    }

    const dados = {
      conta_id: mesa.trim(),
      num_item: id.trim(),
      quantidade: quant.trim(),
    }

    const response = await fetch(
      `http://localhost:8080/consumption`, 
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
    <div className="RPedido-page">
      <div className="RPedido-card">

        <Input
          type = "number"
          placeholder = "Número da mesa"
          value = {mesa}
          onChange={(e) => setMesa(e.target.value)}
        />

        <Input
          type = "number"
          placeholder = "Id do Item"
          value = {id}
          onChange={(e) => setId(e.target.value)}
        />

        <Input
          type = "number"
          placeholder = "Quantidade"
          value = {quant}
          onChange={(e) => setQuant(e.target.value)}
        />

        <Button 
          texto = "Confirmar"
          onClick={() => registrar()} 
        />

      </div>
    </div>
  );
}
