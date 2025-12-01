// src/App.jsx
import "./pages/Estilo/App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Conteudo/Login"
import Garcom from "./pages/Conteudo/Garcom"
import Cliente from "./pages/Conteudo/Cliente"
import Adm from "./pages/Conteudo/Adm"
import RPessoa from "./pages/Conteudo/RPessoa"
import RPedido from "./pages/Conteudo/RPedido"
import CPedido from "./pages/Conteudo/CPedido"
import Mesas from "./pages/Conteudo/Mesas"
import RPagamento from "./pages/Conteudo/RPagamento"
import AMesa from "./pages/Conteudo/AMesa"
import FMesa from "./pages/Conteudo/FMesa"
import AoG from "./pages/Conteudo/AoG"
import Edicao from "./pages/Conteudo/Edicao"
import Negocio from "./pages/Conteudo/Negocio"
import Cardapio from "./pages/Conteudo/Cardapio"
import Cadastradas from "./pages/Conteudo/Cadastradas"
import ItensVendidos from "./pages/Conteudo/ItensVendidos"
import MaiorFaturamento from "./pages/Conteudo/MaiorFaturamento"
import Faturamento from "./pages/Conteudo/Faturamento"
import Periodo from "./pages/Conteudo/Periodo"
import DItem from "./pages/Conteudo/DItem"

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element = {<Login/>} />
        <Route path="/Cliente" element = {<Cliente />} />
        <Route path="/AoG" element = {<AoG />} />

        <Route path="/Adm" element = {<Adm />} />
          <Route path="/Adm/Edicao" element = {<Edicao />} />
          <Route path="/Adm/DItem" element = {<DItem/>} />
          <Route path="/Adm/Negocio" element = {<Negocio />} />
          <Route path="/Adm/Periodo" element = {<Periodo/>} />
          <Route path="/Adm/Periodo/Faturamento" element = {<Faturamento/>} />
          <Route path="/Adm/ItensVendidos" element = {<ItensVendidos/>} />
          <Route path="/Adm/MaiorFaturamento" element = {<MaiorFaturamento/>} />

        <Route path="/Garcom" element = {<Garcom />} />
          <Route path="/Garcom/Cardapio" element = {<Cardapio />} />
          <Route path="/Garcom/RPessoa" element = {<RPessoa />} />
          <Route path="/Garcom/Mesas" element = {<Mesas />} />
            <Route path="/Garcom/Mesas/RPedido" element = {<RPedido />} />
            <Route path="/Garcom/Mesas/CPedido" element = {<CPedido />} />
            <Route path="/Garcom/Mesas/Pagamento" element = {<RPagamento />} />
            <Route path="/Garcom/Mesas/Abrir" element = {<AMesa />} />
            <Route path="/Garcom/Mesas/Fechar" element = {<FMesa />} />
            <Route path="/Garcom/Mesas/Cadastradas" element = {<Cadastradas />} />

      </Routes>
    </BrowserRouter>
  );
}

