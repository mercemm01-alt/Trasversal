import {BrowserRouter as Router, Routes, Route} from "react-router-dom";

import Panaderia from "./pages/Cliente/panaderia";
import Pasteleria from "./pages/Cliente/pasteleria";

import Login from "./pages/Login/login";

import AdminPanel from "./pages/Funcionalities/AdminPanel";
import EmplePanel from "./pages/Funcionalities/EmplePanel";
import Header from "./componentes/header/header";
import Fichar from "./pages/Funcionalities/fichar";
import EditarProductos from "./pages/Funcionalities/editarProducto";
import Inventario from "./pages/Funcionalities/inventario";
import Usuarios from "./pages/Funcionalities/usuarios";

import './App.css'
import RutasSeguras from "./componentes/Rutas/rutas";

function App() {
  return (
    <Router>
    <Header />
    <Routes>
      {/* Clientes */}
      <Route path="/pasteleria" element={<Pasteleria />}></Route>
      <Route path="/panaderia" element={<Panaderia />}></Route>
      
      {/* Login */}
      <Route path="/login" element={<Login />} />

      {/* Tipo Empleado */}
      <Route path="/admin" element=
        {<RutasSeguras soloAdmin> <AdminPanel/> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="editar" element={<EditarProductos />} />
          <Route path="usuarios" element={<Usuarios />} />
      </Route>
      
      <Route path="/emple" element=
        {<RutasSeguras> <EmplePanel/> </RutasSeguras>}>
          <Route path="fichar" element={<Fichar />} />
          <Route path="inventario" element={<Inventario />} />
          <Route path="editar" element={<EditarProductos />} />
      </Route>

    </Routes>
    </Router>
  );
}

export default App;
