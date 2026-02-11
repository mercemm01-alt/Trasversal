package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.PedidoProductoEntity;

public interface PedidoProductoRepository extends JpaRepository<PedidoProductoEntity, Long>{
	 
}
