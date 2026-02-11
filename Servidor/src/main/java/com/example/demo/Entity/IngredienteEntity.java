package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "INGREDIENTE")
public class IngredienteEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INGREDIENTE")
	private Long idIngrediente;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "STOCK", nullable = false)
	private int stock;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ALERGENOS", nullable = false)
	private Alergenos alergeno;
	
	@OneToMany(mappedBy = "ingrediente")
	private Set<IngredienteProductoEntity> ingredienteProductos = new HashSet<IngredienteProductoEntity>();

	// GETTERS & SETTERS
	
	public Long getIdIngredientes() {
		return idIngrediente;
	}

	public void setIdIngredientes(Long idIngredientes) {
		this.idIngrediente = idIngredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Alergenos getAlergeno() {
		return alergeno;
	}

	public void setAlergeno(Alergenos alergeno) {
		this.alergeno = alergeno;
	}

	public Set<IngredienteProductoEntity> getIngredienteProductos() {
		return ingredienteProductos;
	}

	public void setIngredienteProductos(Set<IngredienteProductoEntity> ingredienteProductos) {
		this.ingredienteProductos = ingredienteProductos;
	}

	@Override
	public String toString() {
		return "IngredientesEntity [idIngredientes=" + idIngrediente + ", nombre=" + nombre + ", stock=" + stock
				+ ", alergeno=" + alergeno + ", ingredienteProductos=" + ingredienteProductos + "]";
	}
	
}
