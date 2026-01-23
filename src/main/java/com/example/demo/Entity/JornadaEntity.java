package com.example.demo.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
	private Date fecha;
	
	@Column(name = "HORA_INICIO")
	private Time horaInicio;
	
	@Column(name = "HORA_SALIDA")
	private Time horaSalida;
	
	@Column(name = "HORAS_DIA")
	private float horasDia;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_EMPLEADO", nullable = false)
	private EmpleadoEntity jornada;
	
	// GETTERS & SETTERS


	public Long getIdJornada() {
		return idJornada;
	}


	public void setIdJornada(Long idJornada) {
		this.idJornada = idJornada;
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


	public EmpleadoEntity getJornada() {
		return jornada;
	}


	public void setJornada(EmpleadoEntity jornada) {
		this.jornada = jornada;
	}
	
}
