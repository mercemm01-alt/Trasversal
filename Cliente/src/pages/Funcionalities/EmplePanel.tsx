import { Link, Outlet } from "react-router-dom";
import "./CSS/admin_emple.css";
import { useLocation } from "react-router-dom";

function EmplePanel(){
    const location = useLocation();
    const esHome = location.pathname === "/emple";
    return (
        <main className="panel">
            {esHome && (
            <nav className="menu-panel">
                <Link to="fichar">Fichar</Link>
                <Link to="editar">Editar Productos</Link>
                <Link to="inventario">Inventario</Link>
                <Link to="verPedidos">Ver Pedidos</Link>
            </nav>
            )}
            <section className="contenido">
                <Outlet />
            </section>
        </main>
    );
}

export default EmplePanel;