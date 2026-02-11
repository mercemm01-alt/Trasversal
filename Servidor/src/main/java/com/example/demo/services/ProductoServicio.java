package com.example.demo.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Tipo;
import com.example.demo.Model.ProductoDTO;

public interface ProductoServicio {
    List<ProductoDTO> obtenerProductosPorTipo(Tipo tipo);
    
    List<ProductoDTO> listarProductos();

    void crearProducto(String productoJson, MultipartFile imagen) throws Exception;

    void actualizarProducto(Long idProducto, String productoJson, MultipartFile imagen) throws Exception;
    
    void eliminarProducto(Long idProducto);
}