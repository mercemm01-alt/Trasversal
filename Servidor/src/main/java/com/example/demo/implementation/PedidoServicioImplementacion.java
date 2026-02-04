package com.example.demo.implementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.PedidoDTO;
import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.Estado;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Repository.PedidoRepository;
import com.example.demo.Repository.ProductoRepository;
import com.example.demo.services.PedidoServicio;

@Service
public class PedidoServicioImplementacion implements PedidoServicio {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ProductoRepository productoRepository;
    @Autowired private ClienteRepository clienteRepository;

    @Override
    public PedidoDTO crearPedido(PedidoDTO dto) {
        // 1. Validar fecha (regla de los 2 días)
        if (dto.getFechaEntrega() == null) throw new RuntimeException("Falta fecha de entrega");

        LocalDate fechaEntrega = dto.getFechaEntrega().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate hoy = LocalDate.now();

        if (ChronoUnit.DAYS.between(hoy, fechaEntrega) < 2) {
            throw new RuntimeException("El pedido debe hacerse con 2 días de antelación.");
        }

        // 2. Buscar entidades
        ClienteEntity cliente = clienteRepository.findById(dto.getCliente()).orElseThrow(() -> new RuntimeException("Cliente no existe"));
        ProductoEntity producto = productoRepository.findById(dto.getProducto()).orElseThrow(() -> new RuntimeException("Producto no existe"));

        // 3. Crear entidad y calcular total
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        pedido.setProducto(producto);
        pedido.setCantidad(dto.getCantidad());
        pedido.setFechaPedido(new Date()); // Fecha actual
        pedido.setFechaEntrega(dto.getFechaEntrega());
        pedido.setEstado(Estado.EN_PROCESO);
        
        double total = producto.getPrecio() * dto.getCantidad();
        pedido.setTotal(total);

        // 4. Guardar
        PedidoEntity guardado = pedidoRepository.save(pedido);
        
        // 5. Actualizar DTO para devolverlo
        dto.setIdPedido(guardado.getIdPedido());
        dto.setPrecioFinal(total);
        dto.setEstado(Estado.EN_PROCESO);
        
        return dto;
    }

    @Override
    public List<PedidoDTO> listarPedidosPendientes() {
        return pedidoRepository.findByEstadoOrderByFechaEntregaAsc(Estado.EN_PROCESO)
                .stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public void marcarEntregado(Long idPedido) {
        PedidoEntity p = pedidoRepository.findById(idPedido).orElse(null);
        if(p != null) {
            p.setEstado(Estado.ENTREGADO);
            pedidoRepository.save(p);
        }
    }

    // privado para convertir
    private PedidoDTO convertirADTO(PedidoEntity p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(p.getIdPedido());
        dto.setCliente(p.getCliente().getIdCliente());
        dto.setProducto(p.getProducto().getIdProducto());
        dto.setCantidad(p.getCantidad());
        dto.setFechaInicio(p.getFechaPedido());
        dto.setFechaEntrega(p.getFechaEntrega());
        dto.setEstado(p.getEstado());
        dto.setPrecioFinal(p.getTotal());
        return dto;
    }
}