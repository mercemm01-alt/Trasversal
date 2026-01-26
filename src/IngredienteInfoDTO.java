package com.example.demo.Model;

public class IngredienteInfoDTO {

    private int idIngrediente;
    private String nombre;
    private String alergenos;

    // Constructor sin cantidad
    public IngredienteInfoDTO(int idIngrediente, String nombre, String alergenos) {
        super();
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
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

    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }
}