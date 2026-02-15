import "./CSS/inicio.css";
import imgFachada from "../../assets/img/fachada.jpg";
import imgVitrina from "../../assets/img/vitrina.jpg";

function Home() {


    return (
        <main className="home">

        {/* HERO - Imagen fondo + texto superpuesto */}
        <section className="hero" style={{ backgroundImage: `url(${imgFachada})` }}>

            <div className="hero__overlay">
            <div className="hero__contenido">

                <h1 className="hero__titulo">
                PASTELERÍA Y PANADERÍA LAMA 
                </h1>

                <div className="hero__subtitulo">
                    <h2>Tradición y Calidad desde 1984</h2>
                </div>

            </div>
            </div>

        </section>

        <section className="presentacion">

            <div className="presentacion__card">

            <div className="presentacion__imagenes">
                <img
                src={imgVitrina}
                alt="Productos pastelería"
                className="presentacion__img"
                />
            </div>

            <div className="presentacion__texto">
                <h2 className="presentacion__titulo">
                Tradición y calidad desde siempre
                </h2>

                <p className="presentacion__descripcion">
                Desde 1984, en Pastelería y Panadería Lama creamos dulces que despiertan emociones. 
                Cada uno de nuestros productos es elaborado en nuestro obrador con recetas que han
                pasado de generación en generación, combinando lo mejor de la tradición con un toque 
                innovador.
                </p>

                <p className="presentacion__descripcion">
                Elaboramos cada día productos frescos con ingredientes de primera calidad. 
                Desde tartas personalizadas hasta pan artesanal, combinamos tradición e innovación 
                para ofrecerte el mejor sabor.
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
