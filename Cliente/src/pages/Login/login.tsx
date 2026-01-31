import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";

function Login() {
    const [usuario, setUsuario] = useState("");
    const [contrasena, setPassword] = useState("");
    const [error, setError] = useState("");
    const rutas = useNavigate();

    const envio = (e: React.FormEvent) => {
        e.preventDefault();

        fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            usuario: usuario,
            contrasena: contrasena
        })
        })
        .then(mensaje => {
            if (!mensaje.ok) {
            throw new Error("Usuario o Cotraseña icorrecta");
            }
            return mensaje.json();
        })
        .then(user => {
            if (user.administrador === "S") {
            rutas("/admin");
            } else {
            rutas("/empleado");
            }
        })
        .catch(error => {
            setError(error.message);
        });
    };

    return (
        <main>
        <div className="login">
            <h3>Iniciar Sesión</h3>

            <form onSubmit={envio}>
            <label>Usuario</label>
            <input
                type="text"
                value={usuario}
                onChange={e => setUsuario(e.target.value)}
            />

            <label>Contraseña</label>
            <input
                type="password"
                value={contrasena}
                onChange={e => setPassword(e.target.value)}
            />

            {error && <p className="error">{error}</p>}

            <button type="submit">Iniciar Sesión</button>
            </form>
        </div>
        </main>
    );
}

export default Login;
