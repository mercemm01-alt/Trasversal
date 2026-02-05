import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

interface Producto {
    id: number;
    nombre: string;
    descripcion: string;
    imagen: string;
    imagenesExtra: string[];
    alergenos: string[];
    visible: boolean;
}

function EditarProductos() {
    const [productos, setProductos] = useState<Producto[]>([]);
    const [alergenos, setAlergenos] = useState<string[]>([]);
    const [error, setError] = useState("");
    const admin = localStorage.getItem("admin");

    /* CARGAR PRODUCTOS */
    useEffect(() => {
        fetch("/api/productos")
            .then(res => res.json())
            .then(data => setProductos(data))
            .catch(() => setError("No se pudieron cargar los productos"));
    }, []);

    /* CARGAR ALÉRGENOS DESDE BBDD */
    useEffect(() => {
        fetch("/api/alergenos")
            .then(res => res.json())
            .then(data => setAlergenos(data))
            .catch(() => setError("No se pudieron cargar los alérgenos"));
    }, []);

    const guardarProducto = (
        producto: Producto,
        imagenPrincipal?: File,
        imagenesExtra?: FileList
    ) => {
        const formData = new FormData();

        formData.append(
            "producto",
            new Blob([JSON.stringify(producto)], { type: "application/json" })
        );

        if (imagenPrincipal) {
            formData.append("imagen", imagenPrincipal);
        }

        if (imagenesExtra) {
            Array.from(imagenesExtra).forEach(img =>
                formData.append("imagenesExtra", img)
            );
        }

        fetch(`/api/productos/${producto.id}`, {
            method: "PUT",
            body: formData
        })
            .then(res => {
                if (!res.ok) throw new Error();
            })
            .catch(() => setError("Error al guardar el producto"));
    };

    const toggleAlergeno = (id: number, alergeno: string) => {
        setProductos(prev =>
            prev.map(p =>
                p.id === id
                    ? {
                        ...p,
                        alergenos: p.alergenos.includes(alergeno)
                            ? p.alergenos.filter(a => a !== alergeno)
                            : [...p.alergenos, alergeno]
                    }
                    : p
            )
        );
    };

    return (
        <main className="editar-productos">
            <Link to="nuevoProducto" className="crear-producto">
                Nuevo producto
            </Link>

            {error && <p className="error">{error}</p>}

            {productos.map(p => {
                let nuevaImagen: File | undefined;
                let nuevasImagenes: FileList | undefined;

                return (
                    <article key={p.id} className="producto-editar">
                        {/* IZQUIERDA */}
                        <div className="info-producto">
                            <input
                                type="text"
                                value={p.nombre}
                                onChange={e =>
                                    setProductos(prev =>
                                        prev.map(prod =>
                                            prod.id === p.id
                                                ? { ...prod, nombre: e.target.value }
                                                : prod
                                        )
                                    )
                                }
                            />

                            <textarea
                                value={p.descripcion}
                                onChange={e =>
                                    setProductos(prev =>
                                        prev.map(prod =>
                                            prod.id === p.id
                                                ? { ...prod, descripcion: e.target.value }
                                                : prod
                                        )
                                    )
                                }
                            />

                            <h4>Alérgenos</h4>
                            <div className="alergenos">
                                {alergenos.map(a => (
                                    <label key={a}>
                                        <input
                                            type="checkbox"
                                            checked={p.alergenos.includes(a)}
                                            onChange={() => toggleAlergeno(p.id, a)}
                                        />
                                        {a}
                                    </label>
                                ))}
                            </div>

                            <div className="acciones">
                                <button
                                    onClick={() =>
                                        guardarProducto(p, nuevaImagen, nuevasImagenes)
                                    }
                                >
                                    Guardar cambios
                                </button>

                                <button>
                                    {p.visible ? "Ocultar" : "Mostrar"}
                                </button>

                                {admin === "S" && (
                                    <button className="eliminar">Eliminar</button>
                                )}
                            </div>
                        </div>

                        {/* DERECHA */}
                        <div className="imagen-producto">
                            <img src={`/img/${p.imagen}`} alt={p.nombre} />

                            <label>
                                Cambiar imagen principal
                                <input
                                    type="file"
                                    accept="image/*"
                                    onChange={e => {
                                        if (e.target.files) {
                                            nuevaImagen = e.target.files[0];
                                        }
                                    }}
                                />
                            </label>

                            <label>
                                Añadir imágenes adicionales
                                <input
                                    type="file"
                                    accept="image/*"
                                    multiple
                                    onChange={e => {
                                        if (e.target.files) {
                                            nuevasImagenes = e.target.files;
                                        }
                                    }}
                                />
                            </label>
                        </div>
                    </article>
                );
            })}
        </main>
    );
}

export default EditarProductos;
