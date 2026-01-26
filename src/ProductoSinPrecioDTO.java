package com.example.demo.Model;

public class ProductoSinPrecioDTO {

    private int idProducto;
    private String nombre;
    private String descripcion;

    // Constructor sin precio
    public ProductoSinPrecioDTO(int idProducto, String nombre, String descripcion) {
        super();
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // GETTERS & SETTERS
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}