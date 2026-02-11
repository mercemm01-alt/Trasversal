package com.example.demo.Model;

public class CrearEmpleadoDTO {

	private String usuario;
    private String contrasena;
    private String nombre;
    private String apellidos;
    private String administrador; // "S" | "N"

    public CrearEmpleadoDTO() {
        //TODO Auto-generated constructor stub
    }

    public CrearEmpleadoDTO(String usuario, String contrasena, String nombre, String apellidos, String administrador) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
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

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	@Override
	public String toString() {
		return "CrearEmpleadoDTO [usuario=" + usuario + ", contrasena=" + contrasena + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", administrador=" + administrador + "]";
	}
    
}
