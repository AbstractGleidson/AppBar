import "../Estilo/Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import logo from "../../assets/Logo.png";

//Componentes
import Button from "../../components/Button"
import Input from "../../components/Input"

export default function Login() {
  const [role, setRole] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  async function entrar() {
    if(role === "") {
      alert("Selecione uma opção pra logar!");
      return;
    }

    if(senha.trim() === "") {
      alert("Os campos não podem ser vazios!");
      return;
    }
    setSenha(senha.trim()); //Tira os espaços de senha
    if (role === "Admin") {
        const response = await fetch(
            `http://localhost:8080/auth/login/admin`, 
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              },

              body: senha
            }
        );

        if(response.ok)
        {
          alert("Login realizado com suscesso");
          navigate("/AoG");
        }
        else{
          const msg = await response.text()
          alert(msg)
        }
    } else if (role === "Garcom") {
        const response = await fetch(
            `http://localhost:8080/auth/login/garcom`, 
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              },

              body: senha
            }
        );

        if(response.ok)
        {
          alert("Login realizado com suscesso");
          navigate("/Garcom");
        }
        else{
          const msg = await response.text()
          alert(msg)
        }
    } else if (role === "Cliente") {
      if(!(/^[0-9]+$/.test(senha.trim()))) {
          alert("O campo deve conter apenas números.")
          return;
      }

      const response = await fetch(`http://localhost:8080/client/${senha}`);

      if(!response.ok) //Erro se usuário não existe no banco de dados
        alert("Usuário não cadastrado!");

      else{
        const data = await response.json();

        alert("Login Realizado com sucesso");
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
      <div className="imagem"> 
        <img 
          src={logo} 
          alt="Logo" 
          style={{ width: "100px", height: "auto" }} 
        />
      </div>
      <div className="titulos">
        <h2> Trabalho de POO - Bar System </h2>
      </div>
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
      <div className="nomes">
        <h3> By Gleidson and Ivan </h3>
      </div>
    </div>
  );
}
