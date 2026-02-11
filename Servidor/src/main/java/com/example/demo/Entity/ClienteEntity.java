package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name="CLIENTE")
public class ClienteEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CLIENTE")
	private long idCliente;
	
	@ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private UsuarioEntity usuario;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name="APELLIDOS", nullable = false)
	private String apellidos;
	
	@Column(name="CORREO", nullable = false)
	private String correo;
	
	@Column(name="NUM_TLF", nullable = false)
	private int numTlf;
	
	@OneToMany(mappedBy = "cliente")
    private Set<PedidoEntity> pedidos = new HashSet<>();
	
	// GETTERS & SETTERS

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
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

	public Set<PedidoEntity> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "ClienteEntity [idCliente=" + idCliente + ", usuario=" + usuario + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", correo=" + correo + ", numTlf=" + numTlf + ", pedidos=" + pedidos + "]";
	}
	
	

}
