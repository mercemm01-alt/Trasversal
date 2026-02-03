package com.example.demo.services;

import com.example.demo.Entity.ClienteEntity;

public interface ClienteServicio {
    ClienteEntity registrarCliente(ClienteEntity cliente);
    ClienteEntity loginCliente(String correo, String contrasena);
}