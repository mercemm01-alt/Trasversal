package com.example.demo.Model;

import com.example.demo.Entity.Alergenos;

public class IngredienteDTO {

    private long idIngrediente;
    private String nombre;
    private int cantidad; // Coincide con INT en tu SQL
    private Alergenos alergenos;

    // Constructor completo
    public IngredienteDTO(int idIngrediente, String nombre, int cantidad, Alergenos alergenos) {
        super();
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.alergenos = alergenos;
    }
    
    public IngredienteDTO() {
    	
    }

    // GETTERS & SETTERS
    public long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
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

    @Override
    public String toString() {
        return "IngredienteDTO [idIngrediente=" + idIngrediente + ", nombre=" + nombre + ", cantidad=" + cantidad
                + ", alergenos=" + alergenos + "]";
    }
}