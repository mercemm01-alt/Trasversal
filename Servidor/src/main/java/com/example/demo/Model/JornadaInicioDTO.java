package com.example.demo.Model;

public class JornadaInicioDTO {
	
	private String usuario;
    private String horaInicio;
    
	public JornadaInicioDTO() {
		super();
	}

	public JornadaInicioDTO(String usuario, String horaInicio) {
		super();
		this.usuario = usuario;
		this.horaInicio = horaInicio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Override
	public String toString() {
		return "JornadaInicioDTO [usuario=" + usuario + ", horaInicio=" + horaInicio + "]";
	}
    
}
