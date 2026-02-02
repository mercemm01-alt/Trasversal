package com.example.demo.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "JORNADA")
public class JornadaEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_JORNADA")
	private Long idJornada;
	
	@Column(name = "FECHA")
	private LocalDate fecha;
	
	@Column(name = "HORA_INICIO")
	private LocalTime horaInicio;
	
	@Column(name = "HORA_SALIDA")
	private LocalTime horaSalida;
	
	@Column(name = "HORAS_DIA")
	private float horasDia;
	
	//Empleado que ficha
	@ManyToOne
	@JoinColumn(name = "EMPLEADO", nullable = false)
	private EmpleadoEntity empleado;
	
	// GETTERS & SETTERS


	public Long getIdJornada() {
		return idJornada;
	}


	public void setIdJornada(Long idJornada) {
		this.idJornada = idJornada;
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


	public float getHorasDia() {
		return horasDia;
	}


	public void setHorasDia(float horasDia) {
		this.horasDia = horasDia;
	}


	public EmpleadoEntity getEmpleado() {
		return empleado;
	}


	public void setEmpleado(EmpleadoEntity jornada) {
		this.empleado = jornada;
	}


	@Override
	public String toString() {
	    return "JornadaEntity [idJornada=" + idJornada + ", fecha=" + fecha 
	        + ", horaInicio=" + horaInicio + ", horaSalida=" + horaSalida 
	        + ", horasDia=" + horasDia 
	        + ", empleado=" + (empleado != null ? empleado.getUsuario() : null) + "]";
	}

	
	
	
}
