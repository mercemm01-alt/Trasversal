import { useEffect, useState } from "react";

interface Empleado {
    usuario: string;
    nombre: string;
    apellidos: string;
    administrador: "S" | "N";
}

function AdministrarUsuarios() {
    const [empleados, setEmpleados] = useState<Empleado[]>([]);
    const [mostrarForm, setMostrarForm] = useState(false);
    const [error, setError] = useState("");

    // Formulario
    const [usuario, setUsuario] = useState("");
    const [nombre, setNombre] = useState("");
    const [apellidos, setApellidos] = useState("");
    const [contrasena, setContrasena] = useState("");
    const [admin, setAdmin] = useState(false);

    useEffect(() => {
        fetch("/api/empleados")
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => setEmpleados(data))
        .catch(() => setError("No se pudieron cargar los empleados"));
    }, []);

    const crearEmpleado = (e: React.FormEvent) => {
        e.preventDefault();

        fetch("/api/empleados", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            usuario,
            nombre,
            apellidos,
            contrasena,
            administrador: admin ? "S" : "N"
        })
        })
        .then(res => {
            if (!res.ok) throw new Error();

            setEmpleados(prev => [
            ...prev,
            {
                usuario,
                nombre,
                apellidos,
                administrador: admin ? "S" : "N"
            }
            ]);

            // reset
            setUsuario("");
            setNombre("");
            setApellidos("");
            setContrasena("");
            setAdmin(false);
            setMostrarForm(false);
        })
        .catch(() => setError("No se pudo crear el empleado"));
    };

    const eliminarEmpleado = (usuario: string) => {
        fetch(`/api/empleados/${usuario}`, {
        method: "DELETE"
        })
        .then(res => {
            if (!res.ok) throw new Error();
            setEmpleados(prev =>
            prev.filter(e => e.usuario !== usuario)
            );
        })
        .catch(() => setError("No se pudo eliminar el empleado"));
    };

    return (
        <main className="usuarios">
        <h2>Administrar Usuarios</h2>

        {error && <p className="error">{error}</p>}

        <button onClick={() => setMostrarForm(!mostrarForm)}>
            {mostrarForm ? "Cancelar" : "+ Añadir empleado"}
        </button>

        {/* FORMULARIO */}
        {mostrarForm && (
            <form onSubmit={crearEmpleado} className="form-empleado">
                <input
                    type="text"
                    placeholder="Usuario"
                    value={usuario}
                    onChange={e => setUsuario(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                    required
                />

                <input
                    type="text"
                    placeholder="Apellidos"
                    value={apellidos}
                    onChange={e => setApellidos(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Contraseña"
                    value={contrasena}
                    onChange={e => setContrasena(e.target.value)}
                    required
                />

                <label>
                    <input
                    type="checkbox"
                    checked={admin}
                    onChange={e => setAdmin(e.target.checked)}
                    />
                    Administrador
                </label>

                <button type="submit">Crear</button>
            </form>
        )}

        {/* TABLA */}
        <table>
            <thead>
            <tr>
                <th>Usuario</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Admin</th>
                <th>Acciones</th>
            </tr>
            </thead>

            <tbody>
            {empleados.map(e => (
                <tr key={e.usuario}>
                <td>{e.usuario}</td>
                <td>{e.nombre}</td>
                <td>{e.apellidos}</td>
                <td>{e.administrador === "S" ? "Sí" : "No"}</td>
                <td>
                    <button
                    className="eliminar"
                    onClick={() => eliminarEmpleado(e.usuario)}
                    >
                    Eliminar
                    </button>
                </td>
                </tr>
            ))}
            </tbody>
        </table>
        </main>
    );
}

export default AdministrarUsuarios;
