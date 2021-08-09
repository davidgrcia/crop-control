package com.example.practica1.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ServicioProducto {

	private RepositorioProductos repository;

	public ServicioProducto(RepositorioProductos repository){
		this.repository = repository;
	}

	public Optional<Producto> findOne(long id) {
		return repository.findById(id);
	}
	
	public Producto getOne(long id) {
		return repository.getOne(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}

	public List<Producto> findAll() {
		return repository.findAll();
	}

	public Producto save(Producto p) {
		Producto nuevoProducto = repository.save(p);
		return nuevoProducto;
	}
	

}
