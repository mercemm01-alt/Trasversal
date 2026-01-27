package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


@Entity
@Table(name="PRODUCTO")
public class ProductoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO")
	private long idProducto;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="PRECIO")
	private double precio;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@OneToMany(mappedBy="producto") //Un pedido, muchos productos
	private Set<PedidoEntity> producto = new HashSet<PedidoEntity>();
	
	// GETTERS & SETTERS

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<PedidoEntity> getProducto() {
		return producto;
	}

	public void setProducto(Set<PedidoEntity> producto) {
		this.producto = producto;
	}

}
