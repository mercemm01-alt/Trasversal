package com.example.demo.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "INGREDIENTES")
public class IngredientesEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INGREDIENTES")
	private Long idIngredientes;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CANTIDAD")
	private int cantidad;
	
	@Column(name = "ALERGENOS")
	private Alergenos alergenos;
	
	@OneToMany(mappedBy = "ingredientesProducto")
	private Set<IngredienteProductoEntity> ingredienteProducto = new HashSet<IngredienteProductoEntity>();
	
	// GETTERS & SETTERS

	public Long getIdIngredientes() {
		return idIngredientes;
	}

	public void setIdIngredientes(Long idIngredientes) {
		this.idIngredientes = idIngredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Alergenos getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(Alergenos alergenos) {
		this.alergenos = alergenos;
	}

	public Set<IngredienteProductoEntity> getIngredienteProducto() {
		return ingredienteProducto;
	}

	public void setIngredienteProducto(Set<IngredienteProductoEntity> ingredienteProducto) {
		this.ingredienteProducto = ingredienteProducto;
	}

}
