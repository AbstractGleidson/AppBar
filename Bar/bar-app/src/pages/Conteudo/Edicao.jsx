import "../Estilo/Edicao.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Edicao() {
  const [id, setId] = useState("");
  const [valor, setValor] = useState(null);
  const [nome, setNome] = useState(null);
  const [tipo, setTipo] = useState(null);
  const navigate = useNavigate();

  async function atualizar() {
    //Checa a validade dos valores 
    
    const dados = {
      number_item: id,
      value: valor,
      name: nome,
      type: tipo,
      available: null
    }

    const response = await fetch(
        `http://localhost:8080/item`, 
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify(dados)
        }
    );

      if(!response.ok){
          const msg = await response.text();
          alert(msg)
        }
        else{
          alert("item atualizado com sucesso!");
          navigate(-1);
      }
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