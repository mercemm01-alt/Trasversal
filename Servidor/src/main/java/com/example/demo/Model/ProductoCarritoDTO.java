package com.example.demo.Model;

public class ProductoCarritoDTO {
	
	private Long productoId;
    private int cantidad;
    
	public ProductoCarritoDTO() {
		super();
	}

	public ProductoCarritoDTO(Long productoId, int cantidad) {
		super();
		this.productoId = productoId;
		this.cantidad = cantidad;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ProductoCarritoDTO [productoId=" + productoId + ", cantidad=" + cantidad + "]";
	}
    
}
