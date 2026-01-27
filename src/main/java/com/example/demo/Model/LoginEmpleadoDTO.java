package com.example.demo.Model;

public class LoginEmpleadoDTO {
	
	private String usuario;
	private String contrasena;
	
	public LoginEmpleadoDTO(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	// GETTERS & SETTERS
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "LoginEmpleadoDTO [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}
}
