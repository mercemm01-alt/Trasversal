import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

interface Ingrediente {
    idIngrediente: number;
    nombre: string;
}

function NuevoProducto() {
    const navigate = useNavigate();
    const [error, setError] = useState("");

    const [nombre, setNombre] = useState("");
    const [precio, setPrecio] = useState<number>(0);
    const [descripcion, setDescripcion] = useState("");
    const [ingredientes, setIngredientes] = useState<number[]>([]);
    const [imagen, setImagen] = useState<File | null>(null);
    const [tipo, setTipo] = useState<"PASTELERIA" | "PANADERIA">("PASTELERIA");
    const [listaIngredientes, setListaIngredientes] = useState<Ingrediente[]>([]);

    /* CARGAR INGREDIENTES DESDE INVENTARIO */
    useEffect(() => {
        fetch("/api/ingredientes")
            .then(res => res.json())
            .then(data => setListaIngredientes(data))
            .catch(() => setError("No se pudieron cargar los ingredientes"));
    }, []);
    
    const listIngrediente = (id: number) => {
        setIngredientes(prev =>
            prev.includes(id)
                ? prev.filter(i => i !== id)
                : [...prev, id]
        );
    };

    const crearProducto = (e: React.FormEvent) => {
        e.preventDefault();

        if (!nombre || ingredientes.length === 0) {
            setError("Nombre e ingredientes son obligatorios");
            return;
        }

        const producto = {
            nombre,
            precio,
            descripcion,
            ingredientes,
            tipo
        }

        const formData = new FormData();
        formData.append(
            "producto",
            new Blob([JSON.stringify(producto)], { type: "application/json" })
        );

        if (imagen) {
            formData.append("imagen", imagen);
        }

        fetch("/api/productos", {
            method: "POST",
            body: formData
        })
            .then(res => {
                if (!res.ok) throw new Error();
                navigate("/admin/editar"); // o donde muestres productos
            })
            .catch(() => setError("No se pudo crear el producto"));
    };

    return (
        <main className="crear-producto">
            <h2>Crear nuevo producto</h2>

            {error && <p className="error">{error}</p>}

            <form onSubmit={crearProducto}>
                <label>
                    Imagen
                    <input
                        type="file"
                        accept="image/*"
                        onChange={e => {
                            if (e.target.files) {
                                setImagen(e.target.files[0]);
                            }
                        }}
                    />
                </label>

                <label>
                    Nombre
                    <input
                        type="text"
                        value={nombre}
                        onChange={e => setNombre(e.target.value)}
                        required
                    />
                </label>

                <label>
                    Precio (€)
                    <input
                        type="number"
                        step="0.01"
                        value={precio}
                        onChange={e => setPrecio(Number(e.target.value))}
                        required
                    />
                </label>

                <label>
                    Descripción
                    <textarea
                        value={descripcion}
                        onChange={e => setDescripcion(e.target.value)}
                    />
                </label>

                <h4>Ingredientes</h4>
                <div className="ingredientes">
                    {listaIngredientes.map(i => (
                        <label key={i.idIngrediente}>
                            <input
                                type="checkbox"
                                checked={ingredientes.includes(i.idIngrediente)}
                                onChange={() => listIngrediente(i.idIngrediente)}
                            />
                            {i.nombre}
                        </label>
                    ))}
                </div>

                <label>
                    Tipo
                    <select
                        value={tipo}
                        onChange={e =>
                            setTipo(e.target.value as "PASTELERIA" | "PANADERIA")
                        }
                    >
                        <option value="PASTELERIA">Pastelería</option>
                        <option value="PANADERIA">Panadería</option>
                    </select>
                </label>

                <div className="acciones">
                    <button type="submit">Crear producto</button>
                    <button type="button" onClick={() => navigate("/admin/editar")}>
                        Cancelar
                    </button>
                </div>
            </form>
        </main>
    );
}

export default NuevoProducto;
