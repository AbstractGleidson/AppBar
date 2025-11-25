import "./Pedido.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../components/Button"
import Input from "../components/Input"

export default function Pedido() {
  const [role, setRole] = useState("");
  const [mesa, setMesa] = useState("");
  const [id, setId] = useState("");
  const [quant, setQuant] = useState("");
  const [motivo, setMotivo] = useState("");
  const navigate = useNavigate();

  function registrar() {
    //Checa a validade dos valores
    if(role === "Cancelar" && motivo === "") {
      alert("Cancelamentos devem incluir motivos!")
      return
    } 
    //Passa os valores pro Back
    alert("Atualização subida com sucesso!")
    navigate(-1)
  }

  return (
    <div className="Pedido-page">
      <div className="Pedido-card">

        <Input
          type = "text"
          placeholder = "Número da mesa"
          value = {mesa}
          onChange={(e) => setMesa(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Id do Item"
          value = {id}
          onChange={(e) => setId(e.target.value)}
        />

        <Input
          type = "text"
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
