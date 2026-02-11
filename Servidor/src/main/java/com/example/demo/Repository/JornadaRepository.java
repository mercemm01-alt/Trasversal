package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.EmpleadoEntity;
import com.example.demo.Entity.JornadaEntity;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaEntity, Long>{
	
	// Empleado: jornada hoy
	List<JornadaEntity> findByEmpleadoAndFecha(EmpleadoEntity empleado, LocalDate fecha);
    
    // Cerrar jornada 
    Optional<JornadaEntity> findByEmpleadoAndFechaAndHoraSalidaIsNull(
    		EmpleadoEntity empleado, LocalDate fecha);
    
 // ADMIN: ver todas las jornadas
    @Query("""
        SELECT j FROM JornadaEntity j
        JOIN FETCH j.empleado
        ORDER BY j.fecha DESC
    """)
    List<JornadaEntity> findAllWithEmpleado();
    
    void deleteByEmpleado_IdEmpleado(Long idEmpleado);
    
}
