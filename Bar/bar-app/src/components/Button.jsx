// src/components/Button.jsx
import React from "react";
import "./Button.css";

export default function Button({ texto, ativo, onClick }) {
  return (
    <button 
    
      className={ativo ? "botaoAtivo" : "botao"}
      onClick={onClick}
    >
      {texto}
    </button>
  );
}
