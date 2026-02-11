package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.LoginDTO;
import com.example.demo.Model.RespuestaLoginDTO;
import com.example.demo.services.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public RespuestaLoginDTO login (@RequestBody LoginDTO loginDTO) {
		
		RespuestaLoginDTO respuesta = loginService.login(loginDTO);
		
		if(respuesta == null)  {
			throw new RuntimeException("Usuario o contraseña incorrectos");
		}
		
		return respuesta;
	}
      
}
