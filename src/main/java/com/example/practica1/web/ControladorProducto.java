package com.example.practica1.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practica1.producto.Producto;
import com.example.practica1.producto.ServicioProducto;

@Controller
public class ControladorProducto {

	@Autowired
	private ServicioProducto service;

	@GetMapping("/productos")
	public String mostrarProductos(Model model) {

		model.addAttribute("productos", service.findAll());

		return "productos.html";
	}

	@GetMapping("/producto/ver/{id}")
	public String mostrarProducto(Model model, @PathVariable long id) {

		Optional<Producto> aux = service.findOne(id);
		if (aux.isPresent()) {
			Producto p = aux.get();
			model.addAttribute("producto", p);
			return "producto";
		} else {
			return "productos";
		}

	}

	@GetMapping("/producto/nuevo")
	public String nuevoProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "nuevoProducto";
	}

	@PostMapping("/crearproducto")
	public String crearProducto(@ModelAttribute Producto p) {

		Producto nuevoProducto = service.save(p);

		return "redirect:/producto/ver/" + nuevoProducto.getId();
	}

	@PostMapping("/crearproductotratamiento")
	public String crearProductoTratamiento(@ModelAttribute Producto p) {

		service.save(p);

		return "redirect:/tratamiento/nuevo/";
	}

	@GetMapping("/producto/editar/{id}")
	public String editarProducto(Model model, @PathVariable long id) {

		Optional<Producto> aux = service.findOne(id);
		if (aux.isPresent()) {
			Producto p = aux.get();
			model.addAttribute("producto", p);
			return "editarProducto";
		} else {
			return "productos";
		}

	}

	@PostMapping("/editarproducto")
	public String procesoEditar(Producto p) {

		Producto nuevoProducto = service.save(p);

		return "redirect:/producto/ver/" + nuevoProducto.getId();
	}

}
