import "../Estilo/Negocio.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

// Componentes
import Button from "../../components/Button";
import Input from "../../components/Input";

export default function Negocio() {
  const [cou, setCou] = useState("");
  const [beb, setBeb] = useState("");
  const [com, setCom] = useState("");
  const navigate = useNavigate();

  async function atualizar() {

    if(cou.trim() !== "" && !(/^[0-9]+(,[0-9]{1,2})?$/.test(cou.trim())) ) {
      alert("Os campos devem possuir dados válidos.");
      return;
    }

    if(beb.trim() !== "" &&  !(/^[0-9]+(,[0-9]{1,2})?$/.test(beb.trim())) ) {
      alert("Os campos devem possuir dados válidos.");
      return;
    }

    if(com.trim() !== "" &&  !(/^[0-9]+(,[0-9]{1,2})?$/.test(com.trim())) ) {
      alert("Os campos devem possuir dados válidos.");
      return;
    }
    
    const dados = {
      cou : cou,
      beb : beb,
      com : com
    }

    // Atualizar couvert
    if (cou !== "") {
      const responseCouvert = await fetch(
        `http://localhost:8080/bar/covert/${cou}`,
        { method: "PUT" }
      );

      if (!responseCouvert.ok) {
        alert("Erro ao atualizar couvert!");
      } else {
        alert("Couvert atualizado!");
      }

    // Enviar gorjetas
    if (beb !== "" || com !== "") {

      const tipData = {
        tipPercentDrink: beb === "" ? null : Number(beb),
        tipPercentFood: com === "" ? null : Number(com),
      };

      const responseTip = await fetch(`http://localhost:8080/bar/tip`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(tipData),
      });

      const msg = await responseTip.text();

      if (responseTip.ok) {
        alert(msg);
      } else {
        alert(msg);
      }
    }

      alert("Atualização concluída!");
      navigate(-1);
    }
  }

  return (
    <div className="Negocio-page">
      <div className="Negocio-card">

        <Input
          type="text"
          placeholder="Valor do Couvert (Vazio para não alterar)."
          value={cou}
          onChange={(e) => setCou(e.target.value)}
        />

        <Input
          type="text"
          placeholder="Porcentagem das bebidas (Vazio para não alterar)."
          value={beb}
          onChange={(e) => setBeb(e.target.value)}
        />

        <Input
          type="text"
          placeholder="Porcentagem das comidas (Vazio para não alterar)."
          value={com}
          onChange={(e) => setCom(e.target.value)}
        />

        <Button texto="Confirmar" onClick={atualizar} />
      </div>
    </div>
  );
}