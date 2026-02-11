package com.example.demo.services.implementation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.Entity.JornadaEntity;
import com.example.demo.Model.JornadaAdminDTO;
import com.example.demo.Model.JornadaEmpleadoDTO;
import com.example.demo.Model.JornadaFinDTO;
import com.example.demo.Model.JornadaInicioDTO;
import com.example.demo.Repository.EmpleadoRepository;
import com.example.demo.Repository.JornadaRepository;
import com.example.demo.services.JornadaServicio;

@Service
public class JornadaServicioImplementacion implements JornadaServicio {
	
	@Autowired
    private JornadaRepository jornadaRepo;

    @Autowired
    private EmpleadoRepository empleadoRepo;
    
	@Override
	public List<JornadaEmpleadoDTO> obtenerJornadasHoyUsuario(String usuario) {
		LocalDate hoy = LocalDate.now();

		// Obtener el empleado a partir del usuario
	    EmpleadoEntity empleado = empleadoRepo
	        .findByUsuarioUsuario(usuario)
	        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

	    // Obtener las jornadas de hoy de ese empleado
	    return jornadaRepo
	        .findByEmpleadoAndFecha(empleado, hoy)
	        .stream()
	        .map(j -> {
	            JornadaEmpleadoDTO dto = new JornadaEmpleadoDTO();
	            dto.setFecha(j.getFecha().toString());
	            dto.setInicio(j.getHoraInicio().toString());
	            dto.setFin(
	                j.getHoraSalida() != null ? j.getHoraSalida().toString() : ""
	            );
	            dto.setHoras(j.getHorasDia());
	            return dto;
	        })
	        .toList();
	}

	@Override
	public void iniciarJornada(JornadaInicioDTO dto) {
		EmpleadoEntity empleado = empleadoRepo
				.findByUsuarioUsuario(dto.getUsuario())
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        JornadaEntity jornada = new JornadaEntity();
        jornada.setEmpleado(empleado);
        jornada.setFecha(LocalDate.now());
        jornada.setHoraInicio(LocalTime.parse(dto.getHoraInicio()));

        jornadaRepo.save(jornada);
		
	}
	
	@Override
	public JornadaEntity obtenerJornadaAbierta(String usuario) {

	    EmpleadoEntity empleado = empleadoRepo
	        .findByUsuarioUsuario(usuario)
	        .orElseThrow();

	    return jornadaRepo
	        .findByEmpleadoAndFechaAndHoraSalidaIsNull(
	            empleado,
	            LocalDate.now()
	        )
	        .orElse(null);
	}

	@Override
	public void finalizarJornada(JornadaFinDTO dto) {
		EmpleadoEntity empleado = empleadoRepo
		        .findByUsuarioUsuario(dto.getUsuario())
		        .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
		
		 JornadaEntity jornada = jornadaRepo
	                .findByEmpleadoAndFechaAndHoraSalidaIsNull(
	                        empleado,
	                        LocalDate.now()
	                )
	                .orElseThrow(() -> new RuntimeException("No hay jornada abierta"));

	        LocalTime salida = LocalTime.parse(dto.getHoraSalida());

	        jornada.setHoraSalida(salida);

	        BigDecimal horas = BigDecimal.valueOf(
	                Duration.between(jornada.getHoraInicio(), salida).toMinutes() / 60.0
	        );

	        jornada.setHorasDia(horas);

	        jornadaRepo.save(jornada);
		
	}

	@Override
	public List<JornadaAdminDTO> obtenerTodasJornadasAdmin() {
		return jornadaRepo.findAllWithEmpleado()
                .stream()
                .map(j -> {
                    JornadaAdminDTO dto = new JornadaAdminDTO();
                    dto.setNombreEmpleado(
                        j.getEmpleado().getNombre() + " " + j.getEmpleado().getApellido()
                    );
                    dto.setFecha(j.getFecha().toString());
                    dto.setHoraInicio(j.getHoraInicio().toString());
                    dto.setHoraSalida(
                        j.getHoraSalida() != null ? j.getHoraSalida().toString() : ""
                    );
                    dto.setHorasDia(j.getHorasDia());
                    return dto;
                })
                .toList();
	}

    
}
