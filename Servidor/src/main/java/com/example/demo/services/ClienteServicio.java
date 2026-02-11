package com.example.demo.services;

import com.example.demo.Model.RegistroClienteDTO;
import com.example.demo.Model.RespuestaLoginDTO;

public interface ClienteServicio {
	
    RespuestaLoginDTO registrarCliente(RegistroClienteDTO cliente);
}