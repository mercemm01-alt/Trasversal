import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

interface Ingrediente {
    id: number;         
    nombre: string;      
    cantidad: number;    
    alergenos: string;   
}

function Inventario() {
    const [ingredientes, setIngredientes] = useState<Ingrediente[]>([]);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        fetch("/api/ingredientes")
        .then(res => {
            if (!res.ok) {
            throw new Error("Error al cargar el inventario");
            }
            return res.json();
        })
        .then(data => {
            setIngredientes(data);
        })
        .catch(() => {
            setError("Backend no disponible, usando datos de prueba");
        });
    }, []);

    const aumentarCantidad = (id: number) => {
        fetch(`/api/ingredientes/${id}/sumar`, {
        method: "PUT"
        })
        .then(res => {
            if (!res.ok) {
            throw new Error();
            }

            setIngredientes(prev =>
            prev.map(i =>
                i.id === id ? { ...i, cantidad: i.cantidad + 1 } : i
            )
            );
        })
        .catch(() => {
            setError("No se pudo aumentar o reducir la cantidad");
        });
    };

    const disminuirCantidad = (id: number) => {
        fetch(`/api/ingredientes/${id}/restar`, {
        method: "PUT"
        })
        .then(res => {
            if (!res.ok) {
            throw new Error();
            }

            setIngredientes(prev =>
            prev.map(i =>
                i.id === id && i.cantidad > 0
                ? { ...i, cantidad: i.cantidad - 1 }
                : i
            )
            );
        })
        .catch(() => {
            setError("No se pudo disminuir la cantidad");
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
                <th>Alérgenos</th>
                <th>Cantidad</th>
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
                <tr key={ing.id}>
                <td>{ing.nombre}</td>
                <td>{ing.alergenos}</td>
                <td>{ing.cantidad}</td>
                <td>
                    <button onClick={() => aumentarCantidad(ing.id)}>+</button>
                    <button onClick={() => disminuirCantidad(ing.id)}>-</button>
                </td>
                </tr>
            ))}
            </tbody>
        </table>
        </main>
    );
}

export default Inventario;
