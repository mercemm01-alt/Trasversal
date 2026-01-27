package com.example.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name="PEDIDO")
public class PedidoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO")
	private long idPedido;
	
	@ManyToOne //Muchos pedidos para un cliente
	@JoinColumn(name="CLIENTE", nullable = false)
	private ClienteEntity cliente;

	@ManyToOne 
	@JoinColumn(name="PRODUCTO", nullable = false)
	private ProductoEntity producto;

	@Column(name="CANTIDAD")
	private int cantidad;
	
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name="FECHA_ENTREGA")
	private Date fechaEntrega;
	
	@Column(name="ESTADO")
	private Estado estado;
	
	@Column(name="PRECIO_FINAL")
	private double precioFinal;

	// GETTERS & SETTERS

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	
}
