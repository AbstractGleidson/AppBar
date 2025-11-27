import "../Estilo/AoG.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"

export default function AoG() {
  const navigate = useNavigate();

  return (
    <div className="AoG-page">
      <div className="AoG-card">
        <div className="role-buttons">
        
          <Button 
            texto = "Perfil de Administrador"
            onClick={() => navigate("/Adm")} 
          />

          <Button 
            texto = "Perfil de Garçom"
            onClick={() => navigate("/Garcom")}
          />

        </div>
      </div>
    </div>
  );
}
