package com.example.demo.services;

import java.util.List;
import com.example.demo.DTO.ProductoDTO;

public interface ProductoServicio {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO guardarProducto(ProductoDTO productoDTO);
    void borrarProducto(Long id);
    ProductoDTO editarProducto(Long id, ProductoDTO productoDTO);
}