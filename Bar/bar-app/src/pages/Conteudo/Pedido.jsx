import "../Estilo/Pedido.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Pedido() {
  const [role, setRole] = useState("");
  const [mesa, setMesa] = useState("");
  const [id, setId] = useState("");
  const [quant, setQuant] = useState("");
  const [motivo, setMotivo] = useState("");
  const navigate = useNavigate();

<<<<<<< HEAD
  async function registrar() {
    //Checa a validade dos valores
    if(role === "Cancelar" && motivo === "") {
      alert("Cancelamentos devem incluir motivos!")
      return
    } 
    else if(role === "Registrar"){
      const dados = {
        conta_id: mesa,
        num_item: id,
        quantidade: quant,
      }
=======
  function registrar() {
    if(role.trim() == "") {
      alert("Selecione uma opção de registro.")
      return;
    }

    if(role === "Cancelar" && motivo.trim() === "") {
      alert("Cancelamentos devem incluir motivos!")
      return;
    }
    
    if(id.trim() === "" || quant.trim() == "" || mesa.trim() == "") {
      alert("Os campos não podem ser vazios.")
      return;
    }

    if(!(/^[0-9]+$/.test(mesa.trim())) || !(/^[0-9]+$/.test(quant.trim())) || !(/^[0-9]+$/.test(id.trim()))) {
      alert("Os campos devem conter apenas números.")
      return;
    }
>>>>>>> main

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
    else
    {
      const response = await fetch(
        `http://localhost:8080/consumptions/${mesa}`, 
        {
          method: "Delete",
        }
      );


      if(!response.ok)
      {
        const msg = await response.text();
        alert(msg)
      }
      else{
        alert("Pedido cancelado!");
        navigate(-1);
      }
    }
  }

  return (
    <div className="Pedido-page">
      <div className="Pedido-card">

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

        <Input
          type = "text"
          placeholder = "Motivo (Obrigatório apenas para cancelamentos)."
          value = {motivo}
          onChange={(e) => setMotivo(e.target.value)}
        />

        <div className="role-buttons">

          <div className="resgistramento">
            <Button 
              texto = "Registrar pedido"
              ativo = {role === "Registrar"}
              onClick={() => setRole("Registrar")} 
            />
          </div>

          <div className="cancelamento">
            <Button 
              texto = "Cancelar pedido"
              ativo = {role === "Cancelar"}
              onClick={() => setRole("Cancelar")} 
            />
          </div>

        </div>

        <Button 
          texto = "Confirmar"
          onClick={() => registrar()} 
        />

      </div>
    </div>
  );
}
