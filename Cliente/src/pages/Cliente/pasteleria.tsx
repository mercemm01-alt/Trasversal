import { useEffect, useState } from "react";
import TarjetaProducto from "../../componentes/tarjetaProducto/tarjetaProducto";

function Pasteleria() {
    const [productos, setProductos] = useState<any[]>([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/productos/pasteleria")
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

export default Pasteleria;