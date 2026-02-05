import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ALERGENOS = [
    "GLUTEN","CRUSTACEOS","HUEVOS","PESCADO","CACAHUETES",
    "SOJA","LECHE","FRUTOS_CASCARA","APIO","MOSTAZA",
    "SESAMO","SULFITOS","ALTRAMUCES","MOLUSCOS"
];

function NuevoProducto() {
    const navigate = useNavigate();
    const [error, setError] = useState("");

    const [nombre, setNombre] = useState("");
    const [precio, setPrecio] = useState<number>(0);
    const [descripcion, setDescripcion] = useState("");
    const [alergenos, setAlergenos] = useState<string[]>([]);
    const [imagen, setImagen] = useState<File | null>(null);
    const [imagenesExtra, setImagenesExtra] = useState<FileList | null>(null);

    const listaAlergeno = (a: string) => {
        setAlergenos(prev =>
        prev.includes(a) ? prev.filter(x => x !== a) : [...prev, a]
        );
    };

    const crearProducto = (e: React.FormEvent) => {
        e.preventDefault();

        const producto = {
        nombre,
        precio,
        descripcion,
        alergenos
        };

        const formData = new FormData();
        formData.append(
        "producto",
        new Blob([JSON.stringify(producto)], { type: "application/json" })
        );

        if (imagen) {
        formData.append("imagen", imagen);
        }

        if (imagenesExtra) {
        Array.from(imagenesExtra).forEach(img =>
            formData.append("imagenesExtra", img)
        );
        }

        fetch("/api/productos", {
        method: "POST",
        body: formData
        })
        .then(res => {
            if (!res.ok) throw new Error();
            navigate("/admin/editar"); // o donde tengas la lista
        })
        .catch(() => {
            setError("No se pudo crear el producto");
        });
    };

    return (
        <main className="crear-producto">
        <h2>Crear nuevo producto</h2>

        {error && <p className="error">{error}</p>}

        <form onSubmit={crearProducto}>
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

            <h4>Alérgenos</h4>
            <div className="alergenos">
            {ALERGENOS.map(a => (
                <label key={a}>
                <input
                    type="checkbox"
                    checked={alergenos.includes(a)}
                    onChange={() => listaAlergeno(a)}
                />
                {a}
                </label>
            ))}
            </div>

            <label>
            Imagen principal
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
            Imágenes adicionales
            <input
                type="file"
                accept="image/*"
                multiple
                onChange={e => {
                if (e.target.files) {
                    setImagenesExtra(e.target.files);
                }
                }}
            />
            </label>

            <div className="acciones">
            <button type="submit">Crear producto</button>
            <button type="button" onClick={() => navigate(-1)}>
                Cancelar
            </button>
            </div>
        </form>
        </main>
    );
}

export default NuevoProducto;
