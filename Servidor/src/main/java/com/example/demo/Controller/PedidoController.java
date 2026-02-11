package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.HacerPedidoDTO;
import com.example.demo.Model.VerPedidoDTO;
import com.example.demo.services.PedidoServicio;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired private PedidoServicio pedidoService;
	
	@PostMapping
	public String crearpedido(@RequestBody HacerPedidoDTO pedidoDTO) {
		pedidoService.crearPedido(pedidoDTO);
		return "ok";	
	}
	
	@GetMapping("/mes")
	public List<VerPedidoDTO> pedidosDelMes() {
	    return pedidoService.obtenerPedidosDelMes();
	}
	
	@PutMapping("/{id}")
	public String marcarEntregado(@PathVariable Long id) {
	    pedidoService.marcarEntregado(id);
	    return "ok";
	}
    
}