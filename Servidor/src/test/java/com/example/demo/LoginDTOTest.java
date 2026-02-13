package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.Model.LoginDTO;

class LoginDTOTest {

    @Test
    void testConstructorVacio() {
        LoginDTO login = new LoginDTO();

        assertNull(login.getUsuario());
        assertNull(login.getContrasena());
    }

    @Test
    void testConstructorConParametros() {
        String usuarioEsperado = "admin";
        String contrasenaEsperada = "123456";

        LoginDTO login = new LoginDTO(usuarioEsperado, contrasenaEsperada);

        assertEquals(usuarioEsperado, login.getUsuario());
        assertEquals(contrasenaEsperada, login.getContrasena());
    }

    @Test
    void testSettersYGetters() {
        LoginDTO login = new LoginDTO();
        
        login.setUsuario("empleado1");
        login.setContrasena("secreta");

        assertEquals("empleado1", login.getUsuario());
        assertEquals("secreta", login.getContrasena());
    }
}