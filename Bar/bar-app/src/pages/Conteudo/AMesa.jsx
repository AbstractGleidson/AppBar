import "../Estilo/AMesa.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function AMesa() {
  const [cpf, setCpf] = useState("");
  const [quant, setQuant] = useState("");
  const [ativo, setAtivo] = useState(false);
  const navigate = useNavigate();

  function inverter() {
    setAtivo(prev => !prev);
  }

  async function abertura() {

    if(cpf.trim() === "" || quant.trim() === "") {
      alert("Os campos não podem ser vazios.");
      return;
    }
    if(!(/^[0-9]+$/.test(cpf.trim())) || !(/^[0-9]+$/.test(quant.trim()))) {
      alert("Os campos devem possuir apenas números.")
      return;
    }
    if(quant === 0) {
      alert("Quantidade deve ser maior do que zero.")
      return;
    }

    const dados = {
      cpf_client: cpf,
      peoples: quant,
      couvert: ativo
    }

    const response = await fetch(`
      http://localhost:8080/account/open`, 
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },

        body: JSON.stringify(dados)
      }
    ); //Tem que fazer um post de Cpf, Quantidade de pessoas e Couvert


    if(!response.ok) //Erro se Usuário não existe ou se ele já possui uma conta aberta
      alert(response.text());
    
    else{
      alert("Cadastro bem sucedido!");
      navigate(-1);
    }

  }

  return (
    <div className="AMesa-page">
      <div className="AMesa-card">

        <Input
          type = "text"
          placeholder = "Cpf"
          value = {cpf}
          onChange={(e) => setCpf(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Quantidade de pessoas"
          value = {quant}
          onChange={(e) => setQuant(e.target.value)}
        />

        <div className="couvert">
          <Button 
            texto = "Habilitar Couvert"
            ativo = {ativo}
            onClick={() => inverter()} 
          />
        </div>

        <Button
          texto = "Abrir mesa"
          onClick={() => abertura()}
        />

      </div>
    </div>
  );
}
