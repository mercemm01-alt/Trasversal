package com.example.demo.services.implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ClienteEntity;
import com.example.demo.Entity.Estado;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.PedidoProductoEntity;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Model.CarritoDTO;
import com.example.demo.Model.ProductoCarritoDTO;
import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Repository.PedidoRepository;
import com.example.demo.Repository.ProductoRepository;
import com.example.demo.services.CarritoService;

@Service
public class CarritoImplement implements CarritoService{
	
	@Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

	@Override
	public void guardarCarrito(CarritoDTO carritoDTO) {
		
		// Obtener cliente
        ClienteEntity cliente = clienteRepository
                .findById(carritoDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Buscar pedido EN_PROCESO (carrito activo)
        PedidoEntity pedido = pedidoRepository
                .findByClienteIdClienteAndEstado(cliente.getIdCliente(), Estado.PENDIENTE)
                .orElseGet(() -> {
                    PedidoEntity nuevo = new PedidoEntity();
                    nuevo.setCliente(cliente);
                    nuevo.setFechaPedido(LocalDateTime.now());
                    nuevo.setEstado(Estado.PENDIENTE);
                    nuevo.setTotal(BigDecimal.ZERO);
                    return pedidoRepository.save(nuevo);
                });

        // Vaciar productos anteriores
        pedido.getPedidoProductos().clear();

        BigDecimal total = BigDecimal.ZERO;

        // Añadir productos del carrito
        for (ProductoCarritoDTO item : carritoDTO.getItems()) {

            ProductoEntity producto = productoRepository
                    .findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            PedidoProductoEntity pp = new PedidoProductoEntity();
            pp.setPedido(pedido);
            pp.setProducto(producto);
            pp.setCantidad(item.getCantidad());

            pedido.getPedidoProductos().add(pp);

            total = total.add(
                    producto.getPrecio()
                            .multiply(BigDecimal.valueOf(item.getCantidad()))
            );
        }

        // Actualizar total
        pedido.setTotal(total);

        // Guardar (cascade guarda los PedidoProducto)
        pedidoRepository.save(pedido);
		
	}

}
