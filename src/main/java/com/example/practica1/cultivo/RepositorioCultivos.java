package com.example.practica1.cultivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCultivos extends JpaRepository<Cultivo, Long> {

	List<Cultivo> findAllByOrderByEspecieAsc();
	
}