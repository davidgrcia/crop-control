package com.example.practica1.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practica1.cultivo.Cultivo;
import com.example.practica1.cultivo.RepositorioCultivos;
import com.example.practica1.cultivo.ServicioCultivo;

@Controller
public class ControladorCultivo {

	@Autowired
	private ServicioCultivo service;

	@Autowired
	private RepositorioCultivos repoCultivo;

	@GetMapping("/cultivos")
	public String mostrarCultivos(Model model) {

		model.addAttribute("cultivos", service.findAll());

		return "cultivos";
	}

	@GetMapping("/cultivo/ver/{id}")
	public String mostrarCultivo(Model model, @PathVariable long id) {

		Optional<Cultivo> aux = service.findOne(id);
		if (aux.isPresent()) {
			Cultivo c = aux.get();
			model.addAttribute("cultivo", c);
			return "cultivo";
		} else {
			return "cultivos";
		}

	}

	@GetMapping("/cultivo/nuevo")
	public String nuevoCultivo(Model model) {
		model.addAttribute("cultivo", new Cultivo());
		return "nuevocultivo";
	}

	@PostMapping("/crearcultivo")
	public String crearcultivo(Cultivo c) {

		Cultivo nuevocultivo = service.save(c);

		return "redirect:/cultivo/ver/" + nuevocultivo.getId();
	}

	@PostMapping("/crearcultivotratamiento")
	public String crearcultivotratamiento(Cultivo c) {

		service.save(c);

		return "redirect:/tratamiento/nuevo/";
	}

	@GetMapping("/cultivo/editar/{id}")
	public String editarCultivo(Model model, @PathVariable long id) {

		Optional<Cultivo> aux = service.findOne(id);
		if (aux.isPresent()) {
			Cultivo c = aux.get();
			model.addAttribute("cultivo", c);
			return "editarCultivo";
		} else {
			return "cultivos";
		}

	}

	@PostMapping("/editarcultivo")
	public String procesoEditar(Cultivo c) {
		
		

		Cultivo nuevoCultivo = service.save(c);

		return "redirect:/cultivo/ver/" + nuevoCultivo.getId();
	}

	@GetMapping("/ordenarcultivos")
	public String ordenarCultivos(Model model) {
		model.addAttribute("cultivos", repoCultivo.findAllByOrderByEspecieAsc());
		return "cultivos";
	}

}
