package com.example.demo.Model;

public class EmpleadoDTO {
	
	private String usuario;
    private String nombre;
    private String apellidos;
    private String administrador; // "S" | "N"

	public EmpleadoDTO() {
		super();
	}
	
	public EmpleadoDTO(String usuario, String nombre, String apellidos, String administrador) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.administrador = administrador;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "EmpleadoDTO [usuario=" + usuario + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", administrador=" + administrador + "]";
	}

}
