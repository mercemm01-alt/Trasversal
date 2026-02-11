package com.example.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.Entity.Rol;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Model.CrearEmpleadoDTO;
import com.example.demo.Model.EmpleadoDTO;
import com.example.demo.Repository.EmpleadoRepository;
import com.example.demo.Repository.JornadaRepository;
import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.services.EmpleadoServicio;

@Service
public class EmpleadoImplement implements EmpleadoServicio{

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private EmpleadoRepository empleadoRepo;
    
    @Autowired
    private JornadaRepository jornadaRepo;

    @Override
    public List<EmpleadoDTO> listarEmpleados() {
        return empleadoRepo.findAll().stream().map(emp -> {
            EmpleadoDTO dto = new EmpleadoDTO();
            dto.setUsuario(emp.getUsuario().getUsuario());
            dto.setNombre(emp.getNombre());
            dto.setApellidos(emp.getApellido());
            dto.setAdministrador(emp.getAdmin() ? "S" : "N");
            return dto;
        }).toList();
    }

    @Override
    public void crearEmpleado(CrearEmpleadoDTO dto) {

        if (usuarioRepo.existsByUsuario(dto.getUsuario())) {
            throw new RuntimeException("El usuario ya existe");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuario(dto.getUsuario());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(
            dto.getAdministrador().equals("S") ? Rol.ADMIN : Rol.EMPLEADO
        );

        usuarioRepo.save(usuario);

        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setUsuario(usuario);
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellidos());
        empleado.setAdmin(dto.getAdministrador().equals("S"));

        empleadoRepo.save(empleado);
    }
    
    @Transactional
    @Override
    public void eliminarEmpleado(String usuario) {
    	EmpleadoEntity empleado = empleadoRepo
    	        .findByUsuarioUsuario(usuario)
    	        .orElseThrow();

    	    jornadaRepo.deleteByEmpleado_IdEmpleado(
    	        empleado.getIdEmpleado()
    	    );

    	    empleadoRepo.delete(empleado);
    	    usuarioRepo.delete(empleado.getUsuario());
    }
}
