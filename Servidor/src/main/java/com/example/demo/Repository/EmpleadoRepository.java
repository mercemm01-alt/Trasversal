package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.EmpleadoEntity;

@Repository
public interface EmpleadoRepository  extends JpaRepository<EmpleadoEntity, String>{

	//Consulta para buscar por usuario y contraseña
	@Query("SELECT e FROM EmpleadoEntity e WHERE e.usuario = :usuario AND e.contrasena =:contrasena ")
    public EmpleadoEntity findByUsuarioyContrasena(@Param("usuario") String usuario, @Param("contrasena") String contrasena);

}
