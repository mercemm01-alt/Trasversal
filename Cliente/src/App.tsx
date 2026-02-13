import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Inicio from "./pages/Cliente/Inicio";
import ListarProducto from "./pages/Cliente/listaProductos";
import Carrito from "./pages/Cliente/pageCarrito";

import Login from "./pages/Login/login";
import Registro from "./pages/Login/registro";

import AdminPanel from "./pages/Funcionalities/AdminPanel";
import EmplePanel from "./pages/Funcionalities/EmplePanel";
import Header from "./componentes/header/header";
import Fichar from "./pages/Funcionalities/fichar";
import EditarProductos from "./pages/Funcionalities/editarProducto";
import Inventario from "./pages/Funcionalities/inventario";
import VerRegistros from "./pages/Funcionalities/VerRegistros";
import Usuarios from "./pages/Funcionalities/usuarios";
import NuevoProducto from "./pages/Funcionalities/nuevoProducto";
import NuevoIngrediente from "./pages/Funcionalities/nuevoIngrediente";
import VerPedidos from "./pages/Funcionalities/verPedidos"

import './App.css'
import RutasSeguras from "./componentes/Rutas/rutas";
import Footer from "./componentes/footer/footer";

function App() {
  return (
    <Router>
      <Header />

      <Routes>
        <Route path="/" element={<Inicio />}></Route>
        {/* Clientes */}
        <Route path="/pasteleria" element={<ListarProducto url="/api/productos/pasteleria" />}></Route> {/* Pasteleria */}
        <Route path="/panaderia" element={<ListarProducto url="/api/productos/panaderia" />}></Route> {/* Panaderia */}
        <Route path="/carrito" element={
          <RutasSeguras rolPermitido="CLIENTE"><Carrito /></RutasSeguras>}></Route>

        {/* Login */}
        <Route path="/login" element={<Login />} />
        <Route path="/registro" element={<Registro />} />

        {/* Tipo Empleado */}
        <Route path="/admin" element=
          {<RutasSeguras rolPermitido="ADMIN"> <AdminPanel /> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="inventario/nuevoIngrediente" element={<NuevoIngrediente />}></Route>
          <Route path="editar" element={<EditarProductos />} />
          <Route path="editar/nuevoProducto" element={<NuevoProducto />}></Route>
          <Route path="usuarios" element={<Usuarios />} />
          <Route path="verPedidos" element={<VerPedidos />} />
          <Route path="registros" element={<VerRegistros />} />
        </Route>

        <Route path="/emple" element=
          {<RutasSeguras rolPermitido="EMPLEADO"> <EmplePanel /> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="inventario/nuevoIngrediente" element={<NuevoIngrediente />}></Route>
          <Route path="editar" element={<EditarProductos />} />
          <Route path="editar/nuevoProducto" element={<NuevoProducto />}></Route>
          <Route path="verPedidos" element={<VerPedidos />} />
        </Route>

      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
