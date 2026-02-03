package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.ProductoDTO;
import com.example.demo.services.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<ProductoDTO> obtenerTodos() {
        return productoServicio.obtenerTodos();
    }
    //GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDTO dto) {
        if(dto.getPrecio().doubleValue() < 0) {
            return ResponseEntity.badRequest().body("Precio negativo no válido");
        }
        return ResponseEntity.ok(productoServicio.guardarProducto(dto));
    }
    //EDITAR
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO result = productoServicio.editarProducto(id, dto);
        if(result != null) return ResponseEntity.ok(result);
        return ResponseEntity.notFound().build();
    }
    //BORRAR
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        productoServicio.borrarProducto(id);
        return ResponseEntity.ok("Eliminado");
    }
}