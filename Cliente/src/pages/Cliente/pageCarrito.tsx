import { useState } from "react";
import { useCarrito } from "../../pages/Funcionalities/carritoCompra";
import jsPDF from "jspdf";
import "./CSS/carrito.css";

function Carrito() {
    const { carrito, eliminarProducto, vaciarCarrito } = useCarrito();
    const [fechaEntrega, setFechaEntrega] = useState("");

    const total = carrito.reduce(
        (sum, p) => sum + p.precio * p.cantidad,
        0
    );

    // Fecha mínima = hoy + 48h
    const minDate = () => {
        const d = new Date();
        d.setDate(d.getDate() + 2);
        return d.toISOString().split("T")[0];
    };

    const generarPDF = () => {
        const pdf = new jsPDF();
        pdf.text("Pastelería Panaderia Lama", 20, 20);
        pdf.text(`Fecha entrega: ${fechaEntrega}`, 20, 30);

        let y = 40;
        carrito.forEach(p => {
            pdf.text(
                `${p.nombre} x${p.cantidad} - ${p.precio * p.cantidad} €`,
                20,
                y
            );
            y += 10;
        });

        pdf.text(`TOTAL: ${total} €`, 20, y + 10);
        pdf.save("pedido.pdf");
    };

    const realizarPedido = async () => {
        if (!fechaEntrega) {
            alert("Selecciona una fecha de recogida");
            return;
        }

        console.log(JSON.stringify({
            usuario: Number(localStorage.getItem("idUsuario")),
            fechaEntrega,
            total,
            productos: carrito.map(p => ({
                idProducto: p.id,
                cantidad: p.cantidad
            }))
        }));

        await fetch("/api/pedidos", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                usuario: Number(localStorage.getItem("idUsuario")),
                fechaEntrega,
                total,
                productos: carrito.map(p => ({
                    idProducto: p.id,
                    cantidad: p.cantidad
                }))
            })
        });

        generarPDF();
        vaciarCarrito();
        alert("Pedido realizado correctamente");
    };

    return (
        <main className="mainCarrito">
            <h2>Carrito</h2>

            {carrito.length === 0 && <p>El carrito está vacío</p>}

            {carrito.map(p => (
                <div key={p.id} className="productos-carrito">
                    <span>
                        {p.nombre} x {p.cantidad}
                    </span>
                    <span> Precio: {p.precio * p.cantidad} €</span>
                    <button onClick={() => eliminarProducto(p.id)} className="bt-elim">
                        Eliminar
                    </button>
                </div>
            ))}

            {carrito.length > 0 && (
                <>
                <div  className="contenedor-fecha">
                    <label className="fecha">Fecha de recogida:</label>
                    <input
                    className="elegir-fehca"
                        type="date"
                        min={minDate()}
                        value={fechaEntrega}
                        onChange={e => setFechaEntrega(e.target.value)}
                    />
                </div>
                

                    <h3 className="total">Total: {total} €</h3>
                    <button onClick={realizarPedido} className="bt-comprar">Comprar</button>
                </>
            )}
        </main>
    );
}

export default Carrito;
