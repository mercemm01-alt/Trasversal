package com.example.demo.services;

import java.util.List;

import com.example.demo.Entity.EmpleadoEntity;

public interface EmpleadoServicio {

	EmpleadoEntity loginEmpleado(String usuario, String contrasena);
	List<EmpleadoEntity> listEmpleados();
	void anadirEmpleado(String usuario, String contrasena, String nombre, String apellidos, String admin);
	void eliminarEmpleado(String usuario);
	
}
