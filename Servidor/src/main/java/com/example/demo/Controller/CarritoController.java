package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CarritoDTO;
import com.example.demo.services.CarritoService;

@RestController
@RequestMapping("/api")
public class CarritoController {
	
	@Autowired
    private CarritoService carritoService;
	
	@PostMapping("/carrito")
    public String guardarCarrito(@RequestBody CarritoDTO carritoDTO) {
        carritoService.guardarCarrito(carritoDTO);
        return "ok";
	}
}
