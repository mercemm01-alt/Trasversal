package com.example.demo.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Model.LoginDTO;
import com.example.demo.Model.RespuestaLoginDTO;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.services.LoginService;

@Service
public class LoginImplement implements LoginService {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Override
	public RespuestaLoginDTO login(LoginDTO loginDTO) {
		
		UsuarioEntity usuario = usuarioRepository
				
		.findByUsuarioAndContrasena(
			loginDTO.getUsuario(),
			loginDTO.getContrasena()
						)
		.orElse(null);
		
		if(usuario == null) {
			return null;
		}
		
		return new RespuestaLoginDTO(
				usuario.getIdUsuarios(),
				usuario.getUsuario(),
				usuario.getRol()
		);
	}

}
