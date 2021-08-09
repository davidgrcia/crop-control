package com.example.practica1.cultivo;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.practica1.tratamiento.Tratamiento;

@Entity
public class Cultivo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String especie;
	
	private String variedad;
	private LocalDate fecha_plantado;
	private String zona;
	
	@OneToMany
	private List<Tratamiento> tratamientos;

	public Cultivo(){}
	
	public Cultivo(String especie, String variedad, LocalDate fecha_plantado, String zona
			) {
		super();
		this.especie = especie;
		this.variedad = variedad;
		this.fecha_plantado = fecha_plantado;
		this.zona = zona;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

	public LocalDate getFecha_plantado() {
		return fecha_plantado;
	}

	public void setFecha_plantado(LocalDate fecha_plantado) {
		this.fecha_plantado = fecha_plantado;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public List<Tratamiento> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

	@Override
	public String toString() {
		return "Cultivo [id=" + id + ", especie=" + especie + "]";
	}
	
	
	
}
