package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.JornadaServicio;

@RestController
public class JornadaController {

	@Autowired
	JornadaServicio jornadaServicio;
	
	//REGISTRA CON SAVE LA JORNADA (SIN HORA DE SALIDA) EN BASE AL EMPLEADO ELEGIDO POR SU USUARIO
	@GetMapping("/FichaEntrada")
	public void fichaEntrada(Model model) {
		jornadaServicio.anadirJornada("eclipse");
		
		
	}
	
	//COGE LA JORNADA EXISTENTE Y LE AÑADE LA HORA DE SALIDA
	@GetMapping("/FichaSalida")
	public void fichaSalida(Model model) {
		jornadaServicio.finalizarJornada(1);
		
		
	}
	
	//LISTA DE JORNADAS
		@GetMapping("/Jornadas")
		public void listaJornadas(Model model) {
			jornadaServicio.listJornadas();
			
			
		}
	
}
