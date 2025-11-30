import { QRCodeCanvas } from "qrcode.react";

export default function QRCode({ link, onClose }) {
  return (
    <div style={{
      position: "fixed",
      top: 0,
      left: 0,
      width: "100vw",
      height: "100vh", 
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
    }}>
      <div style={{
        background: "white",
        padding: "20px",
        borderRadius: "10px",
        textAlign: "center"
      }}>

        <a href={link} >
          {link}
        </a>

        <div style={{ marginTop: "20px" }}>
          <QRCodeCanvas value={link} size={180} />
        </div>

        <button
          style={{ marginTop: "20px", padding: "10px 20px" }}
          onClick={onClose}
        >
          Fechar
        </button>
      </div>
    </div>
  );
}
