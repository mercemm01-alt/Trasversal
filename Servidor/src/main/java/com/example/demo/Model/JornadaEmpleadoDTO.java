package com.example.demo.Model;

import java.math.BigDecimal;

public class JornadaEmpleadoDTO {
	
	private String fecha;
    private String inicio;
    private String fin;
    private BigDecimal horas;

	public JornadaEmpleadoDTO() {
		super();
	}

	public JornadaEmpleadoDTO(String fecha, String inicio, String fin, BigDecimal horas) {
		super();
		this.fecha = fecha;
		this.inicio = inicio;
		this.fin = fin;
		this.horas = horas;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	@Override
	public String toString() {
		return "JornadaEmpleadoDTO [fecha=" + fecha + ", inicio=" + inicio + ", fin=" + fin + ", horas=" + horas + "]";
	}
    
}
