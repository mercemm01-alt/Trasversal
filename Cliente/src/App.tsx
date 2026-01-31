import {BrowserRouter as Router, Routes, Route} from "react-router-dom";

import Panaderia from "./pages/Cliente/panaderia";
import Pasteleria from "./pages/Cliente/pasteleria";

import Login from "./pages/Login/login";

import AdminPanel from "./pages/Funcionalities/AdminPanel";
import Header from "./componentes/header/header";
import Fichar from "./pages/Funcionalities/fichar";
import EditarProductos from "./pages/Funcionalities/editarProducto";
//import Inventario from "./pages/Funcionalities/inventario";
//import Usuarios from "./pages/Funcionalities/usuarios";

import './App.css'

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

      {/* Funcionalities */}
      <Route path="control" element={<AdminPanel />} />
      <Route path="control/fichar" element={<Fichar />} />
      <Route path="control/editar" element={<EditarProductos />}></Route>
      {/* <Route path="control/inventario" element={<Inventario />} />
      <Route path="control/usuarios" element={<Usuarios />} /> */}
    </Routes>
    </Router>
  );
}

export default App;
