import "../Estilo/Garcom.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"

export default function Garcom() {
  const [role, setRole] = useState("");
  const navigate = useNavigate();

  return (
    <div className="Garcom-page">
      <div className="Garcom-card">
        <div className="role-buttons">
        
          <Button 
            texto = "Abrir Cardápio"
            onClick={() => navigate("/Garcom/Cardapio")}
          />

          <Button 
            texto = "Registrar pessoa"
            onClick={() => navigate("/Garcom/RPessoa")} 
          />

           <Button 
            texto = "Gerenciar mesas"
            onClick={() => navigate("/Garcom/Mesas")}
          />

        </div>

      </div>
    </div>
  );
}
