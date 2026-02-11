import { Link, useNavigate, useLocation } from "react-router-dom";
import logo from "../../assets/img/logo.png";
import { useEffect, useState } from "react";
import './header.css'

function Header(){
    const [usuario, setUsuario] = useState<string | null>(null);
    const [rol, setRol] = useState<string | null>(null);
    const navigate = useNavigate();
    const location = useLocation();

    useEffect(() => {
        setUsuario(localStorage.getItem("usuario"));
        setRol(localStorage.getItem("rol"));
    }, [location.pathname]);


    const cerrarSesion = () => {
        localStorage.clear();
        setUsuario(null);
        setRol(null);
        navigate("/login");
    };


    return (
        <header>
            <div className="info">
                <div className="contacto">
                    <p>info@pastelerialama.es</p>
                    <p>926 89 67 74</p>
                </div>

                <div className="redes">
                    <a href="https://www.instagram.com/pastelerialama/">
                        <svg xmlns="http://www.w3.org/2000/svg" 
                            width="24" 
                            height="24" 
                            viewBox="0 0 24 24" 
                            fill="none" 
                            stroke="currentColor" 
                            stroke-width="2" 
                            stroke-linecap="round" 
                            stroke-linejoin="round" 
                            className="lucide lucide-instagram-icon lucide-instagram">
                        <rect width="20" height="20" x="2" y="2" rx="5" ry="5"/>
                        <path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"/><line x1="17.5" x2="17.51" y1="6.5" y2="6.5"/></svg>
                    </a>

                    <a href="https://www.facebook.com/p/Lama-Pasteler%C3%ADa-panader%C3%ADa-y-bolleria-100054487568378/?locale=es_ES">
                        <svg xmlns="http://www.w3.org/2000/svg" 
                            width="24" 
                            height="24" 
                            viewBox="0 0 24 24" 
                            fill="none" 
                            stroke="currentColor" 
                            stroke-width="2" 
                            stroke-linecap="round" 
                            stroke-linejoin="round" 
                            className="lucide lucide-facebook-icon lucide-facebook">
                        <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"/></svg>
                    </a>
                </div>

                <div className="usuario">
                    {/* Sin usuario */}
                    {! usuario && (
                        <Link to="/login">
                            <svg xmlns="http://www.w3.org/2000/svg" 
                                width="24" 
                                height="24" 
                                viewBox="0 0 24 24" 
                                fill="none" 
                                stroke="currentColor" 
                                stroke-width="2" 
                                stroke-linecap="round" 
                                stroke-linejoin="round" 
                                className="lucide lucide-user-icon lucide-user">
                            <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"/>
                            <circle cx="12" cy="7" r="4"/></svg>
                        </Link>
                    )}
                    {/* Con usuario */}
                    {usuario && (
                        <div className="logeado">
                            <span>Hola, {usuario}</span>

                            {/* CARRITO SOLO CLIENTE */}
                            {rol === "CLIENTE" && (
                                <Link to="/carrito" className="carrito">
                                    <svg xmlns="http://www.w3.org/2000/svg" 
                                    width="16" 
                                    height="16" 
                                    fill="currentColor" 
                                    className="bi bi-cart" 
                                    viewBox="0 0 16 16">
                                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
                                    </svg>
                                </Link>
                            )}

                            <button onClick={cerrarSesion} className="logout">Cerrar sesión</button>
                        </div>
                    )}
                </div>
            </div>
            <nav>
                <div className="menu">
                    <img src={logo} alt="Logo"/>

                    <div className="links" id="links">
                        <Link className="link-nav" to="/">INICIO</Link>
                        <Link className="link-nav" to="/pasteleria">PASTELERÍA</Link>
                        <Link className="link-nav" to="/panaderia">PANADERÍA</Link>

                        {usuario && rol === "ADMIN" && (
                        <Link className="link-nav admin" to="/admin">
                            PANEL ADMIN
                        </Link>
                        )}

                        {usuario && rol === "EMPLEADO" && (
                            <Link className="link-nav emple" to="/emple">
                                PANEL EMPLEADO
                            </Link>
                        )}
                    </div>
                </div>
            </nav>
        </header>
    )
}

export default Header;