package com.example.demo.services.implementation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.IngredienteEntity;
import com.example.demo.Entity.IngredienteProductoEntity;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Entity.Tipo;
import com.example.demo.Model.ProductoDTO;
import com.example.demo.Repository.IngredienteProductoRepository;
import com.example.demo.Repository.IngredienteRepository;
import com.example.demo.Repository.ProductoRepository;
import com.example.demo.services.ProductoServicio;

import tools.jackson.databind.ObjectMapper;

@Service
public class ProductoServicioImplementacion implements ProductoServicio {

	@Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private IngredienteProductoRepository ingredienteProductoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    /* =========================
       LISTAR TODOS LOS PRODUCTOS
       ========================= */
    @Override
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    /* =========================
       LISTAR PRODUCTOS POR TIPO
       ========================= */
    @Override
    public List<ProductoDTO> obtenerProductosPorTipo(Tipo tipo) {
        return productoRepository.findByTipo(tipo)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    /* =========================
       CREAR PRODUCTO
       ========================= */
    @Override
    public void crearProducto(String productoJson, MultipartFile imagen) throws Exception {

        ProductoDTO dto = objectMapper.readValue(productoJson, ProductoDTO.class);

        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setTipo(dto.getTipo());

        if (imagen != null) {
            producto.setImagen(imagen.getOriginalFilename());
 //           Path ruta = Paths.get("src/main/resources/static/img/" + imagen.getOriginalFilename());
            String uploadDir = System.getProperty("user.dir") + "/uploads/img/";
            Files.write(Paths.get(uploadDir + imagen.getOriginalFilename()), imagen.getBytes());
        }

        productoRepository.save(producto);

        guardarIngredienteProducto(producto, dto.getIngredientes());
    }

    /* =========================
       ACTUALIZAR PRODUCTO
       ========================= */
    @Transactional
    @Override
    public void actualizarProducto(
            Long idProducto,
            String productoJson,
            MultipartFile imagen
    ) throws Exception {

        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductoDTO dto = objectMapper.readValue(productoJson, ProductoDTO.class);

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setTipo(dto.getTipo());

        if (imagen != null) {
            producto.setImagen(imagen.getOriginalFilename());
        }

        // 🔴 Eliminar relaciones anteriores
        ingredienteProductoRepository.deleteByProducto(producto);

        // 🔴 Guardar nuevas relaciones
        guardarIngredienteProducto(producto, dto.getIngredientes());

        productoRepository.save(producto);
    }

    /* =========================
       MÉTODO CLAVE: ENTITY → DTO
       ========================= */
    private ProductoDTO convertirADTO(ProductoEntity producto) {

        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setDescripcion(producto.getDescripcion());
        dto.setImagen(producto.getImagen());
        dto.setTipo(producto.getTipo());

        // 🔥 AQUÍ se calculan los alérgenos
        List<Long> ingredientes = producto.getIngredienteProductos()
                .stream()
                .map(ip -> ip.getIngrediente().getIdIngredientes())
                .toList();

        List<String> alergenos = producto.getIngredienteProductos()
                .stream()
                .map(ip -> ip.getIngrediente().getAlergeno().name())
                .distinct()
                .toList();

        dto.setIngredientes(ingredientes);
        dto.setAlergenos(alergenos);

        return dto;
    }

    /* =========================
       GUARDAR INGREDIENTES
       ========================= */
    private void guardarIngredienteProducto(
            ProductoEntity producto,
            List<Long> ingredientesIds
    ) {
        if (ingredientesIds == null || ingredientesIds.isEmpty()) return;

        List<IngredienteEntity> ingredientes =
                ingredienteRepository.findAllById(ingredientesIds);

        for (IngredienteEntity ingrediente : ingredientes) {
            IngredienteProductoEntity ip = new IngredienteProductoEntity();
            ip.setProducto(producto);
            ip.setIngrediente(ingrediente);
            ingredienteProductoRepository.save(ip);
        }
    }
    
    @Transactional
    @Override
    public void eliminarProducto(Long idProducto) {

        ProductoEntity producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Eliminar relaciones producto–ingrediente primero
        ingredienteProductoRepository.deleteByProducto(producto);

        // Eliminar producto
        productoRepository.delete(producto);
    }
   
}