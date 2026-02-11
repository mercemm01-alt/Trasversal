package com.example.demo.Model;

import com.example.demo.Entity.Rol;

public class RespuestaLoginDTO {
	
	private Long idUsuarios;
    private String usuario;
    private Rol rol;
    
	public RespuestaLoginDTO() {
		super();
	}

	public RespuestaLoginDTO(Long idUsuarios, String usuario, Rol rol) {
		super();
		this.idUsuarios = idUsuarios;
		this.usuario = usuario;
		this.rol = rol;
	}


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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "RespuestaLoginDTO [idUsuarios=" + idUsuarios + ", usuario=" + usuario
				+ ", rol=" + rol + "]";
	}

}
