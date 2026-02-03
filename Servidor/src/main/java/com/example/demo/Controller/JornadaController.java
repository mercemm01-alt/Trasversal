package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.JornadaEntity;
import com.example.demo.services.JornadaServicio;

@RestController
@RequestMapping("/api/jornadas")
public class JornadaController {

    @Autowired
    private JornadaServicio jornadaServicio;

    //FICHAR ENTRADA (CREAMOS LA JORNADA)
    @PostMapping("/entrada/{usuario}")
    public ResponseEntity<JornadaEntity> fichaEntrada(@PathVariable String usuario) {

        JornadaEntity jornada = jornadaServicio.anadirJornada(usuario);

        return ResponseEntity.ok(jornada);
    }

    //FICHAR SALIDA (REGISTRAMOS LA HORA DE SALIDA EN LA JORNADA YA EXISTENTE)
    @PutMapping("/salida/{id}")
    public ResponseEntity<JornadaEntity> fichaSalida(@PathVariable Long id) {

        JornadaEntity jornada = jornadaServicio.finalizarJornada(id);

        return ResponseEntity.ok(jornada);
    }

    //LISTA DE JORNADAS
    @GetMapping
    public ResponseEntity<List<JornadaEntity>> listaJornadas() {

        List<JornadaEntity> jornadas = jornadaServicio.listJornadas();

        return ResponseEntity.ok(jornadas);
    }
}
