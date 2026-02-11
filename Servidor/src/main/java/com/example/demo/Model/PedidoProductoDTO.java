package com.example.demo.Model;

public class PedidoProductoDTO {
	private Long productoId;
    private int cantidad;
    
	public PedidoProductoDTO() {
		super();
	}

	public PedidoProductoDTO(Long idProducto, int cantidad) {
		super();
		this.productoId = idProducto;
		this.cantidad = cantidad;
	}
	
	public Long getIdProducto() {
		return productoId;
	}
	public void setIdProducto(Long idProducto) {
		this.productoId = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "PedidoProductoDTO [idProducto=" + productoId + ", cantidad=" + cantidad + "]";
	}
    
}
