import { useLocation } from "react-router-dom";
import { Link, Outlet } from "react-router-dom";
import "./CSS/admin_emple.css";

function AdminPanel() {
    const location = useLocation();
    const esHome = location.pathname === "/admin";
    return (
        <main className="panel">
            {esHome && (
            <nav className="menu-panel">
                <Link to="fichar">Fichar</Link>
                <Link to="editar">Editar Productos</Link>
                <Link to="inventario">Inventario</Link>
                <Link to="usuarios">Administrar Usuarios</Link>
                <Link to="verPedidos">Ver Pedidos</Link>
                <Link to="registros">Ver Registros</Link>
            </nav>
            )}
            <section className="contenido">
                <Outlet />
            </section>
        </main>
    );
}

export default AdminPanel;
