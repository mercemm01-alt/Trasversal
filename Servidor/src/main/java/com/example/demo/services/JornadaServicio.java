package com.example.demo.services;

import java.util.List;

import com.example.demo.Entity.JornadaEntity;

public interface JornadaServicio {
	
	JornadaEntity anadirJornada(String empleado);
	JornadaEntity finalizarJornada(long id);
	List<JornadaEntity> listJornadas();

}
