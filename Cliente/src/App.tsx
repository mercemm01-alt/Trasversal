import {BrowserRouter as Router, Routes, Route} from "react-router-dom";

import ListarProductos from "./pages/Cliente/listaProductos";

import Login from "./pages/Login/login";

import AdminPanel from "./pages/Funcionalities/AdminPanel";
import EmplePanel from "./pages/Funcionalities/EmplePanel";
import Header from "./componentes/header/header";
import Fichar from "./pages/Funcionalities/fichar";
import EditarProductos from "./pages/Funcionalities/editarProducto";
import Inventario from "./pages/Funcionalities/inventario";
import Usuarios from "./pages/Funcionalities/usuarios";
import NuevoProducto from "./pages/Funcionalities/nuevoProducto";
import NuevoIngrediente from "./pages/Funcionalities/nuevoIngrediente";

import './App.css'
import RutasSeguras from "./componentes/Rutas/rutas";
import Encargos from "./pages/Cliente/encargos";

function App() {
  return (
    <Router>
    <Header />
    <Routes>
      {/* Clientes */}
      <Route path="/pasteleria" element={<ListarProductos url="http://localhost:8080/api/productos/pasteleria"/>}></Route> {/* Pasteleria */}
      <Route path="/panaderia" element={<ListarProductos url="http://localhost:8080/api/productos/panaderia"/>}></Route> {/* Panaderia */}
      <Route path="/encargos" element={<Encargos/>}></Route>
      
      {/* Login */}
      <Route path="/login" element={<Login />} />

      {/* Tipo Empleado */}
      <Route path="/admin" element=
        {<RutasSeguras soloAdmin> <AdminPanel/> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="inventario/nuevoIngrediente" element={<NuevoIngrediente/>}></Route>
          <Route path="editar" element={<EditarProductos />} />
          <Route path="editar/nuevoProducto" element={<NuevoProducto/>}></Route>
          <Route path="usuarios" element={<Usuarios />} />
      </Route>
      
      <Route path="/emple" element=
        {<RutasSeguras> <EmplePanel/> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="inventario/nuevoIngrediente" element={<NuevoIngrediente/>}></Route>
          <Route path="editar" element={<EditarProductos />} />
          <Route path="editar/nuevoProducto" element={<NuevoProducto/>}></Route>
      </Route>

    </Routes>
    </Router>
  );
}

export default App;
