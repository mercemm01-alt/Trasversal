package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	Optional<UsuarioEntity> findByUsuario(String usuario);
	Optional<UsuarioEntity> findByUsuarioAndContrasena(String usuario, String contrasena);

    boolean existsByUsuario(String usuario);
    
    void deleteByUsuario(String usuario);
}
