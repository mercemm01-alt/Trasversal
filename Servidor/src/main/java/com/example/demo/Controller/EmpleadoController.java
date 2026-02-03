package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.services.EmpleadoServicio;

@RestController
public class EmpleadoController {
	
	@Autowired
	private EmpleadoServicio empleadoServicio;
	
	//EMPLEADO EN EL QUE SE VA A INICIAR SESIÓN (GUARDA LOS DATOS DEL USUARIO QUE SE BUSQUE EN BASE AL USUARIO Y CONTRASEÑA)
	EmpleadoEntity empLogIn;
	
	//PRUEBA DE CONSULTA EN / PARA QUE SE EJECUTE SI O SI (SIN WEB, EN CONSOLA).
	@GetMapping("/")
	public void getLoginPage(Model model) {
		empLogIn = empleadoServicio.loginEmpleado("spring", "tool");
		
	}
	
	//PRUEBA DE CONSULTA MASIVA EN /Empleados PARA QUE SE EJECUTE EN CONSOLA
	@GetMapping("/Empleados")
	public void listEmpleados(Model model) {
	    empleadoServicio.listEmpleados();
	}
	
	//PRUEBA DE INSERT CON SAVE(JPA) PARA AÑADIR EMPLEADO (VERIFICABLE EN MYSQL)
	@GetMapping("/AnadirEmpleado")
	public void anadirEmpleado(Model model) {
		empleadoServicio.anadirEmpleado("eclipse", "java", "ECLIPSE", "JAVA", "N");
	}
	
	//PRUEBA DE DELETE POR ID(USUARIO) (VERIFICABLE EN MYSQL)
		@GetMapping("/EliminarEmpleado")
		public void eliminarEmpleado(Model model) {
			empleadoServicio.eliminarEmpleado("eclipse");
		}

	
	

}
