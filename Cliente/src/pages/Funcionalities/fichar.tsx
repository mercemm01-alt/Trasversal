import { useState, useEffect } from "react";
import type { TramoJornada } from "../../types/Jornada";

function Fichar() {

    const usuario = localStorage.getItem("usuario");

    const [jornadas, setJornadas] = useState<TramoJornada[]>([]);
    const [inicioActual, setInicioActual] = useState<string | null>(null);
    const [error, setError] = useState<string>("");

    useEffect(() => {
        if (!usuario) return;
        // Cargar jornadas de hoy
        fetch(`/api/jornadas/hoy/${usuario}`)
            .then(res => {
                if (!res.ok) throw new Error();
                return res.json();
            })
            .then(data => {
                setJornadas(data);

                // Detecta jornada abierta
                const abierta = data.find(
                    (j: TramoJornada) => !j.fin || j.fin === "");

                if (abierta) {
                    setInicioActual(abierta.inicio);
                } else {
                    setInicioActual(null);
                }
                setError("");
            })
            .catch(() => {
                setError("No se pudo cargar la jornada");
                setJornadas([]);
                setInicioActual(null);
            });
    }, [usuario]);

    const iniciarJornada = () => {
        if (!usuario) return;

        const ahora = new Date().toLocaleTimeString("es-ES", { hour12: false });
        setInicioActual(ahora);

        fetch("/api/jornadas/inicio", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                usuario: usuario,
                horaInicio: ahora
            })
        })
        .then(res => {
            if (!res.ok) throw new Error();
        })
        .catch(() => {
            setError("No se pudo iniciar la jornada");
            setInicioActual(null);
        });
    };

    const finalizarJornada = () => {
        if (!inicioActual || !usuario) return;

        const ahora = new Date().toLocaleTimeString("es-ES", { hour12: false });

        fetch("/api/jornadas/fin", {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                usuario: usuario,
                horaSalida: ahora
            })
        })
        .then(res => {
            if (!res.ok) throw new Error();

            setJornadas(prev => [
                ...prev,
                {
                    empleado: usuario,
                    fecha: new Date().toISOString().split("T")[0],
                    inicio: inicioActual,
                    fin: ahora,
                    horas: ""
                }
            ]);

            setInicioActual(null);
        })
        .catch(() => {
            setError("No se pudo finalizar la jornada");
        });
    };

    const calcularHoras = (inicio: string, fin: string): string => {
        const inicioDate = new Date(`1970-01-01T${inicio}`);
        const finDate = new Date(`1970-01-01T${fin}`);
        const horas =
            (finDate.getTime() - inicioDate.getTime()) / 1000 / 60 / 60;
        return horas.toFixed(1);
    };

    const totalHorasDia = (): string => {
        const total = jornadas.reduce((sum, j) => {
            if (!j.fin) return sum;
            const inicio = new Date(`1970-01-01T${j.inicio}`);
            const fin = new Date(`1970-01-01T${j.fin}`);
            return sum + (fin.getTime() - inicio.getTime()) / 1000 / 60 / 60;
        }, 0);

        return total.toFixed(2);
    };

    return (
        <main>
            <h2>Fichar Jornada</h2>

            {error && <p className="error">{error}</p>}

            <div className="botones">
                <button onClick={iniciarJornada} disabled={!!inicioActual}>
                    Iniciar jornada
                </button>

                <button onClick={finalizarJornada} disabled={!inicioActual}>
                    Finalizar jornada
                </button>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Día</th>
                        <th>Hora inicio</th>
                        <th>Hora fin</th>
                        <th>Horas tramo</th>
                    </tr>
                </thead>
                <tbody>
                    {jornadas.length === 0 && (
                        <tr>
                            <td colSpan={4}>No hay datos hoy</td>
                        </tr>
                    )}

                    {jornadas.map((j, index) => (
                        <tr key={index}>
                            <td>{j.fecha}</td>
                            <td>{j.inicio}</td>
                            <td>{j.fin}</td>
                            <td>{j.fin ? calcularHoras(j.inicio, j.fin) : "-"}</td>
                        </tr>
                    ))}
                </tbody>

                {jornadas.length > 0 && (
                    <tfoot>
                        <tr>
                            <td colSpan={3}><strong>Total día</strong></td>
                            <td><strong>{totalHorasDia()}</strong></td>
                        </tr>
                    </tfoot>
                )}
            </table>
        </main>
    );
}

export default Fichar;
