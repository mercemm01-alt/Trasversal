import { useEffect, useState } from "react";
import "./CSS/VerRegistros.css";

interface Registro {
  nombreEmpleado: string;
  fecha: string;
  horaInicio: string;
  horaSalida: string;
  horasTrabajadas: string;
}

function VerRegistros() {

  const [registros, setRegistros] = useState<Registro[]>([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/api/jornadas/registros")
      .then(res => {
        if (!res.ok) {
          throw new Error("Error al obtener registros");
        }
        return res.json();
      })
      .then((data: Registro[]) => setRegistros(data))
      .catch(error => {
        console.error(error);
        setError("No se pudieron cargar los registros");
      });
  }, []);

  return (
    <main className="registros">
    <div style={{ padding: "20px" }}>
      <h2>Registro de Jornadas</h2>

      {error && <p style={{color: "red"}}>{error}</p>}

      <table border={1} cellPadding={10}>
        <thead>
          <tr>
            <th>Empleado</th>
            <th>Fecha</th>
            <th>Hora Entrada</th>
            <th>Hora Salida</th>
            <th>Total Horas</th>
          </tr>
        </thead>
        <tbody>
          {registros.map((r, index) => (
            <tr key={index}>
              <td>{r.nombreEmpleado}</td>
              <td>{r.fecha}</td>
              <td>{r.horaInicio}</td>
              <td>{r.horaSalida}</td>
              <td>{r.horasTrabajadas}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
    </main>
  );
}

export default VerRegistros;
