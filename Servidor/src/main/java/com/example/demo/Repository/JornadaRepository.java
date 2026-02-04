package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.JornadaEntity;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaEntity, Long>{

}
