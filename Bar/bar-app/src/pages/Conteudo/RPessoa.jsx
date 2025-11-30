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

    if(nome.trim() === "" || cpf.trim() === "") {
      alert("Campos não podem ser vazios.");
      return;
    }
    if(!(/^[0-9]+$/.test(cpf.trim()))) {
      alert("O campo deve conter apenas números.")
      return;
    }

    const dados = {
      name : nome.trim(),
      cpf: cpf.trim()
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


    if(!response.ok) //Erro se usuário já existe
      alert();
    
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
          type = "number"
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
