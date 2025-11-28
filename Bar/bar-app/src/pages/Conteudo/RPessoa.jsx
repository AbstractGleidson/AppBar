import "../Estilo/RPessoa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function RPessoa() {
  const [nome, setNome] = useState("");
  const [cpf, setCpf] = useState("");
  const navigate = useNavigate();

  async function cadastro() {
    
    const dados = {
      name : nome,
      cpf: cpf
    }

    const response = await fetch(`
      http://localhost:8080/client`, 
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },

        body: JSON.stringify(dados)
      }
    ); //Tem que fazer um post de Nome e Cpf


    if(!response.ok)
      alert(response.text());
    
    else{
      alert("Cadastro bem sucedido!");
      navigate(-1);
    }
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
