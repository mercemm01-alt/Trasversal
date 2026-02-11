package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.ProductoEntity;
import com.example.demo.Entity.Tipo;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
	
	List<ProductoEntity> findByTipo(Tipo categoria);
}
