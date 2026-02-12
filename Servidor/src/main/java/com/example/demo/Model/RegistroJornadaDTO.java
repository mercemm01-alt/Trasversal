package com.example.demo.Model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroJornadaDTO {

    private String nombreEmpleado;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaSalida;
    private String horasTrabajadas;

    public RegistroJornadaDTO(String nombreEmpleado, LocalDate fecha, LocalTime horaInicio, LocalTime horaSalida) {

        this.nombreEmpleado = nombreEmpleado;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaSalida = horaSalida;

        if (horaInicio != null && horaSalida != null) {
            Duration d = Duration.between(horaInicio, horaSalida);
            long horas = d.toHours();
            long minutos = d.toMinutes() % 60;
            this.horasTrabajadas = horas + "h " + minutos + "m";
            System.out.println("HORAS: " + horas + " | MINUTOS: " + minutos);
        } else {
            this.horasTrabajadas = "En curso";
        }
    }

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(String horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

    
    
}
