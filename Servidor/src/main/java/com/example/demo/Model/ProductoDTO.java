package com.example.demo.Model;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Entity.Tipo;

public class ProductoDTO {

    private Long idProducto;
    private String nombre;
    private BigDecimal precio; // Usamos BigDecimal para dinero (coincide con DECIMAL en SQL)
    private String descripcion;
    private String imagen;
    private List<Long> ingredientes;
    private List<String> alergenos;
    private Tipo tipo;

    // Constructor completo
    public ProductoDTO(Long idProducto, String nombre, BigDecimal precio, String descripcion, String imagen,
			List<Long> ingredientes, List<String> alergenos, Tipo tipo) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.ingredientes = ingredientes;
		this.alergenos = alergenos;
		this.tipo = tipo;
	}
    
    public ProductoDTO(ProductoEntity producto) {
        this.idProducto = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.precio = producto.getPrecio();
        this.descripcion = producto.getDescripcion();
        this.imagen = producto.getImagen();
        this.tipo = producto.getTipo();
        this.alergenos = producto.getIngredienteProductos()
                .stream()
                .map(ip -> ip.getIngrediente().getAlergeno().name())
                .distinct()
                .toList();
    }
    
	// Constructor vacío (a veces útil para Spring)
    public ProductoDTO() {
        super();
    }

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Long> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Long> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<String> getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(List<String> alergenos) {
		this.alergenos = alergenos;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "ProductoDTO [idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", descripcion="
				+ descripcion + ", imagen=" + imagen + ", ingredientes=" + ingredientes + ", alergenos=" + alergenos
				+ ", tipo=" + tipo + "]";
	}
    
}