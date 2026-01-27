package com.example.demo.Model;

import java.math.BigDecimal;

public class ProductoDTO {

    private long idProducto;
    private String nombre;
    private BigDecimal precio; // Usamos BigDecimal para dinero (coincide con DECIMAL en SQL)
    private String descripcion;

    // Constructor completo
    public ProductoDTO(int idProducto, String nombre, BigDecimal precio, String descripcion) {
        super();
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Constructor vacío (a veces útil para Spring)
    public ProductoDTO() {
        super();
    }

    // GETTERS & SETTERS
    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ProductoDTO [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", descripcion="
                + descripcion + "]";
    }
}