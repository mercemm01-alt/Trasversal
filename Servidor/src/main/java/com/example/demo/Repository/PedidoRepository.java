package com.example.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.PedidoEntity;
import com.example.demo.Entity.Estado;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

    List<PedidoEntity> findByEstadoOrderByFechaEntregaAsc(Estado estado);
}
