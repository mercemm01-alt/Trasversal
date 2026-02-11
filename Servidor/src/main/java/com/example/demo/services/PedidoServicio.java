package com.example.demo.services;

import java.util.List;

import com.example.demo.Model.HacerPedidoDTO;
import com.example.demo.Model.VerPedidoDTO;

public interface PedidoServicio {
    void crearPedido(HacerPedidoDTO pedidoDTO);
    List<VerPedidoDTO> obtenerPedidosDelMes(); // Para historial
    void marcarEntregado(Long idPedido);
    
    /*List<PedidoDTO> listarPedidosPendientes(); // Para empleados*/
}