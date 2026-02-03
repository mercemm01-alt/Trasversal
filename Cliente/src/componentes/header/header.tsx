import { Link, useNavigate } from "react-router-dom";
import logo from "../../assets/img/logo.png";
import './header.css'

function Header(){
    const usuario = localStorage.getItem("usuario");
    const admin = localStorage.getItem("admin");
    const navigate = useNavigate();

    const cerrarSesion = () => {
        localStorage.removeItem("usuario");
        localStorage.removeItem("admin"),
        navigate("/login");
    }

    return (
        <header>
            <div className="info">
                <p>info@pastelerialama.es</p>
                <p>926 89 67 74</p>
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

                        {admin === "S" && <Link to="/admin">Panel Admin</Link>}
                        {admin === "N" && <Link to="/empleado">Panel Empleado</Link>}

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
                        <Link className="link-nav" to="/encargos">ENCARGOS</Link>
                    </div>
                </div>
            </nav>
        </header>
    )
}

export default Header;