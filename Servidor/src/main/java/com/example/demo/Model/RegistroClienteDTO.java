package com.example.demo.Model;

public class RegistroClienteDTO {
	
	private String usuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String correo;
    private int numTlf;
    
	public RegistroClienteDTO() {
		super();
	}

	public RegistroClienteDTO(String usuario, String contrasena, String nombre, String apellidos, String correo,
			int numTlf) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.numTlf = numTlf;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getNumTlf() {
		return numTlf;
	}

	public void setNumTlf(int numTlf) {
		this.numTlf = numTlf;
	}

	@Override
	public String toString() {
		return "RegistroClienteDTO [usuario=" + usuario + ", contrasena=" + contrasena + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", correo=" + correo + ", numTlf=" + numTlf + "]";
	}
    
}
