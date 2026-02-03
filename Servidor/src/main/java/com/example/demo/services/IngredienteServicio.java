package com.example.demo.services;

import java.util.List;

import com.example.demo.Model.IngredienteDTO;

public interface IngredienteServicio {
    List<IngredienteDTO> obtenerTodos();
    IngredienteDTO guardarIngrediente(IngredienteDTO dto);
    IngredienteDTO actualizarStock(Long id, int cantidadExtra);
    void borrarIngrediente(Long id);
}