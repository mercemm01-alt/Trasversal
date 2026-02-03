package com.example.demo.services;

import java.util.List;

import com.example.demo.Model.ProductoDTO;

public interface ProductoServicio {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO guardarProducto(ProductoDTO productoDTO);
    void borrarProducto(Long id);
    ProductoDTO editarProducto(Long id, ProductoDTO productoDTO);
}