import type { Producto } from "../../types/Producto";
import { useCarrito } from "../../pages/Funcionalities/carritoCompra";
import "./tarjetaProducto.css";

function TarjetaProducto({ producto }: { producto: Producto }) {
    const { agregarProducto } = useCarrito();
    const rol = localStorage.getItem("rol");
    
    return (
        <article className="tj-producto">
            <img src={`img/${producto.imagen}`} alt={producto.nombre}/>
            <div className="contenido-producto">
                <h3>{producto.nombre}</h3>

                <p>{producto.descripcion}</p>

                <p className="alergenos">
                    Alérgenos:{" "}
                    {producto.alergenos.length > 0
                        ? producto.alergenos.join(", ")
                        : "Sin alérgenos"}
                </p>

                {rol === "CLIENTE" && (
                    <div className="acciones">
                        <p className="precio">{producto.precio} €</p>
                        <button onClick={() =>
                            agregarProducto({
                                id: producto.idProducto,
                                nombre: producto.nombre,
                                precio: producto.precio,
                                cantidad: 1
                            })
                        }>
                            Añadir al carrito
                        </button>
                    </div>
                )}
            </div>
        </article>
    );
}

export default TarjetaProducto;