package com.example.demo.Model;

public class IngredienteDTO {

    private Long idIngrediente;
    private String nombre;
    private int stock; // Coincide con INT en tu SQL
    private String alergeno;

    // Constructor completo
    public IngredienteDTO(Long idIngrediente, String nombre, int stock, String alergeno) {
        super();
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.stock = stock;
        this.alergeno = alergeno;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getAlergeno() {
        return alergeno;
    }

    public void setAlergeno(String alergeno) {
        this.alergeno = alergeno;
    }

    @Override
    public String toString() {
        return "IngredienteDTO [idIngrediente=" + idIngrediente + ", nombre=" + nombre + ", stock=" + stock
                + ", alergenos=" + alergeno + "]";
    }
}