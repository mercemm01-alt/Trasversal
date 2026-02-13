import { useEffect, useState } from "react";
import type { Pedido } from "../../types/Pedido";
import BeneficiosStats from "./BeneficiosStats";

function VerPedidos() {

    const [pedidos, setPedidos] = useState<Pedido[]>([]);
    const [cargando, setCargando] = useState(true);

    const cargarPedidos = async () => {
        try {
            const res = await fetch("/api/pedidos/mes");
            const data = await res.json();
            setPedidos(data);
        } catch (error) {
            console.error("Error cargando pedidos", error);
        } finally {
            setCargando(false);
        }
    };

    const marcarEntregado = async (idPedido: number) => {
        await fetch(`/api/pedidos/${idPedido}`, {
            method: "PUT"
        });
        cargarPedidos();
    };

    useEffect(() => {
        cargarPedidos();
    }, []);

    if (cargando) {
        return <p>Cargando pedidos...</p>;
    }

    return (
        <main>
            <h2>Pedidos del mes</h2>

            <div style={{ marginBottom: "20px" }}>
                <BeneficiosStats />
            </div>

            {pedidos.length === 0 && <p>No hay pedidos este mes</p>}

            {pedidos.map(p => (
                <div key={p.idPedido} style={{ border: "1px solid #ccc", padding: "10px", marginBottom: "10px" }}>
                    <p>
                        <strong>Cliente:</strong> {p.nombreCliente} {p.apellidosCliente}
                    </p>
                    <p>
                        <strong>Fecha pedido:</strong> {new Date(p.fechaPedido).toLocaleDateString()}
                    </p>
                    <p>
                        <strong>Fecha entrega:</strong> {new Date(p.fechaEntrega).toLocaleDateString()}
                    </p>
                    <p>
                        <strong>Total:</strong> {p.total} €
                    </p>
                    <p>
                        <strong>Estado:</strong> {p.estado}
                    </p>

                    {p.estado === "EN_PROCESO" && (
                        <button onClick={() => marcarEntregado(p.idPedido)}>
                            Marcar como entregado
                        </button>
                    )}
                </div>
            ))}
        </main>
    );
}

export default VerPedidos;
