package com.example.demo.services.implementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.Estado;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.PedidoProductoEntity;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Model.HacerPedidoDTO;
import com.example.demo.Model.PedidoProductoDTO;
import com.example.demo.Model.VerPedidoDTO;
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
	public void crearPedido(HacerPedidoDTO pedidoDTO) {
    	System.out.println("ID USUARIO RECIBIDO: " + pedidoDTO.getUsuario());

    	// Validar cliente
    	ClienteEntity cliente = clienteRepository
    	        .findByUsuario_IdUsuarios(pedidoDTO.getUsuario())
    	        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Validar fecha (48h)
        LocalDateTime ahora = LocalDateTime.now();
        
        LocalDateTime fechaEntrega = pedidoDTO.getFechaEntrega().atStartOfDay();
        if (fechaEntrega.isBefore(ahora.plusHours(48))) {
            throw new RuntimeException("La fecha de entrega debe ser al menos 48h después");
        }

        //  Crear pedido
        PedidoEntity pedido = new PedidoEntity();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(ahora);
        pedido.setFechaEntrega(fechaEntrega);
        pedido.setEstado(Estado.PENDIENTE);
        pedido.setTotal(pedidoDTO.getTotal());

        // Productos del pedido
        for (PedidoProductoDTO p : pedidoDTO.getProductos()) {
        	if (p.getIdProducto() == null) {
                throw new IllegalArgumentException("ID de producto no puede ser nulo. Verifica los productos en el carrito.");
            }

            ProductoEntity producto = productoRepository.findById(p.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            PedidoProductoEntity pedidoProducto = new PedidoProductoEntity();
            pedidoProducto.setPedido(pedido);
            pedidoProducto.setProducto(producto);
            pedidoProducto.setCantidad(p.getCantidad());

            pedido.getPedidoProductos().add(pedidoProducto);
            
            if (pedido.getPedidoProductos().isEmpty()) {
                throw new IllegalArgumentException("El pedido debe contener al menos un producto válido.");
            }
        }

        // Guardar todo (cascade)
        pedidoRepository.save(pedido);
		
	}
    
    private VerPedidoDTO convertirAVerPedidoDTO(PedidoEntity p) {
        return new VerPedidoDTO(
            p.getIdPedido(),
            p.getCliente().getNombre(),
            p.getCliente().getApellidos(),
            p.getFechaPedido(),
            p.getFechaEntrega(),
            p.getTotal(),
            p.getEstado().name()
        );
    }
    
    @Override
    public List<VerPedidoDTO> obtenerPedidosDelMes() {
    	 return pedidoRepository.findPedidosDelMes().stream()
    		        .map(this::convertirAVerPedidoDTO)
    		        .toList();
    }

    @Override
    public void marcarEntregado(Long idPedido) {
        PedidoEntity pedido = pedidoRepository.findById(idPedido).orElseThrow(() 
        		-> new RuntimeException("Pedido no encontrado"));

        pedido.setEstado(Estado.ENTREGADO);
        pedidoRepository.save(pedido);
    }
    

   /* @Override
    public List<PedidoDTO> listarPedidosPendientes() {
        return pedidoRepository.findByEstadoOrderByFechaEntregaAsc(Estado.EN_PROCESO)
                .stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    

    

    // privado para convertir
    private PedidoDTO convertirADTO(PedidoEntity p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(p.getIdPedido());
        dto.setCliente(p.getCliente().getIdCliente());
        dto.setProducto(p.getProducto().getIdProducto());
        dto.setCantidad(p.getCantidad());
        dto.setFechaInicio(p.getFechaInicio());
        dto.setFechaEntrega(p.getFechaEntrega());
        dto.setEstado(p.getEstado());
        dto.setPrecioFinal(p.getPrecioFinal());
        return dto;
    }*/
}