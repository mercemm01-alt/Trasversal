import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./CSS/nuevoIngrediente.css";

const ALERGENOS = [
    "GLUTEN", "CRUSTACEOS", "HUEVOS", "PESCADO", "CACAHUETES", "SOJA",
    "LECHE", "FRUTOS_CASCARA", "APIO", "MOSTAZA", "SESAMO", "SULFITOS",
    "ALTRAMUCES", "MOLUSCOS"
];

function NuevoIngrediente() {
    const [nombre, setNombre] = useState("");
    const [stock, setStock] = useState(0);
    const [alergeno, setAlergeno] = useState<string>("");
    const [error, setError] = useState("");

    const navigate = useNavigate();

    const guardarIngrediente = () => {
        if (!nombre || !alergeno) {
            setError("Todos los campos son obligatorios");
            return;
        }

        fetch("/api/ingredientes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nombre,
                stock,
                alergeno
            })
        })
            .then(res => {
                if (!res.ok) throw new Error();
                navigate("/admin/inventario");
            })
            .catch(() => {
                setError("No se pudo guardar el ingrediente");
            });
    };

    return (
        <main className="nuevo-ingrediente">
            <h2>Nuevo ingrediente</h2>

            {error && <p className="error">{error}</p>}

            <label>
                Nombre
                <input value={nombre} onChange={e => setNombre(e.target.value)} />
            </label>

            <label>
                Stock inicial
                <input
                    type="number"
                    min={0}
                    value={stock}
                    onChange={e => setStock(Number(e.target.value))}
                />
            </label>

            <label>
                Alérgeno
                <select value={alergeno} onChange={e => setAlergeno(e.target.value)}>
                    <option value="">Selecciona uno</option>
                    {ALERGENOS.map(a => (
                        <option key={a} value={a}>{a}</option>
                    ))}
                </select>
            </label>

            <div className="acciones">
                <button onClick={guardarIngrediente}>Guardar</button>
                <button onClick={() => navigate("/admin/inventario")}>Cancelar</button>
            </div>
        </main>
    );
}

export default NuevoIngrediente;
