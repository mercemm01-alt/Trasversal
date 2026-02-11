package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class HacerPedidoDTO {

	private Long usuario;
	private LocalDate  fechaEntrega;
	private BigDecimal  total;
	public List<PedidoProductoDTO> productos;
	
	public HacerPedidoDTO() {
		
	}

	public HacerPedidoDTO(Long usuario, LocalDate fechaEntrega, BigDecimal total, List<PedidoProductoDTO> productos) {
		super();
		this.usuario = usuario;
		this.fechaEntrega = fechaEntrega;
		this.total = total;
		this.productos = productos;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<PedidoProductoDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<PedidoProductoDTO> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "PedidoDTO [clienteId=" + usuario + ", fechaEntrega=" + fechaEntrega + ", total=" + total
				+ ", productos=" + productos + "]";
	}

	
	
}
