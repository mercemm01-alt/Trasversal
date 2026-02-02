package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.PedidoDTO;
import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.Estado;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Repository.PedidoRepository;
import com.example.demo.Repository.ProductoRepository;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private ClienteRepository clienteRepository;

    // --- 1. CREAR PEDIDO (Para el Cliente) ---
    @PostMapping("/crear")
    public ResponseEntity<?> crearPedido(@RequestBody PedidoDTO dto) {
        
        // A. Validar regla de los 2 días
        if (dto.getFechaEntrega() == null) {
            return ResponseEntity.badRequest().body("Error: Debes indicar fecha de entrega.");
        }
        
        // Conversión de java.util.Date a LocalDate para calcular los días
        LocalDate fechaEntrega = dto.getFechaEntrega().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
        LocalDate hoy = LocalDate.now();
        
        long diasDiferencia = ChronoUnit.DAYS.between(hoy, fechaEntrega);

        if (diasDiferencia < 2) {
            return ResponseEntity.badRequest().body("Error: El pedido debe hacerse con al menos 2 días de antelación.");
        }

        // B. Buscar IDs (Cliente y Producto). getCliente() y getProducto() porque son longs en DTO
        ClienteEntity cliente = clienteRepository.findById(dto.getCliente()).orElse(null);
        ProductoEntity producto = productoRepository.findById(dto.getProducto()).orElse(null);

        if (cliente == null || producto == null) {
            return ResponseEntity.badRequest().body("Error: Cliente o Producto no encontrados.");
        }

        // C. Crear la Entidad
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        pedido.setProducto(producto);
        pedido.setCantidad(dto.getCantidad());
        pedido.setFechaPedido(new Date()); // Fecha actual (Inicio)
        pedido.setFechaEntrega(dto.getFechaEntrega()); 
        
        // Asignamos estado inicial (para cambiar el estado aqui)
        pedido.setEstado(Estado.EN_PROCESO); 
        
        // D. Calcular Precio Final
        double precioUnitario = producto.getPrecio();
        double total = precioUnitario * dto.getCantidad();
        pedido.setTotal(total);

        // E. Guardar
        PedidoEntity guardado = pedidoRepository.save(pedido);
        
        // Actualizamos el DTO para devolverlo
        dto.setIdPedido(guardado.getIdPedido());
        dto.setFechaInicio(guardado.getFechaPedido());
        dto.setPrecioFinal(total);
        dto.setEstado(guardado.getEstado());
        
        return ResponseEntity.ok(dto);
    }

    // --- 2. LISTAR PENDIENTES (Para Empleados) ---
    @GetMapping("/pendientes")
    public List<PedidoDTO> verPendientes() {
        // Buscamos por el Enum EN_PROCESO (importante cambiar si no es el estado)
        List<PedidoEntity> pendientes = pedidoRepository.findByEstadoOrderByFechaEntregaAsc(Estado.EN_PROCESO);
        
        return pendientes.stream().map(this::convertirADTO).collect(Collectors.toList());
    }
    
    // --- 3. LISTAR TODOS ---
    @GetMapping
    public List<PedidoDTO> verTodos() {
        return pedidoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    // --- 4. MARCAR ENTREGADO ---
    @PutMapping("/entregar/{id}")
    public ResponseEntity<?> marcarEntregado(@PathVariable Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElse(null);
        
        if (pedido != null) {
            pedido.setEstado(Estado.ENTREGADO); // Usamos Enum
            pedidoRepository.save(pedido);
            return ResponseEntity.ok("Pedido entregado.");
        }
        return ResponseEntity.status(404).body("Error: Pedido no encontrado.");
    }
    
    // Método auxiliar para limpiar código
    private PedidoDTO convertirADTO(PedidoEntity p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(p.getIdPedido());
        dto.setCliente(p.getCliente().getIdCliente().longValue());
        dto.setProducto(p.getProducto().getIdProducto());
        dto.setCantidad(p.getCantidad());
        dto.setFechaInicio(p.getFechaPedido());
        dto.setFechaEntrega(p.getFechaEntrega());
        dto.setEstado(p.getEstado());
        dto.setPrecioFinal(p.getTotal());
        return dto;
    }
}