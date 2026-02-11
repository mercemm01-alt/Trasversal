package com.example.demo.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDO_PRODUCTO")
public class PedidoProductoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PP")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PEDIDO_ID", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "PRODUCTO_ID", nullable = false)
    private ProductoEntity producto;

    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

    // GETTERS & SETTERS
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
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

	@Override
	public String toString() {
		return "PedidoProductoEntity [id=" + id + ", pedido=" + pedido + ", producto=" + producto + ", cantidad="
				+ cantidad + "]";
	}

}
