package com.example.demo.Model;

public class CrearEmpleadoDTO {

    private String nombre;
	private String apellido;
	private String admin;
    private String usuario;
	private String contrasena;

    public CrearEmpleadoDTO() {
        //TODO Auto-generated constructor stub
    }

    public CrearEmpleadoDTO(String nombre, String apellido, String admin, String usuario, String contrasena) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.admin = "N";
        this.usuario = usuario;
		this.contrasena = contrasena;
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
    
}
