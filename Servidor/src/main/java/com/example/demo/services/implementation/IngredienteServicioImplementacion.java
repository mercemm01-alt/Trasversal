package com.example.demo.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.IngredientesEntity;
import com.example.demo.Model.IngredienteDTO;
import com.example.demo.Repository.IngredienteRepository;
import com.example.demo.services.IngredienteServicio;

@Service
public class IngredienteServicioImplementacion implements IngredienteServicio {

    @Autowired private IngredienteRepository ingredienteRepository;

    @Override
    public List<IngredienteDTO> obtenerTodos() {
        return ingredienteRepository.findAll().stream().map(i -> new IngredienteDTO(
            i.getIdIngredientes().intValue(),
            i.getNombre(),
            i.getCantidad(),
            i.getAlergenos() 
        )).collect(Collectors.toList());
    }

    @Override
    public IngredienteDTO guardarIngrediente(IngredienteDTO dto) {
        IngredientesEntity i = new IngredientesEntity();
        i.setNombre(dto.getNombre());
        i.setCantidad(dto.getCantidad());
        // i.setAlergenos(dto.getAlergenos()); // Revisa el tipo de dato de Alergenos en Entity
        
        IngredientesEntity guardado = ingredienteRepository.save(i);
        dto.setIdIngrediente(guardado.getIdIngredientes().intValue());
        return dto;
    }

    @Override
    public IngredienteDTO actualizarStock(Long id, int cantidadExtra) {
        IngredientesEntity i = ingredienteRepository.findById(id).orElse(null);
        if(i != null) {
            i.setCantidad(i.getCantidad() + cantidadExtra);
            ingredienteRepository.save(i);
            // Devolver DTO actualizado
            return new IngredienteDTO(i.getIdIngredientes().intValue(), i.getNombre(), i.getCantidad(), null);
        }
        return null;
    }

    @Override
    public void borrarIngrediente(Long id) {
        if(ingredienteRepository.existsById(id)) {
            ingredienteRepository.deleteById(id);
        }
    }
}