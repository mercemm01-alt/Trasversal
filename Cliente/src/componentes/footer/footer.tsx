import './footer.css';

const footer = () => {
    return (
        <footer className="footer">
            <div className="footer-container">

                {/* COLUMNA 1: LOGO Y DESCRIPCIÓN */}
                <div className="footer-section">
                    <h3>Pastelería Lama</h3>
                    <p>
                        Desde 1990 elaborando productos artesanales con la mejor calidad.
                        Tradición familiar y sabor auténtico en cada bocado.
                    </p>
                    <div className="social-icons">
                        <a href="https://www.facebook.com/p/Lama-Pasteler%C3%ADa-panader%C3%ADa-y-bolleria-100054487568378/?locale=es_ES" title="Facebook">f</a>
                        <a href="https://www.instagram.com/pastelerialama/" title="Instagram">ig</a>
                    </div>
                </div>

                {/* COLUMNA 2: ENLACES RÁPIDOS */}
                <div className="footer-section">
                    <h3>Explorar</h3>
                    <ul className="footer-links">
                        <li><a href="/">Inicio</a></li>
                        <li><a href="/productos">Nuestros Productos</a></li>
                        <li><a href="/login">Área Empleados</a></li>
                        <li><a href="/contacto">Contacto</a></li>
                        <li><a href="#">Aviso Legal</a></li>
                    </ul>
                </div>

                {/* COLUMNA 3: CONTACTO REAL */}
                <div className="footer-section">
                    <h3>Contacto</h3>
                    <p>📍 Ctra. de Urda 1, 13670 <br /> Villarrubia de los Ojos (Ciudad Real)</p>
                    <p>📞 926 89 67 74</p>
                    <p>📱 660 87 84 19</p>
                    <p>✉️ info@pastelerialama.es</p>
                    <p>🕒 L-D: 09:00 - 14:00 / 17:00 - 20:30</p>
                </div>

            </div>

            {/* BARRA INFERIOR DE COPYRIGHT */}
            <div className="footer-bottom">
                &copy; {new Date().getFullYear()} Pastelería Lama. Todos los derechos reservados.
                | Desarrollado por Alumnos DAW
            </div>
        </footer>
    );
};

export default footer;