package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Model.ClienteLoginDTO;
import com.example.demo.services.ClienteServicio;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") 
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClienteLoginDTO loginDTO) {
        ClienteEntity cliente = clienteServicio.loginCliente(
                loginDTO.getUsuario(), 
                loginDTO.getContraseña()
        );

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    // REGISTRO 
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody ClienteEntity cliente) {
        
        ClienteEntity nuevo = clienteServicio.registrarCliente(cliente);
        
        if (nuevo != null) {
            // Si devuelve el objeto - se ha guardado bien (Código 200 OK)
            return ResponseEntity.ok(nuevo);
        } else {
            // Si devuelve null - el correo ya existía (Código 400 Bad Request)
            return ResponseEntity.badRequest().body("Error: El correo electrónico ya está registrado.");
        }
    }
}