package com.example.practica1.producto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String nombre;
	private String descripcion;
	private int plazo_reentrada;
	private int plazo_recoleccion;

	public Producto() {}

	public Producto(String nombre, String descripcion, int plazo_reentrada, int plazo_recoleccion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plazo_reentrada = plazo_reentrada;
		this.plazo_recoleccion = plazo_recoleccion;
	}
	
	public Producto(String nombre, String descripcion, int plazo_recoleccion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.plazo_recoleccion = plazo_recoleccion;
	}
	
	public Producto(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPlazo_reentrada() {
		return plazo_reentrada;
	}

	public void setPlazo_reentrada(int plazo_reentrada) {
		this.plazo_reentrada = plazo_reentrada;
	}

	public int getPlazo_recoleccion() {
		return plazo_recoleccion;
	}

	public void setPlazo_recoleccion(int plazo_recoleccion) {
		this.plazo_recoleccion = plazo_recoleccion;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", plazo_reentrada=" + plazo_reentrada
				+ ", recoleccion=" + plazo_recoleccion + "]";
	}


}
