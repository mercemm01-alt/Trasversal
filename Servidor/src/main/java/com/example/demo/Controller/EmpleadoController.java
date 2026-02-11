package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CrearEmpleadoDTO;
import com.example.demo.Model.EmpleadoDTO;
import com.example.demo.services.EmpleadoServicio;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping
    public List<EmpleadoDTO> listar() {
        return empleadoServicio.listarEmpleados();
    }

    @PostMapping
    public void crear(@RequestBody CrearEmpleadoDTO dto) {
        empleadoServicio.crearEmpleado(dto);
    }

    @DeleteMapping("/{usuario}")
    public void eliminar(@PathVariable String usuario) {
        empleadoServicio.eliminarEmpleado(usuario);
    }
}