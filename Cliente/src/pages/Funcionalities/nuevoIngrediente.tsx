import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ALERGENOS = [
    "GLUTEN", "CRUSTACEOS", "HUEVOS", "PESCADO", "CACAHUETES", "SOJA",
    "LECHE", "FRUTOS_CASCARA", "APIO", "MOSTAZA", "SESAMO", "SULFITOS",
    "ALTRAMUCES", "MOLUSCOS"
];

function NuevoIngrediente() {
    const [nombre, setNombre] = useState("");
    const [cantidad, setCantidad] = useState(0);
    const [alergenosSeleccionados, setAlergenosSeleccionados] = useState<string[]>([]);
    const [error, setError] = useState("");

    const navigate = useNavigate();

    const toggleAlergeno = (alergeno: string) => {
        setAlergenosSeleccionados(prev =>
            prev.includes(alergeno)
                ? prev.filter(a => a !== alergeno)
                : [...prev, alergeno]
        );
    };

    const guardarIngrediente = () => {
        if (!nombre.trim()) {
            setError("El nombre del ingrediente es obligatorio");
            return;
        }

        const ingrediente = {
            nombre,
            cantidad,
            alergenos: alergenosSeleccionados.join(", ")
        };

        fetch("/api/ingredientes", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(ingrediente)
        })
            .then(res => {
                if (!res.ok) throw new Error();
                navigate("/inventario");
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
                <input
                    type="text"
                    value={nombre}
                    onChange={e => setNombre(e.target.value)}
                />
            </label>

            <label>
                Cantidad inicial
                <input
                    type="number"
                    min={0}
                    value={cantidad}
                    onChange={e => setCantidad(Number(e.target.value))}
                />
            </label>

            <h4>Alérgenos</h4>
            <div className="alergenos">
                {ALERGENOS.map(a => (
                    <label key={a}>
                        <input
                            type="checkbox"
                            checked={alergenosSeleccionados.includes(a)}
                            onChange={() => toggleAlergeno(a)}
                        />
                        {a}
                    </label>
                ))}
            </div>

            <div className="acciones">
                <button onClick={guardarIngrediente}>
                    Guardar ingrediente
                </button>

                <button onClick={() => navigate("/inventario")}>
                    Cancelar
                </button>
            </div>
        </main>
    );
}

export default NuevoIngrediente;
