package com.example.demo.Model;

public class CrearEmpleadoDTO {

    private String nombre;
	private String apellido;
	private String admin;
    private String usuario;
	private String contrasena;

    public CrearEmpleadoDTO(String nombre, String apellido, String admin, String usuario, String contrasena) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.admin = "N";
        this.usuario = usuario;
		this.contrasena = contrasena;
	}
    
}
