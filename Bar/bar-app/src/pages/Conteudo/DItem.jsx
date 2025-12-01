import "../Estilo/DItem.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function DItem() {
  const [id, setId] = useState("");
  const navigate = useNavigate();

  async function deletar() {

    if(id.trim() === "") {
      alert("O campo não pode estar vazio.")
    }
    if(!(/^[0-9]+$/.test(id.trim()))) {
      alert("O campo deve possuir apenas números.")
      return;
    }
    setId(id.trim()); //Exclui os espaços do string Id
    const response = await fetch(
      `http://localhost:8080/Item/${id}`, {
      method: "DELETE",
    });

    if(!response.ok)
      alert("Item não existente!");
    else{
      alert("Item deletado.");
      navigate(-1);
    }
  }

  return (
    <div className="DItem-page">
      <div className="DItem-card">

        <Input
          type = "number"
          placeholder = "Id do item"
          value = {id}
          onChange={(e) => setId(e.target.value)}
        />

        <Button
          texto = "Deletar"
          onClick={() => fechamento()}
        />
      </div>
    </div>
  );
}
