package com.example.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Alergenos;
import com.example.demo.Entity.IngredienteEntity;
import com.example.demo.Model.IngredienteDTO;
import com.example.demo.Repository.IngredienteRepository;
import com.example.demo.services.IngredienteServicio;

@Service
public class IngredienteServicioImplementacion implements IngredienteServicio {

    @Autowired private IngredienteRepository ingredienteRepository;

    @Override
    public List<IngredienteDTO> obtenerTodos() {
        return ingredienteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public IngredienteDTO obtenerPorId(Long idIngrediente) {
        IngredienteEntity ingrediente = ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

        return convertirADTO(ingrediente);
    }

    @Override
    public IngredienteDTO actualizarIngrediente(Long idIngrediente, IngredienteDTO dto) {

        IngredienteEntity ingrediente = ingredienteRepository.findById(idIngrediente)
                .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

        ingrediente.setNombre(dto.getNombre());
        ingrediente.setStock(dto.getStock());
        ingrediente.setAlergeno(Alergenos.valueOf(dto.getAlergeno()));

        ingredienteRepository.save(ingrediente);

        return convertirADTO(ingrediente);
    }

    @Override
    public void eliminarIngrediente(Long idIngrediente) {
    	IngredienteEntity ingrediente = ingredienteRepository.findById(idIngrediente)
    	        .orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));

    	    if (!ingrediente.getIngredienteProductos().isEmpty()) {
    	        throw new RuntimeException("No se puede eliminar el ingrediente porque está asociado a productos");
    	    }

    	    ingredienteRepository.delete(ingrediente);
    }

    
    // Convertir de Entity a DTO
    private IngredienteDTO convertirADTO(IngredienteEntity entity) {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setIdIngrediente(entity.getIdIngredientes());
        dto.setNombre(entity.getNombre());
        dto.setStock(entity.getStock());
        dto.setAlergeno(entity.getAlergeno().name());
        return dto;
    }
    
    @Override
    public IngredienteDTO crearIngrediente(IngredienteDTO dto) {

        IngredienteEntity ingrediente = new IngredienteEntity();
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setStock(dto.getStock());
        ingrediente.setAlergeno(Alergenos.valueOf(dto.getAlergeno()));
        
        ingrediente.setAlergeno(Alergenos.valueOf(dto.getAlergeno()));

        IngredienteEntity guardado = ingredienteRepository.save(ingrediente);

        return convertirADTO(guardado);
    }
}