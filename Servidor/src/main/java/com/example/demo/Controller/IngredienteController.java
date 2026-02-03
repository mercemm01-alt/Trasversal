package com.example.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.IngredienteDTO;
import com.example.demo.services.IngredienteServicio;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "*")
public class IngredienteController {

    @Autowired
    private IngredienteServicio ingredienteServicio;

    @GetMapping
    public List<IngredienteDTO> verIngredientes() {
        return ingredienteServicio.obtenerTodos();
    }
    //GUARDAR 
    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody IngredienteDTO dto) {
        if(dto.getCantidad() < 0) return ResponseEntity.badRequest().body("Stock negativo");
        return ResponseEntity.ok(ingredienteServicio.guardarIngrediente(dto));
    }
    //ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestParam int cantidadExtra) {
        //llamamos al método de actualizar stock del servicio
        IngredienteDTO result = ingredienteServicio.actualizarStock(id, cantidadExtra);
        if(result != null) return ResponseEntity.ok(result);
        return ResponseEntity.notFound().build();
    }
    //BORRAR
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ingredienteServicio.borrarIngrediente(id);
        return ResponseEntity.ok("Eliminado");
    }
}