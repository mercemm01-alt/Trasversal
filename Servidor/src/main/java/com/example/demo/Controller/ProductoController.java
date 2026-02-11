package com.example.demo.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Tipo;
import com.example.demo.Model.ProductoDTO;
import com.example.demo.services.ProductoServicio;

@RestController
@RequestMapping("/api")

public class ProductoController {

	 @Autowired
	    private ProductoServicio productoService;

    // LISTAR
	 @GetMapping("/productos/panaderia")
	    public List<ProductoDTO> obtenerPanaderia() {
	        return productoService.obtenerProductosPorTipo(Tipo.PANADERIA);
	    }

	    @GetMapping("/productos/pasteleria")
	    public List<ProductoDTO> obtenerPasteleria() {
	        return productoService.obtenerProductosPorTipo(Tipo.PASTELERIA);
	    }

	    @GetMapping("/productos")
	    public List<ProductoDTO> listarProductos() {
	        return productoService.listarProductos();
	    }

	    @PostMapping(value = "/productos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> crearProducto(@RequestPart("producto") String producto,
	            @RequestPart(value = "imagen", required = false) MultipartFile imagen
	    ) throws Exception {

	        productoService.crearProducto(producto, imagen);
	        return ResponseEntity.ok().build();
	    }

	    @PutMapping(value = "/productos/{idProducto}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<?> actualizarProducto(
	            @PathVariable Long idProducto,
	            @RequestPart("producto") String producto,
	            @RequestPart(value = "imagen", required = false) MultipartFile imagen
	    ) throws Exception {
	    	
	        productoService.actualizarProducto(idProducto, producto, imagen);
	        return ResponseEntity.ok().build();
	    }
	    
	    @DeleteMapping("/productos/{idProducto}")
	    public ResponseEntity<?> eliminarProducto(@PathVariable Long idProducto) {
	        productoService.eliminarProducto(idProducto);
	        return ResponseEntity.ok().build();
	    }

}