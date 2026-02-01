import { Link, Outlet } from "react-router-dom";

function EmplePanel(){
    return (
        <main>
            <div className="menu-admin">
                <Link to="fichar">Fichar</Link>
                <Link to="editar">Editar Productos</Link>
                <Link to="inventario">Inventario</Link>
            </div>

            <section className="contenido-emple">
                <Outlet />
            </section>
        </main>
    );
}

export default EmplePanel;