import "./RPessoa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../components/Button"
import Input from "../components/Input"

export default function RPessoa() {
  const [nome, setNome] = useState("");
  const [cpf, setCpf] = useState("");
  const navigate = useNavigate();

  function cadastro() {
    //Passa as infos pro banco de dados
    alert("Cadastro bem sucedido!")
    navigate(-1)
  }

  return (
    <div className="RPessoa-page">
      <div className="RPessoa-card">

        <Input
          type = "text"
          placeholder = "Nome"
          value = {nome}
          onChange={(e) => setNome(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Cpf"
          value = {cpf}
          onChange={(e) => setCpf(e.target.value)}
        />

        <Button
          texto = "Cadastrar"
          onClick={() => cadastro()}
        />
      </div>
    </div>
  );
}
