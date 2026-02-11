package com.example.demo.Model;

public class LoginDTO {
	
	private String usuario;
    private String contrasena;

	public LoginDTO() {
		super();
	}
	
	
	public LoginDTO(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

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
		return "LoginDTO [usuario=" + usuario + ", contrasena=" + contrasena + "]";
	}
  
}
