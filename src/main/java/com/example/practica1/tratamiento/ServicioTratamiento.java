package com.example.practica1.tratamiento;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


@Service
public class ServicioTratamiento {

	private RepositorioTratamientos repository;

	public ServicioTratamiento(RepositorioTratamientos repository){
		this.repository = repository;
	}

	public Optional<Tratamiento> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Tratamiento> findAll() {
		return repository.findAll();
	}

	public Tratamiento save(Tratamiento t) {
		Tratamiento nuevoTratamiento = repository.save(t);
		return nuevoTratamiento;
	}
	

}
