package com.example.demo.services;

import java.util.List;

import com.example.demo.Model.CrearEmpleadoDTO;
import com.example.demo.Model.EmpleadoDTO;

public interface EmpleadoServicio {

	List<EmpleadoDTO> listarEmpleados();
	void crearEmpleado(CrearEmpleadoDTO dto);

    void eliminarEmpleado(String usuario);
	
}
