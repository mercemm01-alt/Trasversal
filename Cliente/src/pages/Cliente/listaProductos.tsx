import { useEffect, useState } from "react";
import TarjetaProducto from "../../componentes/tarjetaProducto/tarjetaProducto";

function ListaProducto({url}: {url:string}) {
    const [productos, setProductos] = useState<any[]>([]);

    useEffect(() => {
        fetch(url)
        .then(res => res.json())
        .then(data => setProductos(data))
        .catch(err => console.error(err)); //Captura Errores
    }, []);

    return (
        <main className="productos">
        {productos.map(producto => (
            <TarjetaProducto key={producto.id} producto={producto} />
        ))}
        </main>
    );
}

export default ListaProducto;