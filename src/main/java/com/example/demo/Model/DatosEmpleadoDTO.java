package com.example.demo.Model;

public class DatosEmpleadoDTO {
	
	private String nombre;
	private String apellido;
	private String admin;
	
	public DatosEmpleadoDTO(String nombre, String apellido, String admin) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.admin = "N";
	}
	
	// GETTERS & SETTERS
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
}
