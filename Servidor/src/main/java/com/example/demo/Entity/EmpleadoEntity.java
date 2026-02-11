package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLEADO")
public class EmpleadoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPLEADO")
	private Long idEmpleado;
	
	@ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioEntity usuario;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", nullable = false)
	private String apellido;
	
	@Column(name = "ES_ADMIN", nullable = false)
	private Boolean admin;
	
	//Mapeado por empleado
	@OneToMany(mappedBy = "empleado")
	private Set<JornadaEntity> jornada = new HashSet<JornadaEntity>();

	// GETTERS & SETTERS
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
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
		return "EmpleadoEntity [idEmpleado=" + idEmpleado + ", usuario=" + usuario + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", admin=" + admin + ", jornada=" + jornada
				+ "]";
	}
	
	
}
