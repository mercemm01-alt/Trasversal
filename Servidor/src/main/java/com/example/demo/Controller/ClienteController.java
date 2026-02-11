package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.RegistroClienteDTO;
import com.example.demo.Model.RespuestaLoginDTO;
import com.example.demo.services.ClienteServicio;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServicio clienteServicio;
    
    // REGISTRO 
    @PostMapping("/registro")
    public RespuestaLoginDTO  registro(@RequestBody RegistroClienteDTO  cliente) {
        return clienteServicio.registrarCliente(cliente);
        

    }
}