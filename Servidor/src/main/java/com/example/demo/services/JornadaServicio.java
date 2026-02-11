package com.example.demo.services;

import java.util.List;

import com.example.demo.Entity.JornadaEntity;
import com.example.demo.Model.JornadaAdminDTO;
import com.example.demo.Model.JornadaEmpleadoDTO;
import com.example.demo.Model.JornadaFinDTO;
import com.example.demo.Model.JornadaInicioDTO;

public interface JornadaServicio {
	
	List<JornadaEmpleadoDTO> obtenerJornadasHoyUsuario(String usuario);

    void iniciarJornada(JornadaInicioDTO dto);

    void finalizarJornada(JornadaFinDTO dto);

    List<JornadaAdminDTO> obtenerTodasJornadasAdmin();

	JornadaEntity obtenerJornadaAbierta(String usuario);
}
