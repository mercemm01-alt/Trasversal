package com.example.demo.Model;

import java.sql.Date;
import java.sql.Time;

public class JornadaDTO {
	
	private Long idJornada;
	private Long idEmpleado;
	private Date fecha;
	private Time horaInicio;
	private Time horaSalida;
	private float horasDia;
	
	public JornadaDTO(Long idJornada, Long idEmpleado, Date fecha, Time horaInicio, Time horaSalida) {
		super();
		this.idJornada = idJornada;
		this.idEmpleado = idEmpleado;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaSalida = horaSalida;
	}
	// GETTERS & SETTERS
	public Long getIdJornada() {
		return idJornada;
	}
	public void setIdJornada(Long idJornada) {
		this.idJornada = idJornada;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}
	public float getHorasDia() {
		return horasDia;
	}
	public void setHorasDia(float horasDia) {
		this.horasDia = horasDia;
	}
	@Override
	public String toString() {
		return "JornadaDTO [idJornada=" + idJornada + ", idEmpleado=" + idEmpleado + ", fecha=" + fecha
				+ ", horaInicio=" + horaInicio + ", horaSalida=" + horaSalida + ", horasDia=" + horasDia + "]";
	}
	
}
