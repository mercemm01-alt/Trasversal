import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./login.css";
import { Link } from "react-router-dom";

function Login() {
    const [usuario, setUsuario] = useState("");
    const [contrasena, setPassword] = useState("");
    const [error, setError] = useState("");
    const rutas = useNavigate();

    const envio = (e: React.FormEvent) => {
        e.preventDefault();

        fetch("/api/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            usuario: usuario.trim(),
            contrasena: contrasena.trim()
        })
        })
        .then(mensaje => {
            if (!mensaje.ok) {
            throw new Error("Usuario o Cotraseña icorrectos");
            }
            return mensaje.json();
        })
        .then(user => {
            console.log("RESPUESTA LOGIN:", user);
            
            localStorage.setItem("idUsuario", user.idUsuarios);
            localStorage.setItem("usuario", user.usuario);
            localStorage.setItem("rol", user.rol);

            if (user.rol === "ADMIN") {
                rutas("/admin");
            } else if(user.rol === "EMPLEADO"){
                rutas("/emple");
            } else{
                rutas("/cliente");
            }
        })
        .catch(error => {
            setError(error.message);
        });
    };

    return (
        <main className="main">
        <div className="login">
            <h3>Iniciar Sesión</h3>

            <form onSubmit={envio}>
            <label>Usuario</label>
            <input
                type="text"
                value={usuario}
                onChange={e => setUsuario(e.target.value)} required
            />

            <label>Contraseña</label>
            <input
                type="password"
                value={contrasena}
                onChange={e => setPassword(e.target.value)} required
            />

            {error && <p className="error">{error}</p>}

            <button type="submit">Iniciar Sesión</button>
            <p className="registro-link">
                ¿Aún no tienes cuenta?<br />
                <Link to="/registro">Regístrate aquí</Link>
            </p>
            </form>
        </div>
        </main>
    );
}

export default Login;
