package com.example.demo.services;

import java.util.List;

import com.example.demo.Entity.JornadaEntity;

public interface JornadaServicio {
	
	void anadirJornada(String empleado);
	void finalizarJornada(long id);
	List<JornadaEntity> listJornadas();

}
