package com.example.demo.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PEDIDO")
public class PedidoEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PEDIDO")
	private long idPedido;
	
	@ManyToOne //Muchos pedidos para un cliente
	@JoinColumn(name="CLIENTE_ID", nullable = false)
	private ClienteEntity cliente;

	@Column(name = "FECHA_PEDIDO", nullable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "FECHA_ENTREGA", nullable = false)
    private LocalDateTime fechaEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false)
    private Estado estado;

    @Column(name = "TOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoProductoEntity> pedidoProductos = new HashSet<>();

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

	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Set<PedidoProductoEntity> getPedidoProductos() {
		return pedidoProductos;
	}

	public void setPedidoProductos(Set<PedidoProductoEntity> pedidoProductos) {
		this.pedidoProductos = pedidoProductos;
	}

	@Override
	public String toString() {
		return "PedidoEntity [idPedido=" + idPedido + ", cliente=" + cliente + ", fechaPedido=" + fechaPedido
				+ ", fechaEntrega=" + fechaEntrega + ", estado=" + estado + ", total=" + total + ", pedidoProductos="
				+ pedidoProductos + "]";
	}
	
}
