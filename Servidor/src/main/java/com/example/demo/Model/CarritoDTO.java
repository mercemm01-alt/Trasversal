package com.example.demo.Model;

import java.util.List;

public class CarritoDTO {
	
	private Long clienteId;
    private List<ProductoCarritoDTO> items;
    
	public CarritoDTO() {
		super();
	}

	public CarritoDTO(Long clienteId, List<ProductoCarritoDTO> items) {
		super();
		this.clienteId = clienteId;
		this.items = items;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<ProductoCarritoDTO> getItems() {
		return items;
	}

	public void setItems(List<ProductoCarritoDTO> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CarritoDTO [clienteId=" + clienteId + ", items=" + items + "]";
	}
    
}
