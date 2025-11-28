import "../Estilo/Mesas.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

//Componentes
import Button from "../../components/Button"

export default function Mesas() {
  const [role, setRole] = useState("");
  const navigate = useNavigate();

  return (
    <div className="Mesas-page">
      <div className="Mesas-card">
        <div className="role-buttons">
        
          <Button 
            texto = "Abrir mesa"
            onClick={() => navigate("/Garcom/Mesas/Abrir")} 
          />

          <Button 
            texto = "Fechar mesa"
            onClick={() => navigate("/Garcom/Mesas/Fechar")} 
          />

          <Button 
            texto = "Mesas cadastradas"
            onClick={() => navigate("/Garcom/Mesas/Cadastradas")}
          />

          <Button 
            texto = "Gerenciar pedidos"
            onClick={() => navigate("/Garcom/Mesas/Pedido")}
          />

          <Button 
            texto = "Gerenciar pagamentos"
            onClick={() => navigate("/Garcom/Mesas/Pagamento")}
          />

        </div>

      </div>
    </div>
  );
}
