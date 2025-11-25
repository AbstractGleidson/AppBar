import "./Edicao.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../components/Button"
import Input from "../components/Input"

export default function Edicao() {
  const [id, setId] = useState("");
  const [valor, setValor] = useState("");
  const [nome, setNome] = useState("");
  const [tipo, setTipo] = useState("");
  const navigate = useNavigate();

  function atualizar() {
    //Checa a validade dos valores 
    //Passa os valores pro Back
    alert("Atualização subida com sucesso!")
    navigate(-1)
  }

  return (
    <div className="Edicao-page">
      <div className="Edicao-card">

        <Input
          type = "text"
          placeholder = "Id do Item"
          value = {id}
          onChange={(e) => setId(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Nome do item (Vazio para não alterar)."
          value = {nome}
          onChange={(e) => setNome(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Valor (Vazio para não alterar)."
          value = {valor}
          onChange={(e) => setValor(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Tipo (Vazio para não alterar)."
          value = {tipo}
          onChange={(e) => setTipo(e.target.value)}
        />

        <Button 
          texto = "Confirmar"
          onClick={() => atualizar()} 
        />

      </div>
    </div>
  );
}
