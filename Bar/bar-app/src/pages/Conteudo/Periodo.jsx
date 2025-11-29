import "../Estilo/Periodo.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function RPessoa() {
  const [inicio, setInicio] = useState("");
  const [fim, setFim] = useState("");
  const navigate = useNavigate();

  async function cadastro() {


    const dados = {
      name : nome,
      cpf: cpf
    }

    
  }

  return (
    <div className="Periodo-page">
      <div className="Periodo-card">

        <Input
          type = "date"
          placeholder = "Data final"
          value = {inicio}
          onChange={(e) => setInicio(e.target.value)}
        />

        <Input
          type = "date"
          placeholder = "Data final"
          value = {fim}
          onChange={(e) => setFim(e.target.value)}
        />

        <Button
          texto = "Gerar relatório"
          onClick={() => cadastro()}
        />
      </div>
    </div>
  );
}
