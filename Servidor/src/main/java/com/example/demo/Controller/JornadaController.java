package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.JornadaEntity;
import com.example.demo.Model.JornadaAdminDTO;
import com.example.demo.Model.JornadaEmpleadoDTO;
import com.example.demo.Model.JornadaFinDTO;
import com.example.demo.Model.JornadaInicioDTO;
import com.example.demo.services.JornadaServicio;

@RestController
@RequestMapping("/api/jornadas")
public class JornadaController {

    @Autowired
    private JornadaServicio jornadaServicio;

    @GetMapping("/hoy/{usuario}")
    public List<JornadaEmpleadoDTO> hoyEmpleado(@PathVariable String usuario) {
        return jornadaServicio.obtenerJornadasHoyUsuario(usuario);
    }

    @PostMapping("/inicio")
    public void iniciar(@RequestBody JornadaInicioDTO dto) {
    	jornadaServicio.iniciarJornada(dto);
    }
    
    @GetMapping("/abierta/{usuario}")
    public JornadaEntity jornadaAbierta(@PathVariable String usuario) {
        return jornadaServicio.obtenerJornadaAbierta(usuario);
    }

    @PutMapping("/fin")
    public void finalizar(@RequestBody JornadaFinDTO dto) {
    	jornadaServicio.finalizarJornada(dto);
    }

    // ADMIN
    @GetMapping("/admin/todas")
    public List<JornadaAdminDTO> todasAdmin() {
        return jornadaServicio.obtenerTodasJornadasAdmin();
    }
}
