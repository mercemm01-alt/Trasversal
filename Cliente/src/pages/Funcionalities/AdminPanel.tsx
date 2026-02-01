import { Link, Outlet } from "react-router-dom";

function AdminLayout() {
    return (
        <main>
        <div className="menu-admin">
            <Link to="fichar">Fichar</Link>
            <Link to="editar">Editar Productos</Link>
            <Link to="inventario">Inventario</Link>
            <Link to="usuarios">Administrar Usuarios</Link>
        </div>

        <section className="contenido-admin">
            <Outlet />
        </section>
        </main>
    );
}

export default AdminLayout;

