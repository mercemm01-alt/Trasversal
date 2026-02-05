function TarjetaProducto({ producto }: any) {
    return (
        <article className="tj-producto">
            <img src={`http://localhost:8080/img/${producto.imagen}`}
                alt={producto.nombre}/>
            <h3>{producto.nombre}</h3>
            <p>{producto.descripcion}</p>
            <p className="alergenos">
                Alérgenos: {producto.alergenos.join(", ")}
            </p>
        </article>
    );
}

export default TarjetaProducto;