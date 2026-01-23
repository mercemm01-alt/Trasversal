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
	private ProductoEntity productoIngrdiente;
	
	@ManyToOne
	@JoinColumn(name = "INGREDIENTES_ID", nullable = false)
	private IngredientesEntity ingredientesProducto;
	
	// GETTERS & SETTERS

	public long getIdIp() {
		return idIp;
	}

	public void setIdIp(long idIp) {
		this.idIp = idIp;
	}

	public ProductoEntity getProductoIngrdiente() {
		return productoIngrdiente;
	}

	public void setProductoIngrdiente(ProductoEntity productoIngrdiente) {
		this.productoIngrdiente = productoIngrdiente;
	}

	public IngredientesEntity getIngredientesProducto() {
		return ingredientesProducto;
	}

	public void setIngredientesProducto(IngredientesEntity ingredientesProducto) {
		this.ingredientesProducto = ingredientesProducto;
	}

	
}
