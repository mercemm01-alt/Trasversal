package com.example.demo.Model;

public class JornadaFinDTO {
	
	private String usuario;
    private String horaSalida;
    
	public JornadaFinDTO() {
		super();
	}

	public JornadaFinDTO(String usuario, String horaSalida) {
		super();
		this.usuario = usuario;
		this.horaSalida = horaSalida;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Override
	public String toString() {
		return "JornadaFinDTO [usuario=" + usuario + ", horaSalida=" + horaSalida + "]";
	}
   
}
