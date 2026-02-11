import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import type { Ingrediente } from "../../types/Ingrediente";


function Inventario() {
    const [ingredientes, setIngredientes] = useState<Ingrediente[]>([]);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        fetch("/api/ingredientes")
            .then(res => {
                if (!res.ok) throw new Error();
                return res.json();
            })
            .then(data => setIngredientes(data))
            .catch(() => setError("Error al cargar el inventario"));
    }, []);

    const actualizarStock = (ingrediente: Ingrediente, nuevoStock: number) => {
        fetch(`/api/ingredientes/${ingrediente.idIngrediente}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                ...ingrediente,
                stock: nuevoStock
            })
        })
            .then(res => {
                if (!res.ok) throw new Error();
                setIngredientes(prev =>
                    prev.map(i =>
                        i.idIngrediente === ingrediente.idIngrediente
                            ? { ...i, stock: nuevoStock }
                            : i
                    )
                );
            })
            .catch(() => setError("No se pudo actualizar el stock"));
    };

    const eliminarIngrediente = (id: number) => {
        if (!confirm("¿Seguro que quieres eliminar este ingrediente?")) return;

        fetch(`/api/ingredientes/${id}`, {
            method: "DELETE"
        })
            .then(async res => {
                if (!res.ok) {
                    const msg = await res.text(); // ✅ AQUÍ SÍ
                    throw new Error(msg);
                }

                setIngredientes(prev =>
                    prev.filter(i => i.idIngrediente !== id)
                );
            })
            .catch(err => {
                setError(err.message); // ✅ AQUÍ SOLO MENSAJE
            });
    };

    return (
        <main>
            <h2>Inventario</h2>

            {error && <p className="error">{error}</p>}

            <Link to="nuevoIngrediente" className="crear-ingrediente">
                Nuevo Ingrediente
            </Link>

            <table>
                <thead>
                    <tr>
                        <th>Ingrediente</th>
                        <th>Alérgeno</th>
                        <th>Stock</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    {ingredientes.length === 0 && (
                        <tr>
                            <td colSpan={4}>No hay ingredientes</td>
                        </tr>
                    )}

                    {ingredientes.map(ing => (
                        <tr key={ing.idIngrediente}>
                            <td>{ing.nombre}</td>
                            <td>{ing.alergeno}</td>
                            <td>{ing.stock}</td>
                            <td>
                                <button onClick={() => actualizarStock(ing, ing.stock + 1)}>+</button>
                                <button
                                    onClick={() =>
                                        ing.stock > 0 && actualizarStock(ing, ing.stock - 1)
                                    }
                                >
                                    -
                                </button>
                                <button onClick={() => eliminarIngrediente(ing.idIngrediente)}>
                                    <svg xmlns="http://www.w3.org/2000/svg" 
                                        width="16" 
                                        height="16" 
                                        fill="currentColor" 
                                        className="bi bi-trash3" 
                                        viewBox="0 0 16 16">
                                    <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5M11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47M8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </main>
    );
}

export default Inventario;
