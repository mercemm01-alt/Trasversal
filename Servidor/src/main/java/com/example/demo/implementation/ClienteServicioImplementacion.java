package com.example.demo.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.services.ClienteServicio;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteEntity registrarCliente(ClienteEntity cliente) {
        // 1. VALIDACIÓN: Comprobar si el correo ya existe
        if (clienteRepository.existsByCorreo(cliente.getCorreo())) {
            // Si existe se devuelve null
            return null; 
        }

        // 2. Si no existe, guardamos el cliente nuevo
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteEntity loginCliente(String correo, String contrasena) {
        return clienteRepository.findByCorreoAndContraseña(correo, contrasena);
    }
}