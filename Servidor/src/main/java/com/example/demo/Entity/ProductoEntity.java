package com.example.demo.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private Long idProducto;
	
	@Column(name="NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name="PRECIO", nullable = false, precision = 10, scale = 2)
	private BigDecimal  precio;
	
	@Column(name="DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name="IMAGEN")
	private String imagen;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "TIPO", nullable = false)
    private Tipo tipo;
	
	// Producto - Pedido
	@OneToMany(mappedBy = "producto")
    private Set<PedidoProductoEntity> pedidoProductos = new HashSet<>();
	
	//Producto - Ingrediente
	@OneToMany(mappedBy = "producto")
    private Set<IngredienteProductoEntity> ingredienteProductos = new HashSet<>();
	
	// GETTERS & SETTERS
	
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Set<PedidoProductoEntity> getPedidoProductos() {
		return pedidoProductos;
	}

	public void setPedidoProductos(Set<PedidoProductoEntity> pedidoProductos) {
		this.pedidoProductos = pedidoProductos;
	}

	public Set<IngredienteProductoEntity> getIngredienteProductos() {
		return ingredienteProductos;
	}

	public void setIngredienteProductos(Set<IngredienteProductoEntity> ingredienteProductos) {
		this.ingredienteProductos = ingredienteProductos;
	}

	@Override
	public String toString() {
		return "ProductoEntity [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + ", tipo=" + tipo + ", pedidoProductos="
				+ pedidoProductos + ", ingredienteProductos=" + ingredienteProductos + "]";
	}
	
}
