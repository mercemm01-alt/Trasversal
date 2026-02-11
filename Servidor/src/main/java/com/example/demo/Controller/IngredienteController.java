package com.example.demo.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.IngredienteDTO;
import com.example.demo.services.IngredienteServicio;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

	@Autowired
    private IngredienteServicio ingredienteService;

    // VER INVENTARIO
    @GetMapping
    public List<IngredienteDTO> obtenerIngredientes() {
        return ingredienteService.obtenerTodos();
    }

    // VER UN INGREDIENTE
    @GetMapping("/{id}")
    public IngredienteDTO obtenerIngrediente(@PathVariable Long id) {
        return ingredienteService.obtenerPorId(id);
    }

    // EDITAR INGREDIENTE
    @PutMapping("/{id}")
    public IngredienteDTO actualizarIngrediente(
            @PathVariable Long id,
            @RequestBody IngredienteDTO dto) {

        return ingredienteService.actualizarIngrediente(id, dto);
    }

    // ELIMINAR INGREDIENTE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarIngrediente(@PathVariable Long id) {
        try {
            ingredienteService.eliminarIngrediente(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping
    public ResponseEntity<IngredienteDTO> crearIngrediente(@RequestBody IngredienteDTO dto) {

        IngredienteDTO nuevo = ingredienteService.crearIngrediente(dto);
        return ResponseEntity.ok(nuevo);
    }
}