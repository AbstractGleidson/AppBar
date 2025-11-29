import "../Estilo/Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Login() {
  const [role, setRole] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  async function entrar() {
    if(role !== "Admin" && role !== "Garcom" && role != "Cliente") {
      alert("Selecione uma função!");
      return
    }

    if (role === "Admin") {
      navigate("/AoG");
    } else if (role === "Garcom") {
      navigate("/Garcom");
    } else if (role === "Cliente") {

      const response = await fetch(`http://localhost:8080/client/${senha}`);

      if(!response.ok)
        alert("Usuário não cadastrado!");

      else{
        const data = await response.json();

        alert(JSON.stringify(data));
        navigate(
          "/Cliente", 
          {state: {cpf: senha}
          }
        );
      }
    }
  }

  return (
    <div className="login-page">
      <div className="login-card">
        <div className="role-buttons">
        
          <Button 
            texto = "Administrador"
            ativo = {role === "Admin"}
            onClick={() => setRole("Admin")} 
          />

          <Button 
            texto = "Garçom"
            ativo = {role === "Garcom"}
            onClick={() => setRole("Garcom")} 
          />

          <Button 
            texto = "Cliente" 
            ativo = {role === "Cliente"}
            onClick={() => setRole("Cliente")}
          />
          
        </div>

        <Input
          type = "password"
          placeholder = "Senha"
          value = {senha}
          onChange={(e) => setSenha(e.target.value)}
        />
        <Button
          texto = "Entrar"
          onClick = {() => entrar()}
        />
      </div>
    </div>
  );
}
