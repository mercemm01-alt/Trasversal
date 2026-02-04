package com.example.demo.services;

import java.util.List;

import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Model.PedidoDTO;

public interface PedidoServicio {
    PedidoDTO crearPedido(PedidoDTO pedidoDTO);
    List<PedidoDTO> listarPedidosPendientes(); // Para empleados
    List<PedidoDTO> listarTodos(); // Para historial
    void marcarEntregado(Long idPedido);
}