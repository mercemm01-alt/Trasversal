package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Alergenos;
import com.example.demo.Entity.IngredienteEntity;

@Repository
public interface IngredienteRepository  extends JpaRepository<IngredienteEntity, Long>{
	List<IngredienteEntity> findByAlergenoIn(List<Alergenos> alergenos);
}
