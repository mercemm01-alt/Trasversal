package com.example.demo.Model;

public class ClientePedidosDTO {
	
	private String nombre;
	private String apellido;
	private int telefono;
	private long idPedido;
	
	public ClientePedidosDTO() {
		
	}

	public ClientePedidosDTO(String nombre, String apellido, int telefono, long idPedido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.idPedido = idPedido;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	@Override
	public String toString() {
		return "ClientePedidosDTO [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", idPedido=" + idPedido + "]";
	}

}
