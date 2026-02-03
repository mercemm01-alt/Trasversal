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
public class JornadaServicioImplementacion implements JornadaServicio {

    @Autowired
    private JornadaRepository repo;

    @Autowired
    private EmpleadoRepository emplerepo;

    @Override
    public JornadaEntity anadirJornada(String empleadousu) {

        LocalDate fechaHoy = LocalDate.now();
        LocalTime horaInicio = LocalTime.now();

        EmpleadoEntity empleado = emplerepo.findById(empleadousu)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        JornadaEntity jornada = new JornadaEntity();
        jornada.setFecha(fechaHoy);
        jornada.setHoraInicio(horaInicio);
        jornada.setEmpleado(empleado);

        return repo.save(jornada); 
    }

    @Override
    public JornadaEntity finalizarJornada(long id) {

        JornadaEntity jornada = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        LocalTime horaSalida = LocalTime.now();
        jornada.setHoraSalida(horaSalida);

        return repo.save(jornada);
    }

    @Override
    public List<JornadaEntity> listJornadas() {
        return repo.findAll();
    }
}
