package com.example.demo.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.Model.LoginEmpleadoDTO;
import com.example.demo.Repository.EmpleadoRepository;
import com.example.demo.services.EmpleadoServicio;

@Service
public class EmpleadoServicioImplementacion implements EmpleadoServicio{

	@Autowired
	EmpleadoRepository repo;

	@Override
	public EmpleadoEntity loginEmpleado(String usuario, String contrasena) {
		EmpleadoEntity empleado = new EmpleadoEntity();
		LoginEmpleadoDTO login = new LoginEmpleadoDTO(usuario, contrasena);
//		boolean check = false;
		
		empleado = repo.findByUsuarioyContrasena(usuario, contrasena);
		if(empleado==null) {
			System.out.println("HOLA NO ENCUENTRA AL USUARIO");
//			check=false;
			empleado = new EmpleadoEntity();
			empleado.setNombre("Usuario o Contraseña Incorrectos."); // a modo de mensaje para mostrarlo, aunque sea raro
			
		} else {
			System.out.println("AAAAAAAAAAA");
			login.setUsuario(empleado.getUsuario());
			login.setContrasena(empleado.getContrasena());
//			check=true;
		}
		
		System.out.println(empleado.getNombre());
		return empleado;
	}
//.
	@Override
	public List<EmpleadoEntity> listEmpleados() {
		List<EmpleadoEntity> empleados;
		empleados = repo.findAll();
		
		System.out.println(empleados);
		return empleados;
	}

	@Override
	public EmpleadoEntity anadirEmpleado(String usuario, String contrasena, String nombre, String apellidos, String admin) {
		EmpleadoEntity empleado = new EmpleadoEntity();
		empleado.setNombre(nombre);
		empleado.setApellido(apellidos);
		empleado.setUsuario(usuario);
		empleado.setContrasena(contrasena);
		empleado.setAdmin(admin);
		
		repo.save(empleado);
		return empleado;
	}

	@Override
	public void eliminarEmpleado(String usuario) {
		repo.deleteById(usuario);
		
	}

	
}
