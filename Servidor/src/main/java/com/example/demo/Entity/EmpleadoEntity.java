package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLEADO")
public class EmpleadoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDOS")
	private String apellido;
	
	@Column(name = "CONTRASENA")
	private String contrasena;
	
	@Column(name = "ADMINISTRADOR")
	private String admin;
	
	@OneToMany(mappedBy = "empleado")
	private Set<JornadaEntity> jornada = new HashSet<JornadaEntity>();
	
	// GETTERS & SETTERS

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Set<JornadaEntity> getJornada() {
		return jornada;
	}

	public void setJornada(Set<JornadaEntity> jornada) {
		this.jornada = jornada;
	}

	@Override
	public String toString() {
		return "EmpleadoEntity [usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", contrasena="
				+ contrasena + ", admin=" + admin + ", jornada=" + jornada + "]";
	}

	
	
}
