import "../Estilo/Negocio.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Negocio() {
  const [cou, setCou] = useState("");
  const [beb, setBeb] = useState("");
  const [com, setCom] = useState("");
  const navigate = useNavigate();

  function atualizar() {

    if(!(/^[0-9]+(,[0-9]{1,2})?$/.test(cou.trim())) || !(/^[0-9]+(,[0-9]{1,2})?$/.test(beb.trim())) || !(/^[0-9]+(,[0-9]{1,2})?$/.test(com.trim()))) {
      alert("Os campos devem possuir dados válidos.");
      return;
    }
    
    const dados = {
      cou : cou,
      beb : beb,
      com : com
    }

    JSON.stringify(dados, null, 2) //Passar pro DB

    alert("Atualização subida com sucesso!")
    navigate(-1)
  }

  return (
    <div className="Negocio-page">
      <div className="Negocio-card">

        <Input
          type = "text"
          placeholder = "Valor do Couvert (Vazio para não alterar)."
          value = {cou}
          onChange={(e) => setCou(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Porcentagem das bebidas (Vazio para não alterar)."
          value = {beb}
          onChange={(e) => setBeb(e.target.value)}
        />

        <Input
          type = "text"
          placeholder = "Porcentagem das comidas (Vazio para não alterar)."
          value = {com}
          onChange={(e) => setCom(e.target.value)}
        />

        <Button 
          texto = "Confirmar"
          onClick={() => atualizar()} 
        />

      </div>
    </div>
  );
}
