package com.example.demo.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.EmpleadoEntity;

@Repository
public interface EmpleadoRepository  extends JpaRepository<EmpleadoEntity, Long>{

	Optional<EmpleadoEntity> findByUsuarioUsuario(String usuario);
	
	void deleteByUsuario_Usuario(String usuario);
}
