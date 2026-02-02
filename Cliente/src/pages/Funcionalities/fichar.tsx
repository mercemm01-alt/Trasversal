import { useState, useEffect } from "react";

interface TramoJornada {
    empleado: string | null;
    fecha: string;
    inicio: string;
    fin: string;
}

function Fichar() {
    const [jornadas, setJornadas] = useState<TramoJornada[]>([]);
    const [inicioActual, setInicioActual] = useState<string | null>(null);
    const [fechaHoy, setFechaHoy] = useState<string>("");
    const [error, setError] = useState<string>("");

    const empleado = localStorage.getItem("usuario");

    useEffect(() => {
        const hoy = new Date().toLocaleDateString();
        setFechaHoy(hoy);

        if (!empleado) return;

        fetch(`/api/jornadas/hoy/${empleado}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => {
            setJornadas(data);
            setError("");
        })
        .catch(() => {
            setError("No se pudo conectar con el servidor");
        });
    }, [empleado]);

    const iniciarJornada = () => {
        if (!empleado) return;

        const ahora = new Date().toLocaleTimeString();
        setInicioActual(ahora);

        fetch("/api/jornadas/inicio", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            empleado: empleado,
            fecha: fechaHoy,
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
        if (!inicioActual || !empleado) return;

        const ahora = new Date().toLocaleTimeString();

        fetch("/api/jornadas/fin", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            empleado: empleado,
            fecha: fechaHoy,
            horaSalida: ahora
        })
        })
        .then(res => {
            if (!res.ok) throw new Error();

            setJornadas(prev => [
            ...prev,
            {
                empleado: empleado,
                fecha: fechaHoy,
                inicio: inicioActual,
                fin: ahora
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
        const inicio = new Date(`1970-01-01T${j.inicio}`);
        const fin = new Date(`1970-01-01T${j.fin}`);
        return sum + (fin.getTime() - inicio.getTime()) / 1000 / 60 / 60;
        }, 0);

        return total.toFixed(2);
    };

    return (
        <main>
        <h2>Fichar Jornada</h2>

        <p><strong>Fecha:</strong> {fechaHoy}</p>

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
                <td>{calcularHoras(j.inicio, j.fin)}</td>
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
