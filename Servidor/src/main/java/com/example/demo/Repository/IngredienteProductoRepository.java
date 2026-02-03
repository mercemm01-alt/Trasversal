package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.IngredienteProductoEntity;

@Repository
public interface IngredienteProductoRepository extends JpaRepository<IngredienteProductoEntity, Long>{

}
