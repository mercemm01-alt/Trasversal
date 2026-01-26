package com.example.demo.Model;

public class IngredienteDTO {

    private int idIngrediente;
    private String nombre;
    private int cantidad; // Coincide con INT en tu SQL
    private String alergenos;

    // Constructor completo
    public IngredienteDTO(int idIngrediente, String nombre, int cantidad, String alergenos) {
        super();
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.alergenos = alergenos;
    }

    // GETTERS & SETTERS
    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
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

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }
}