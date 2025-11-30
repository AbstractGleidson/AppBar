import "../Estilo/Edicao.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Edicao() {
  const [id, setId] = useState("");
  const [valor, setValor] = useState("");
  const [nome, setNome] = useState("");
  const [tipo, setTipo] = useState("");
  const navigate = useNavigate();

  async function atualizar() {
    //Checa a validade dos valores 
    
    if(id.trim() === "") {
      alert("Campo do id deve ser preenchido.");
      return;
    }

    if(!(/^[0-9]+$/.test(id.trim())) || (valor.trim() !== "" && !(/^[0-9]+(,[0-9]{1,2})?$/.test(valor.trim()))) ) {
      alert("Os campos devem possuir dados válidos.");
      return;
    }

    if(tipo.trim() !== "" && (tipo.trim().toLowerCase() !== "comida" && tipo.trim().toLowerCase() !== "bebida") ) {
      alert("O tipo deve ser Comida ou Bebida.");
      return;
    }

    const tipoInt = (tipo === ""? null: tipo);
    
    if(tipoInt != null)
      tipoInt = (tipoInt === "Comida"? 1 : 2);

    const dados = {
      number_item: id.trim(),
      value: valor === ""? null: valor.trim(),
      name: nome === ""? null: nome.trim(),
      type: tipoInt,
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
          type = "number"
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