package com.example.demo.Controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.IngredientesEntity;
import com.example.demo.Model.IngredienteDTO;
import com.example.demo.Repository.IngredienteRepository;

@RestController
@RequestMapping("/api/ingredientes")

public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // LISTAR
    @GetMapping
    public List<IngredienteDTO> verIngredientes() {
        List<IngredientesEntity> lista = ingredienteRepository.findAll();
        
        return lista.stream().map(i -> new IngredienteDTO(
            i.getIdIngredientes().intValue(), // Long a int
            i.getNombre(),
            i.getCantidad(),
            i.getAlergenos()
        )).collect(Collectors.toList());
    }

    // GUARDAR
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarIngrediente(@RequestBody IngredienteDTO dto) {
        
        if(dto.getCantidad() < 0) {
            return ResponseEntity.badRequest().body("Error: El stock no puede ser negativo.");
        }

        IngredientesEntity entidad = new IngredientesEntity();
        entidad.setNombre(dto.getNombre());
        entidad.setCantidad(dto.getCantidad());
        entidad.setAlergenos(dto.getAlergenos());
        
        IngredientesEntity guardado = ingredienteRepository.save(entidad);
        
        dto.setIdIngrediente(guardado.getIdIngredientes().intValue());
        return ResponseEntity.ok(dto);
    }

    // ACTUALIZAR - ID AHORA ES LONG
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarStock(@PathVariable Long id, @RequestBody IngredienteDTO dto) {
        
        IngredientesEntity existente = ingredienteRepository.findById(id).orElse(null);
        
        if(existente != null) {
            existente.setNombre(dto.getNombre());
            existente.setCantidad(dto.getCantidad());
            existente.setAlergenos(dto.getAlergenos());
            
            ingredienteRepository.save(existente);
            return ResponseEntity.ok("Stock actualizado.");
        } 
        return ResponseEntity.status(404).body("Error: Ingrediente no encontrado.");
    }
    
    // BORRAR - ID AHORA ES LONG
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if(ingredienteRepository.existsById(id)){
            ingredienteRepository.deleteById(id);
            return ResponseEntity.ok("Ingrediente eliminado del almacén.");
        }
        return ResponseEntity.status(404).body("Error: No existe ese ingrediente.");
    }
}