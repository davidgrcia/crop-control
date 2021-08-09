package com.example.practica1.tratamiento;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.practica1.cultivo.Cultivo;
import com.example.practica1.producto.Producto;

@Entity
public class Tratamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@ManyToOne
	private Cultivo cultivo;
	@ManyToOne
	private Producto producto;

	private String numlote;
	private LocalDate fecha;
	private LocalDate fechareentrada;
	private LocalDate fecharecoleccion;

	public Tratamiento() {
	}

	public Tratamiento(Cultivo cultivo, Producto producto, String numlote, LocalDate fecha) {
		super();
		this.cultivo = cultivo;
		this.producto = producto;
		this.numlote = numlote;
		this.fecha = fecha;
		if (producto.getPlazo_reentrada() != 0) {
			this.fechareentrada = fecha.plusDays(producto.getPlazo_reentrada());
		}
		if (producto.getPlazo_recoleccion() != 0) {
			this.fecharecoleccion = fecha.plusDays(producto.getPlazo_recoleccion());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNumlote() {
		return numlote;
	}

	public void setNumlote(String numlote) {
		this.numlote = numlote;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalDate getFecha_reentrada() {
		return fechareentrada;
	}

	public void setFecha_reentrada(LocalDate fecha_reentrada) {
		this.fechareentrada = fecha_reentrada;
	}

	public LocalDate getFecha_recoleccion() {
		return fecharecoleccion;
	}

	public void setFecha_recoleccion(LocalDate fecha_recoleccion) {
		this.fecharecoleccion = fecha_recoleccion;
	}

	@Override
	public String toString() {
		return "nº" + numlote;
	}

}
