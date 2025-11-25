import "./Cliente.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Cliente() {
  const navigate = useNavigate();

  return (
    <div className="Cliente-page">
      <div className="Cliente-card">
        <div className="Mesa">

          <p> Pedidos da mesa x: </p>
          
        </div>
      </div>
    </div>
  );
}
