package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.HacerPedidoDTO;
import com.example.demo.Model.VerPedidoDTO;
import com.example.demo.services.PedidoServicio;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.Date;
import org.springframework.http.ResponseEntity;
import com.example.demo.Repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired private PedidoServicio pedidoService;
	
	@Autowired private PedidoRepository pedidoRepository;
	
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
	
	@GetMapping("/beneficios")
    public ResponseEntity<?> verBeneficios(@RequestParam String periodo) {
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy;
        LocalDate fin = hoy;

        switch (periodo.toLowerCase()) {
            case "dia":
                // Se queda como está (hoy)
                break;
            case "semana":
                inicio = hoy.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
                fin = hoy.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
                break;
            case "mes":
                inicio = hoy.with(TemporalAdjusters.firstDayOfMonth());
                fin = hoy.with(TemporalAdjusters.lastDayOfMonth());
                break;
            default:
                return ResponseEntity.badRequest().body("Periodo no válido. Usa: dia, semana, mes");
        }

        // Convertimos LocalDate a Date
        Date fechaInicio = Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fechaFin = Date.from(fin.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());

        // Consultamos al repositorio
        Double beneficios = pedidoRepository.obtenerBeneficios(fechaInicio, fechaFin);
        
        return ResponseEntity.ok(Collections.singletonMap("beneficio", beneficios));
    }
    
}