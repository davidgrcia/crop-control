package com.example.practica1.tratamiento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioTratamientos extends JpaRepository<Tratamiento, Long> {
	
	List<Tratamiento> findAllByOrderByCultivo_EspecieAsc();
	List<Tratamiento> findAllByOrderByFechareentradaAsc();
	List<Tratamiento> findAllByOrderByFecharecoleccionAsc();
	  
	List<Tratamiento> findAllByFechaBefore(LocalDate fechaVigor);
	  
	List<Tratamiento> findAllByFecharecoleccionAfter(LocalDate fechaVigor);
	  
}