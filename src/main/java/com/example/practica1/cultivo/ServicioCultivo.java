package com.example.practica1.cultivo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



@Service
public class ServicioCultivo {

	private RepositorioCultivos repository;

	public ServicioCultivo(RepositorioCultivos repository){
		this.repository = repository;
	}

	public Optional<Cultivo> findOne(long id) {
		return repository.findById(id);
	}
	
	public Cultivo getOne(long id) {
		return repository.getOne(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Cultivo> findAll() {
		return repository.findAll();
	}

	public Cultivo save(Cultivo c) {
		Cultivo nuevoCultivo = repository.save(c);
		return nuevoCultivo;
	}
	
	public void saveAndFlush(Cultivo c) {
		repository.saveAndFlush(c);
	}
	

}
