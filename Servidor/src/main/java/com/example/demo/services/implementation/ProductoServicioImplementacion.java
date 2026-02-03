package com.example.demo.services.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Model.ProductoDTO;
import com.example.demo.Repository.ProductoRepository;
import com.example.demo.services.ProductoServicio;

@Service
public class ProductoServicioImplementacion implements ProductoServicio {

    @Autowired private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return productoRepository.findAll().stream().map(p -> new ProductoDTO(
            (int) p.getIdProducto(),
            p.getNombre(),
            BigDecimal.valueOf(p.getPrecio()),
            p.getDescripcion()
        )).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO guardarProducto(ProductoDTO dto) {
        ProductoEntity p = new ProductoEntity();
        p.setNombre(dto.getNombre());
        p.setPrecio(dto.getPrecio().doubleValue());
        p.setDescripcion(dto.getDescripcion());
        
        ProductoEntity guardado = productoRepository.save(p);
        dto.setIdProducto((int) guardado.getIdProducto());
        return dto;
    }

    @Override
    public void borrarProducto(Long id) {
        if(productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        }
    }

    @Override
    public ProductoDTO editarProducto(Long id, ProductoDTO dto) {
        ProductoEntity p = productoRepository.findById(id).orElse(null);
        if(p != null) {
            p.setNombre(dto.getNombre());
            p.setPrecio(dto.getPrecio().doubleValue());
            p.setDescripcion(dto.getDescripcion());
            productoRepository.save(p);
            return dto;
        }
        return null;
    }
}