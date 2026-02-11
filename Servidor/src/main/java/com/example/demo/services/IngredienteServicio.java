package com.example.demo.services;

import java.util.List;

import com.example.demo.Model.IngredienteDTO;

public interface IngredienteServicio {
    List<IngredienteDTO> obtenerTodos();
    IngredienteDTO obtenerPorId(Long idIngrediente);
    IngredienteDTO actualizarIngrediente(Long idIngrediente, IngredienteDTO dto);
    void eliminarIngrediente(Long idIngrediente);
    IngredienteDTO crearIngrediente(IngredienteDTO dto);

}