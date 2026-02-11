package com.example.demo.Model;

import java.math.BigDecimal;

public class JornadaAdminDTO {
	private String nombreEmpleado;
    private String fecha;
    private String horaInicio;
    private String horaSalida;
    private BigDecimal horasDia;
    
	public JornadaAdminDTO() {
		super();
	}

	public JornadaAdminDTO(String nombreEmpleado, String fecha, String horaInicio, String horaSalida,
			BigDecimal horasDia) {
		super();
		this.nombreEmpleado = nombreEmpleado;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaSalida = horaSalida;
		this.horasDia = horasDia;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public BigDecimal getHorasDia() {
		return horasDia;
	}

	public void setHorasDia(BigDecimal horasDia) {
		this.horasDia = horasDia;
	}

	@Override
	public String toString() {
		return "JornadaAdminDTO [nombreEmpleado=" + nombreEmpleado + ", fecha=" + fecha + ", horaInicio=" + horaInicio
				+ ", horaSalida=" + horaSalida + ", horasDia=" + horasDia + "]";
	}
    
}
