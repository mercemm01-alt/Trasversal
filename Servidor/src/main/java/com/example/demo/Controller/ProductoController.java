package com.example.demo.Controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Model.ProductoDTO;
import com.example.demo.Repository.ProductoRepository;

@RestController
@RequestMapping("/api/productos")

public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // LISTAR
    @GetMapping
    public List<ProductoDTO> obtenerTodos() {
        List<ProductoEntity> productos = productoRepository.findAll();
        
        return productos.stream().map(p -> new ProductoDTO(
            (int) p.getIdProducto(), // Convertimos el BIGINT a int para el DTO
            p.getNombre(),
            BigDecimal.valueOf(p.getPrecio()),
            p.getDescripcion()
        )).collect(Collectors.toList());
    }

    // GUARDAR (POST)
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDTO productoDTO) {
        
        // Validación simple
        if(productoDTO.getPrecio().doubleValue() < 0) {
            return ResponseEntity.badRequest().body("Error: El precio no puede ser negativo.");
        }

        ProductoEntity nuevo = new ProductoEntity();
        nuevo.setNombre(productoDTO.getNombre());
        nuevo.setPrecio(productoDTO.getPrecio().doubleValue());
        nuevo.setDescripcion(productoDTO.getDescripcion());

        ProductoEntity guardado = productoRepository.save(nuevo);
        
        // Convertimos el ID Long nuevo a int para devolverlo
        productoDTO.setIdProducto(guardado.getIdProducto());
        return ResponseEntity.ok(productoDTO);
    }

    // EDITAR (PUT) - AQUÍ CAMBIA EL ID A LONG
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        
        // Buscamos directamente con el Long
        ProductoEntity p = productoRepository.findById(id).orElse(null);
        
        if(p != null) {
            p.setNombre(productoDTO.getNombre());
            p.setPrecio(productoDTO.getPrecio().doubleValue());
            p.setDescripcion(productoDTO.getDescripcion());
            
            productoRepository.save(p);
            return ResponseEntity.ok("Producto actualizado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Error: Producto no encontrado.");
        }
    }

    // BORRAR (DELETE) - AQUÍ CAMBIA EL ID A LONG
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        if(productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado.");
        } else {
            return ResponseEntity.status(404).body("Error: No se pudo eliminar, no existe.");
        }
    }
}