package com.example.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="USUARIOS")
public class UsuarioEntity  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USUARIOS")
    private Long idUsuarios;
    
    @Column(name="USUARIO", nullable = false, unique = true)
    private String usuario;
    
    @Column(name="CONTRASENA", nullable = false)
    private String contrasena;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;
    
    // GETTERS & SETTERS

	public Long getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(Long idUsuarios) {
		this.idUsuarios = idUsuarios;
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "UsuarioEntity [idUsuarios=" + idUsuarios + ", usuario=" + usuario + ", contrasena=" + contrasena
				+ ", rol=" + rol + "]";
	}

}
