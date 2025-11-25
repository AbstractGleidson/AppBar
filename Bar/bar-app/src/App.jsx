// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login"
import Garcom from "./pages/Garcom"
import Cliente from "./pages/Cliente"
import Adm from "./pages/Adm"
import RPessoa from "./pages/RPessoa"
import Pedido from "./pages/Pedido"
import Mesas from "./pages/Mesas"
import RPagamento from "./pages/RPagamento"
import AMesa from "./pages/AMesa"
import FMesa from "./pages/FMesa"
import AoG from "./pages/AoG"
import Edicao from "./pages/Edicao"
import Negocio from "./pages/Negocio"

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

