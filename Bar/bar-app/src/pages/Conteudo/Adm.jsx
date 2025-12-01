import "../Estilo/Adm.css";
import { useNavigate } from "react-router-dom";
import Button from "../../components/Button"

export default function Adm() {
  const navigate = useNavigate();

  return (
    <div className="Adm-page">
      <div className="Adm-card">
        <div className="botoes">

          <Button 
            texto = "Editar Cardápio"
            onClick={() => navigate("/Adm/Edicao")}
          />

          <Button 
            texto = "Cancelar Item"
            onClick={() => navigate("/Adm/DItem")}
          />

          <Button 
            texto = "Alterar regras do negócio"
            onClick={() => navigate("/Adm/Negocio")}
          /> 

          <Button 
            texto = "Faturamento por período"
            onClick={() => navigate("/Adm/Periodo")}
          />

          <Button 
            texto = "Itens mais vendidos"
            onClick={() => navigate("/Adm/ItensVendidos")}
          />

          <Button 
            texto = "Itens com maior faturamento"
            onClick={() => navigate("/Adm/MaiorFaturamento")}
          />        
  
        </div>
      </div>
    </div>
  );
}
