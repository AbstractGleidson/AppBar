import "./Adm.css";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button"

export default function Adm() {
  const navigate = useNavigate();

  return (
    <div className="Adm-page">
      <div className="Adm-card">
        <div className="botoes">

          <Button 
            texto = "Alterar regras do negócio"
            onClick={() => navigate("/Adm/Negocio")}
          /> 

          <Button 
            texto = "Editar Cardápio"
            onClick={() => navigate("/Adm/Edicao")}
          />

          <Button 
            texto = "Relatórios"
            onClick={() => navigate("/Adm/Relatorios")}
          />        
  
        </div>
      </div>
    </div>
  );
}
