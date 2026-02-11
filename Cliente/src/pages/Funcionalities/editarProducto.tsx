import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import type { Producto } from "../../types/Producto";

interface Ingrediente {
    idIngrediente: number;
    nombre: string;
}

function EditarProductos() {
    const [productos, setProductos] = useState<Producto[]>([]);
    const [ingredientes, setIngredientes] = useState<Ingrediente[]>([]);
    const [error, setError] = useState("");

    
       //CARGAR PRODUCTOS
    useEffect(() => {
        fetch("/api/productos")
            .then(res => res.json())
            .then(data => setProductos(data))
            .catch(() => setError("No se pudieron cargar los productos"));
    }, []);

       //CARGAR INGREDIENTES (inventario)
    useEffect(() => {
        fetch("/api/ingredientes")
            .then(res => res.json())
            .then(data => setIngredientes(data))
            .catch(() => setError("No se pudieron cargar los ingredientes"));
    }, []);

       //TOGGLE INGREDIENTE
    const toggleIngrediente = (idProducto: number, idIngrediente: number) => {
        setProductos(prev =>
            prev.map(p =>
                p.idProducto === idProducto
                    ? {
                        ...p,
                        ingredientes: p.ingredientes
                            ? p.ingredientes.includes(idIngrediente)
                                ? p.ingredientes.filter(i => i !== idIngrediente)
                                : [...p.ingredientes, idIngrediente]
                            : [idIngrediente]
                    }
                    : p
            )
        );
    };

    const eliminarProducto = (idProducto: number) => {
        if (!confirm("¿Seguro que quieres eliminar este producto?")) return;

        fetch(`/api/productos/${idProducto}`, {
            method: "DELETE"
        })
            .then(res => {
                if (!res.ok) throw new Error();

                // Quitar el producto de la lista
                setProductos(prev =>
                    prev.filter(p => p.idProducto !== idProducto)
                );
            })
            .catch(() => setError("No se pudo eliminar el producto"));
    };

       //GUARDAR PRODUCTO
    const guardarProducto = (
        producto: Producto,
        imagenPrincipal?: File
    ) => {
        const formData = new FormData();

        const productoEnviar = {
            nombre: producto.nombre,
            precio: producto.precio,
            descripcion: producto.descripcion,
            tipo: producto.tipo,
            ingredientes: producto.ingredientes
        };

        formData.append(
            "producto",
            new Blob([JSON.stringify(productoEnviar)], {
                type: "application/json"
            })
        );

        if (imagenPrincipal) {
            formData.append("imagen", imagenPrincipal);
        }

        fetch(`/api/productos/${producto.idProducto}`, {
            method: "PUT",
            body: formData
        })
            .then(res => {
                if (!res.ok) throw new Error();
            })
            .catch(() => setError("Error al guardar el producto"));
    };

    return (
        <main className="editar-productos">
            <Link to="nuevoProducto" className="crear-producto">
                Nuevo producto
            </Link>

            {error && <p className="error">{error}</p>}

            {productos.map(p => {
                let nuevaImagen: File | undefined;

                return (
                    <article key={p.idProducto} className="producto-editar">
                        <div className="imagen-producto">
                            <img src={`/img/${p.imagen}`} alt={p.nombre} />

                            <input
                                type="file"
                                accept="image/*"
                                onChange={e => {
                                    if (e.target.files) {
                                        nuevaImagen = e.target.files[0];
                                    }
                                }}
                            />
                        </div>

                        <div className="info-producto">
                            <label>Nombre</label>
                            <input
                                type="text"
                                value={p.nombre}
                                onChange={e =>
                                    setProductos(prev =>
                                        prev.map(prod =>
                                            prod.idProducto === p.idProducto
                                                ? { ...prod, nombre: e.target.value }
                                                : prod
                                        )
                                    )
                                }
                            />

                            <label>Descripción</label>
                            <textarea
                                value={p.descripcion}
                                onChange={e =>
                                    setProductos(prev =>
                                        prev.map(prod =>
                                            prod.idProducto === p.idProducto
                                                ? { ...prod, descripcion: e.target.value }
                                                : prod
                                        )
                                    )
                                }
                            />

                            {/* INGREDIENTES (EDITABLE)*/}

                            <h4>Ingredientes</h4>
                            <div className="ingredientes">
                                {ingredientes.map(i => (
                                    <label key={i.idIngrediente}>
                                        <input
                                            type="checkbox"
                                            checked={p.ingredientes?.includes(i.idIngrediente) ?? false}
                                            onChange={() =>
                                                toggleIngrediente(
                                                    p.idProducto,
                                                    i.idIngrediente
                                                )
                                            }
                                        />
                                        {i.nombre}
                                    </label>
                                ))}
                            </div>

                            {/* ALÉRGENOS (SOLO INFORMACIÓN) */}
                            <h4>Alérgenos</h4>
                            <p>{p.alergenos.join(", ") || "Sin alérgenos"}</p>

                            <label>
                                Tipo
                                <select
                                    value={p.tipo}
                                    onChange={e =>
                                        setProductos(prev =>
                                            prev.map(prod =>
                                                prod.idProducto === p.idProducto
                                                    ? {
                                                        ...prod,
                                                        tipo: e.target.value as any
                                                    }
                                                    : prod
                                            )
                                        )
                                    }
                                >
                                    <option value="PASTELERIA">Pastelería</option>
                                    <option value="PANADERIA">Panadería</option>
                                </select>
                            </label>

                            <div className="acciones">
                                <button
                                    onClick={() =>
                                        guardarProducto(p, nuevaImagen)
                                    }
                                >Guardar cambios</button>

                                <button
                                    className="eliminar"
                                    onClick={() => eliminarProducto(p.idProducto)}
                                >Eliminar producto</button>
                            </div>
                        </div>
                    </article>
                );
            })}
        </main>
    );
}

export default EditarProductos;
