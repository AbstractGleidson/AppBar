// src/components/Input.jsx
import React from "react";

export default function Entrada({ type, placeholder, value, onChange }) {
  return (
    <div className="Campo">
      <input
        className="input"
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
      />
    </div>
  );
}
