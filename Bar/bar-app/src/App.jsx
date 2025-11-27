// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Conteudo/Login"
import Garcom from "./pages/Conteudo/Garcom"
import Cliente from "./pages/Conteudo/Cliente"
import Adm from "./pages/Conteudo/Adm"
import RPessoa from "./pages/Conteudo/RPessoa"
import Pedido from "./pages/Conteudo/Pedido"
import Mesas from "./pages/Conteudo/Mesas"
import RPagamento from "./pages/Conteudo/RPagamento"
import AMesa from "./pages/Conteudo/AMesa"
import FMesa from "./pages/Conteudo/FMesa"
import AoG from "./pages/Conteudo/AoG"
import Edicao from "./pages/Conteudo/Edicao"
import Negocio from "./pages/Conteudo/Negocio"
import Cardapio from "./pages/Conteudo/Cardapio"

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element = {<Login />} />
        <Route path="/Cliente" element = {<Cliente />} />
        <Route path="/AoG" element = {<AoG />} />

        <Route path="/Adm" element = {<Adm />} />
          <Route path="/Adm/Edicao" element = {<Edicao />} />
          <Route path="/Adm/Negocio" element = {<Negocio />} />

        <Route path="/Garcom" element = {<Garcom />} />
          <Route path="/Garcom/Cardapio" element = {<Cardapio />} />
          <Route path="/Garcom/RPessoa" element = {<RPessoa />} />
          <Route path="/Garcom/Mesas" element = {<Mesas />} />
            <Route path="/Garcom/Mesas/Pedido" element = {<Pedido />} />
            <Route path="/Garcom/Mesas/Pagamento" element = {<RPagamento />} />
            <Route path="/Garcom/Mesas/Abrir" element = {<AMesa />} />
            <Route path="/Garcom/Mesas/Fechar" element = {<FMesa />} />

      </Routes>
    </BrowserRouter>
  );
}

