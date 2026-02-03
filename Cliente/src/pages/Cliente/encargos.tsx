import { useState } from "react";


function Encargos() {
  /* ===============================
     ESTADOS
     =============================== */
    const [nombre, setNombre] = useState("");
    const [apellidos, setApellidos] = useState("");
    const [correo, setCorreo] = useState("");
    const [telefono, setTelefono] = useState("");
    const [relleno, setRelleno] = useState("");
    const [tamano, setTamano] = useState("");
    const [observaciones, setObservaciones] = useState("");

    const [errores, setErrores] = useState<{ [key: string]: string }>({});
    const [mensajeFinal, setMensajeFinal] = useState("");
    const [exito, setExito] = useState<boolean | null>(null);

  /* ===============================
     VALIDACIÓN BÁSICA (FRONTEND)
     =============================== */
    const validar = () => {
        const nuevosErrores: { [key: string]: string } = {};

        if (!nombre) nuevosErrores.nombre = "Es obligatorio rellenar este campo";
        if (!apellidos) nuevosErrores.apellidos = "Es obligatorio rellenar este campo";
        if (!correo) nuevosErrores.correo = "Es obligatorio rellenar este campo";
        if (!telefono) nuevosErrores.telefono = "Es obligatorio rellenar este campo";
        if (!relleno) nuevosErrores.relleno = "Es obligatorio rellenar este campo";
        if (!tamano) nuevosErrores.tamano = "Es obligatorio rellenar este campo";
        if (!observaciones) nuevosErrores.observaciones = "Es obligatorio rellenar este campo";

        setErrores(nuevosErrores);
        return Object.keys(nuevosErrores).length === 0;
    };

  /* ===============================
     ENVÍO DEL ENCARGO
     =============================== */
    const enviarPedido = (e: React.FormEvent) => {
        e.preventDefault();

        setMensajeFinal("");
        setExito(null);

        // Si faltan campos → no se envía nada al servidor
        if (!validar()) {
        setMensajeFinal("Faltan campos por rellenar");
        return;
        }

        // React SOLO envía los datos básicos
        fetch("/api/encargos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nombre,
            apellidos,
            correo,
            telefono,
            relleno,
            tamano,
            observaciones
        })
        })
        .then(res => {
            if (!res.ok) throw new Error();
            setExito(true);
            setMensajeFinal("Se ha realizado el pedido correctamente");
        })
        .catch(() => {
            setExito(false);
            setMensajeFinal("No se puede realizar su pedido");
        });
    };

    return (
        <main className="encargos">
        <h2>Encargos de Tartas</h2>

        <form onSubmit={enviarPedido}>
            {/* NOMBRE */}
            <label>
            Nombre
            <input value={nombre} onChange={e => setNombre(e.target.value)} />
            {errores.nombre && <p className="error">{errores.nombre}</p>}
            </label>

            {/* APELLIDOS */}
            <label>
            Apellidos
            <input value={apellidos} onChange={e => setApellidos(e.target.value)} />
            {errores.apellidos && <p className="error">{errores.apellidos}</p>}
            </label>

            {/* CORREO */}
            <label>
            Correo
            <input type="email" value={correo} onChange={e => setCorreo(e.target.value)} />
            {errores.correo && <p className="error">{errores.correo}</p>}
            </label>

            {/* TELÉFONO */}
            <label>
            Teléfono
            <input value={telefono} onChange={e => setTelefono(e.target.value)} />
            {errores.telefono && <p className="error">{errores.telefono}</p>}
            </label>

            {/* RELLENO */}
            <fieldset>
            <legend>Tipo de relleno</legend>
            {["Chocolate", "Crema", "Turrón", "Oreo", "Kinder"].map(r => (
                <label key={r}>
                <input
                    type="radio"
                    name="relleno"
                    value={r}
                    checked={relleno === r}
                    onChange={e => setRelleno(e.target.value)}
                />
                {r}
                </label>
            ))}
            {errores.relleno && <p className="error">{errores.relleno}</p>}
            </fieldset>

            {/* TAMAÑO */}
            <fieldset>
            <legend>Tamaño de la tarta (Personas)</legend>
            {["4", "8", "12", "24"].map(t => (
                <label key={t}>
                <input
                    type="radio"
                    name="tamano"
                    value={t}
                    checked={tamano === t}
                    onChange={e => setTamano(e.target.value)}
                />
                {t} 
                </label>
            ))}
            {errores.tamano && <p className="error">{errores.tamano}</p>}
            </fieldset>

            {/* OBSERVACIONES */}
            <label>
            Observaciones
            <textarea
                maxLength={250}
                value={observaciones}
                onChange={e => setObservaciones(e.target.value)}
            />
            {errores.observaciones && (
                <p className="error">{errores.observaciones}</p>
            )}
            </label>

            {/* BOTÓN */}
            <button type="submit">Realizar pedido</button>

            {/* MENSAJE GENERAL */}
            {mensajeFinal && (
            <p className={exito ? "mensaje-exito" : "mensaje-error"}>
                {mensajeFinal}
            </p>
            )}
        </form>

        {/* MODAL CENTRADO */}
        {exito !== null && (
            <div className="modal">
            <div className="modal-contenido">
                <p>{mensajeFinal}</p>
                <button onClick={() => setExito(null)}>Cerrar</button>
            </div>
            </div>
        )}
        </main>
    );
}

export default Encargos;
