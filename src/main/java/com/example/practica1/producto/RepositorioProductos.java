package com.example.practica1.producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProductos extends JpaRepository<Producto, Long> {

}