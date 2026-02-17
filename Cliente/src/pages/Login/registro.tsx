import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./login.css";

function Registro() {

    const navigate = useNavigate();

    const [usuario, setUsuario] = useState("");
    const [contrasena, setContrasena] = useState("");
    const [confirmarContrasena, setConfirmarContrasena] = useState("");
    const [nombre, setNombre] = useState("");
    const [apellidos, setApellidos] = useState("");
    const [correo, setCorreo] = useState("");
    const [telefono, setTelefono] = useState("");
    const [error, setError] = useState("");

    const enviar = (e: React.FormEvent) => {
        e.preventDefault();

        if (contrasena !== confirmarContrasena) {
            setError("Las contraseñas no coinciden");
            return;
        }

        fetch("/api/clientes/registro", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                usuario: usuario,
                contrasena: contrasena,
                nombre: nombre,
                apellidos: apellidos,
                correo: correo,
                numTlf: telefono
            })
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error("No se pudo completar el registro");
                }
                return res.json();
            })
            .then(user => {
                localStorage.setItem("usuario", user.usuario);
                localStorage.setItem("rol", user.rol);
                localStorage.setItem("idUsuario", user.idUsuarios);

                navigate("/");
            })
            .catch(err => {
                setError(err.message);
            });
    };

    return (
        <main className="main">
            <div className="registro">
                <h3>Registro de Cliente</h3>

                <form onSubmit={enviar}>

                    <label>Usuario</label>
                    <input
                        type="text"
                        value={usuario}
                        onChange={e => setUsuario(e.target.value)}
                        required
                    />

                    <label>Contraseña</label>
                    <input
                        type="password"
                        value={contrasena}
                        onChange={e => setContrasena(e.target.value)}
                        required
                    />

                    <label>Confirmar contraseña</label>
                    <input
                        type="password"
                        value={confirmarContrasena}
                        onChange={e => setConfirmarContrasena(e.target.value)}
                        required
                    />

                    <label>Nombre</label>
                    <input
                        type="text"
                        value={nombre}
                        onChange={e => setNombre(e.target.value)}
                        required
                    />

                    <label>Apellidos</label>
                    <input
                        type="text"
                        value={apellidos}
                        onChange={e => setApellidos(e.target.value)}
                        required
                    />

                    <label>Correo electrónico</label>
                    <input
                        type="email"
                        value={correo}
                        onChange={e => setCorreo(e.target.value)}
                        required
                    />

                    <label>Teléfono</label>
                    <input
                        type="number"
                        value={telefono}
                        onChange={e => setTelefono(e.target.value)}
                        required
                    />

                    {error && <p className="error">{error}</p>}

                    <button type="submit">Registrarse</button>

                    <p className="volver-login">
                        ¿Ya tienes cuenta?
                        <br />
                        <Link to="/login">Inicia sesión aquí</Link>
                    </p>

                </form>
            </div>
        </main>
    );
}

export default Registro;
