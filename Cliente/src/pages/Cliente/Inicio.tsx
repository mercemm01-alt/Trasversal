import "./CSS/inicio.css";

import { useEffect, useState } from "react";

function Home() {

    // Carrusel automático
    const imagenes = [
    "/img/pasteleria1.jpg",
    "/img/pasteleria2.jpg",
    "/img/pasteleria3.jpg"
    ];

    const [index, setIndex] = useState(0);

    useEffect(() => {
        const intervalo = setInterval(() => {
        setIndex((prev) => (prev + 1) % imagenes.length);
        }, 4000);

        return () => clearInterval(intervalo);
    }, []);

    return (
        <main className="home">

        {/* HERO - Imagen fondo + texto superpuesto */}
        <section className="hero">

            <div className="hero__overlay">
            <div className="hero__contenido">

                <h1 className="hero__titulo">
                Endulzamos tus momentos especiales
                </h1>

                <div className="hero__lineas">
                <p className="hero__texto">Pastelería artesanal</p>
                <p className="hero__texto">Ingredientes naturales</p>
                <p className="hero__texto">Pan recién hecho cada día</p>
                <p className="hero__texto">Encargos personalizados</p>
                </div>

            </div>
            </div>

        </section>


        {/* TARJETA CON CARRUSEL */}
        <section className="presentacion">

            <div className="presentacion__card">

            <div className="presentacion__imagenes">
                <img
                src={imagenes[index]}
                alt="Productos pastelería"
                className="presentacion__img"
                />
            </div>

            <div className="presentacion__texto">
                <h2 className="presentacion__titulo">
                Tradición y calidad desde siempre
                </h2>

                <p className="presentacion__descripcion">
                En LAMA Pastelería y Panadería elaboramos cada producto
                con pasión y dedicación. Nuestra tradición artesanal
                combinada con ingredientes de primera calidad garantiza
                sabores únicos.
                </p>

                <p className="presentacion__descripcion">
                Tartas personalizadas, bollería recién hecha y pan
                artesanal cada mañana.
                </p>

            </div>

            </div>

        </section>


        {/* RESEÑAS GOOGLE */}
        <section className="resenas">

            <h2 className="resenas__titulo">
            Lo que opinan nuestros clientes
            </h2>

            <div className="resenas__grid">

            <div className="resena">
                <h3 className="resena__nombre">María G.</h3>
                <p className="resena__estrellas">★★★★★</p>
                <p className="resena__texto">
                La mejor pastelería de la zona. Las tartas personalizadas
                son increíbles y el trato es excelente.
                </p>
            </div>

            <div className="resena">
                <h3 className="resena__nombre">Carlos R.</h3>
                <p className="resena__estrellas">★★★★★</p>
                <p className="resena__texto">
                El pan está siempre recién hecho y la bollería es espectacular.
                Muy recomendable.
                </p>
            </div>

            <div className="resena">
                <h3 className="resena__nombre">Laura M.</h3>
                <p className="resena__estrellas">★★★★☆</p>
                <p className="resena__texto">
                Gran variedad de productos y excelente calidad.
                Volveré sin duda.
                </p>
            </div>

            </div>

        </section>

        </main>
    );
}

export default Home;
