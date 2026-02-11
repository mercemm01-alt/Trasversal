package com.example.demo.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.Rol;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Model.RegistroClienteDTO;
import com.example.demo.Model.RespuestaLoginDTO;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.services.ClienteServicio;

@Service
public class ClienteServicioImplementacion implements ClienteServicio {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
    @Autowired
    private ClienteRepository clienteRepository;

	@Override
	public RespuestaLoginDTO registrarCliente(RegistroClienteDTO cliente) {
		
		// Comprueba que no exista ese usuario.
		if(usuarioRepository.existsByUsuario(cliente.getUsuario())){
			throw new RuntimeException("El usuario ya existe");
		}
		
		// Comprueba que los campos no esten en blanco
		if (cliente.getUsuario().isBlank() || cliente.getContrasena().isBlank()) {
		    throw new RuntimeException("Datos inválidos");
		}
		
		// Si no exisite crea el usuario
		UsuarioEntity nuevoUsuario = new UsuarioEntity();
		nuevoUsuario.setUsuario(cliente.getUsuario());
		nuevoUsuario.setContrasena(cliente.getContrasena());
		nuevoUsuario.setRol(Rol.CLIENTE);
        usuarioRepository.save(nuevoUsuario);
		
     // Y crearmos el cliente
        ClienteEntity nuevoCliente = new ClienteEntity();
        nuevoCliente.setUsuario(nuevoUsuario);
        nuevoCliente.setNombre(cliente.getNombre());
        nuevoCliente.setApellidos(cliente.getApellidos());
        nuevoCliente.setCorreo(cliente.getCorreo());
        nuevoCliente.setNumTlf(cliente.getNumTlf());

        clienteRepository.save(nuevoCliente);
		
		return new RespuestaLoginDTO(nuevoUsuario.getIdUsuarios(),
									nuevoUsuario.getUsuario(),
									nuevoUsuario.getRol());
		
	}
}