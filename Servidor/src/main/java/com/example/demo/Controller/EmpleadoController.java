package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.services.EmpleadoServicio;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<EmpleadoEntity> login(@RequestBody EmpleadoEntity datosLogin) {

        EmpleadoEntity empleado = empleadoServicio.loginEmpleado(
                datosLogin.getUsuario(),
                datosLogin.getContrasena()
        );

        if (empleado == null) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(empleado);
    }

    // LISTAR
    @GetMapping("/empleados")
    public ResponseEntity<List<EmpleadoEntity>> listEmpleados() {
        return ResponseEntity.ok(empleadoServicio.listEmpleados());
    }

    // AÑADIR
    @PostMapping("/anadirempleado")
    public ResponseEntity<EmpleadoEntity> anadirEmpleado(@RequestBody EmpleadoEntity empleado) {

        EmpleadoEntity nuevo = empleadoServicio.anadirEmpleado(
                empleado.getUsuario(),
                empleado.getContrasena(),
                empleado.getNombre(),
                empleado.getApellido(),
                empleado.getAdmin()
        );

        return ResponseEntity.ok(nuevo);
    }

    // ELIMINAR
    @DeleteMapping("/{usuario}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable String usuario) {

        empleadoServicio.eliminarEmpleado(usuario);

        return ResponseEntity.noContent().build();
    }
}