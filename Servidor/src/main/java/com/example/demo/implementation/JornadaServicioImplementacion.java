package com.example.demo.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.Entity.JornadaEntity;
import com.example.demo.Repository.EmpleadoRepository;
import com.example.demo.Repository.JornadaRepository;
import com.example.demo.services.JornadaServicio;

@Service
public class JornadaServicioImplementacion  implements JornadaServicio{

	@Autowired
	JornadaRepository repo;
	
	@Autowired
	EmpleadoRepository emplerepo;
	
	@Override
	public void anadirJornada(String empleadousu) {
		LocalDate fechaHoy = LocalDate.now();      
        LocalTime horaInicio = LocalTime.now();
        
        JornadaEntity jornada = new JornadaEntity();
        EmpleadoEntity empleado = emplerepo.findById(empleadousu).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        
        jornada.setFecha(fechaHoy);
        jornada.setHoraInicio(horaInicio);
        jornada.setEmpleado(empleado);
        jornada.setEmpleado(empleado);
        
        repo.save(jornada);
		
	}

	@Override
	public void finalizarJornada(long id) {
		JornadaEntity jornada = repo.findById(id).orElseThrow(() -> new RuntimeException("Jornada no encontrada"));
		LocalTime horaSalida = LocalTime.now();
		jornada.setHoraSalida(horaSalida);
		repo.save(jornada);
		
		
	}

	@Override
	public List<JornadaEntity> listJornadas() {
		List<JornadaEntity> jornadas;
		jornadas = repo.findAll();
		
		System.out.println(jornadas);
		return jornadas;
	}

}
