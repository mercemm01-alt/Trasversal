import { Link, Outlet } from "react-router-dom";
import "./CSS/admin_emple.css";

function AdminPanel() {
    return (
        <main className="panel">
            <nav className="menu">
                <Link to="fichar">Fichar</Link>
                <Link to="editar">Editar Productos</Link>
                <Link to="inventario">Inventario</Link>
                <Link to="usuarios">Administrar Usuarios</Link>
                <Link to="verPedidos">Ver Pedidos</Link>
            </nav>

            <section className="contenido">
                <Outlet />
            </section>
        </main>
    );
}

export default AdminPanel;

