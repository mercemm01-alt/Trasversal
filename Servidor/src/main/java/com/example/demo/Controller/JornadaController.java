package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.JornadaEntity;
import com.example.demo.Model.JornadaAdminDTO;
import com.example.demo.Model.JornadaEmpleadoDTO;
import com.example.demo.Model.JornadaFinDTO;
import com.example.demo.Model.JornadaInicioDTO;
import com.example.demo.Model.RegistroJornadaDTO;
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
    
    @GetMapping("/registros")
    public ResponseEntity<List<RegistroJornadaDTO>> verRegistros() {
        return ResponseEntity.ok(jornadaServicio.obtenerRegistros());
    }

    
}
