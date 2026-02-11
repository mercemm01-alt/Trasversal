package com.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "INGREDIENTE_PRODUCTO")
public class IngredienteProductoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_IP")
	private long idIp;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCTO_ID", nullable = false)
	private ProductoEntity producto;
	
	@ManyToOne
	@JoinColumn(name = "INGREDIENTE_ID", nullable = false)
	private IngredienteEntity ingrediente;
	
	// GETTERS & SETTERS

	public long getIdIp() {
		return idIp;
	}

	public void setIdIp(long idIp) {
		this.idIp = idIp;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public IngredienteEntity getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(IngredienteEntity ingrediente) {
		this.ingrediente = ingrediente;
	}

	@Override
	public String toString() {
		return "IngredienteProductoEntity [idIp=" + idIp + ", producto=" + producto + ", ingrediente=" + ingrediente
				+ "]";
	}

}
