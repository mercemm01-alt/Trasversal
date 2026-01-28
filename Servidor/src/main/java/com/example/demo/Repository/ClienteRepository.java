package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.ClienteEntity;

@Repository
public interface ClienteRepository  extends JpaRepository<ClienteEntity, Integer>{
	

}
