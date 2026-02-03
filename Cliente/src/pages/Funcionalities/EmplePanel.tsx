import { Link, Outlet } from "react-router-dom";
import "./CSS/admin_emple.css";

function EmplePanel(){
    return (
        <main className="panel">
            <nav className="menu">
                <Link to="fichar">Fichar</Link>
                <Link to="editar">Editar Productos</Link>
                <Link to="inventario">Inventario</Link>
            </nav>

            <section className="contenido">
                <Outlet />
            </section>
        </main>
    );
}

export default EmplePanel;