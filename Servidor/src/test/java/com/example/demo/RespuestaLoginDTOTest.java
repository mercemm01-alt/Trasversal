package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.Model.RespuestaLoginDTO;
import com.example.demo.Entity.Rol;

class RespuestaLoginDTOTest {

    @Test
    void testConstructorVacio() {
        RespuestaLoginDTO dto = new RespuestaLoginDTO();

        assertNull(dto.getIdUsuarios());
        assertNull(dto.getUsuario());
        assertNull(dto.getRol());
    }

    @Test
    void testConstructorConParametros() {
        Long idEsperado = 1L;
        String usuarioEsperado = "admin";
        Rol rolEsperado = null;

        RespuestaLoginDTO dto = new RespuestaLoginDTO(idEsperado, usuarioEsperado, rolEsperado);

        assertEquals(idEsperado, dto.getIdUsuarios());
        assertEquals(usuarioEsperado, dto.getUsuario());
        assertEquals(rolEsperado, dto.getRol());
    }

    @Test
    void testSettersYGetters() {

        RespuestaLoginDTO dto = new RespuestaLoginDTO();

        Long idEsperado = 2L;
        String usuarioEsperado = "empleado";
        Rol rolEsperado = null;

        dto.setIdUsuarios(idEsperado);
        dto.setUsuario(usuarioEsperado);
        dto.setRol(rolEsperado);

        assertEquals(idEsperado, dto.getIdUsuarios());
        assertEquals(usuarioEsperado, dto.getUsuario());
        assertEquals(rolEsperado, dto.getRol());
    }
}
