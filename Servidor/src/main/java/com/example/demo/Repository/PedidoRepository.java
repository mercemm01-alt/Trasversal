package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.Estado;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

	Optional<PedidoEntity> findByClienteIdClienteAndEstado(Long idCliente, Estado estado);
	
	@Query("""
	        SELECT p FROM PedidoEntity p
	        WHERE MONTH(p.fechaEntrega) = MONTH(CURRENT_DATE)
	        AND YEAR(p.fechaEntrega) = YEAR(CURRENT_DATE)
	        ORDER BY p.fechaEntrega ASC
	    """)
	    List<PedidoEntity> findPedidosDelMes();;

}
