import { useEffect, useState } from "react";
import TarjetaProducto from "../../componentes/tarjetaProducto/tarjetaProducto";
import type { Producto } from "../../types/Producto";
import "./CSS/listaProductos.css";

function ListarProducto({url}: { url: string }) {
    const [productos, setProductos] = useState<Producto[]>([]);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        fetch(url)
        .then(res =>{
            if(!res.ok){
                throw new Error("Error al cargar productos");
            }
            return res.json();
        })
        .then((data: Producto[]) => setProductos(data))
        .catch((err) => {
        console.error(err);
        setError("No se pudieron cargar los productos");
        });}, [url]);

        if (error) {
            return <p>{error}</p>;
        }

    return (
        <main className="productos">
            {productos.length === 0 ? (
            <p>No hay productos disponibles</p>) : (
            productos.map((producto) => (
            <TarjetaProducto key={producto.idProducto} producto={producto}/>
        )))}
    </main>
    );
}

export default ListarProducto;