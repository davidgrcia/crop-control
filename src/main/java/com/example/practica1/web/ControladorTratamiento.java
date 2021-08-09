package com.example.practica1.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practica1.cultivo.Cultivo;
import com.example.practica1.cultivo.ServicioCultivo;
import com.example.practica1.producto.Producto;
import com.example.practica1.producto.ServicioProducto;
import com.example.practica1.tratamiento.RepositorioTratamientos;
import com.example.practica1.tratamiento.ServicioTratamiento;
import com.example.practica1.tratamiento.Tratamiento;

@Controller
public class ControladorTratamiento {

	@Autowired
	private ServicioTratamiento service;

	@Autowired
	private ServicioCultivo servicioCultivo;

	@Autowired
	private RepositorioTratamientos repoTratamiento;

	@Autowired
	private ServicioProducto servicioProducto;

	@GetMapping("/tratamientos")
	public String mostrarTratamientos(Model model) {

		model.addAttribute("tratamientos", service.findAll());

		return "tratamientos";
	}

	@GetMapping("/tratamiento/ver/{id}")
	public String mostrarTratamiento(Model model, @PathVariable long id) {

		Optional<Tratamiento> aux = service.findOne(id);
		if (aux.isPresent()) {
			Tratamiento t = aux.get();
			model.addAttribute("tratamiento", t);
			return "tratamiento";
		} else {
			return "tratamientos";
		}
	}

	@GetMapping("/tratamiento/nuevo")
	public String nuevoTratamiento(Model model) {
		model.addAttribute("cultivos", servicioCultivo.findAll());
		model.addAttribute("productos", servicioProducto.findAll());

		model.addAttribute("producto", new Producto());
		model.addAttribute("cultivo", new Cultivo());

		return "nuevoTratamiento";

	}

	@RequestMapping(value = "/creartratamiento", method = RequestMethod.POST)
	public String crearTratamiento(Model model, @RequestParam(value = "cultivo", required = true) long cultivo,
			@RequestParam(value = "producto", required = true) long producto,
			@RequestParam(value = "numlote", required = true) String numlote,
			@RequestParam(value = "fecha", required = true) LocalDate fecha) {

		Cultivo c = servicioCultivo.getOne(cultivo);

		Producto p = servicioProducto.getOne(producto);

		Tratamiento t = new Tratamiento(c, p, numlote, fecha);

		if (t.getProducto().getPlazo_reentrada() != 0) {
			t.setFecha_reentrada(fecha.plusDays(t.getProducto().getPlazo_reentrada()));
		}
		if (t.getProducto().getPlazo_recoleccion() != 0) {
			t.setFecha_recoleccion(fecha.plusDays(t.getProducto().getPlazo_recoleccion()));
		}
		Cultivo cultivoAux = servicioCultivo.getOne(cultivo);

		List<Tratamiento> lista = cultivoAux.getTratamientos();

		lista.add(t);

		cultivoAux.setTratamientos(lista);

		Tratamiento nuevoTratamiento = service.save(t);

		return "redirect:/tratamiento/ver/" + nuevoTratamiento.getId();
	}

	@GetMapping("/tratamiento/editar/{id}")
	public String editarTratamiento(Model model, @PathVariable long id) {

		model.addAttribute("cultivos", servicioCultivo.findAll());
		model.addAttribute("productos", servicioProducto.findAll());

		Optional<Tratamiento> aux = service.findOne(id);
		if (aux.isPresent()) {
			Tratamiento t = aux.get();

			model.addAttribute("tratamiento", t);

			return "editarTratamiento";
		} else {
			return "tratamientos";
		}

	}

	@PostMapping("/editartratamiento")
	public String procesoEditar(Tratamiento t) {

		if (t.getProducto().getPlazo_reentrada() != 0) {
			t.setFecha_reentrada(t.getFecha().plusDays(t.getProducto().getPlazo_reentrada()));
		}
		if (t.getProducto().getPlazo_recoleccion() != 0) {
			t.setFecha_recoleccion(t.getFecha().plusDays(t.getProducto().getPlazo_recoleccion()));
		}

		List<Cultivo> cultivoBorrar = servicioCultivo.findAll();

		for (Cultivo cultivo : cultivoBorrar) {
			List<Tratamiento> tratamientos = cultivo.getTratamientos();
			if (!tratamientos.isEmpty()) {
				for (Tratamiento tt : tratamientos) {
					if (tt.getId() == t.getId()) {
						cultivo.getTratamientos().remove(tt);
						servicioCultivo.save(cultivo);
						tratamientos.remove(tt);
						break;
					}
				}
			}

		}

		Cultivo cultivoAux = servicioCultivo.getOne(t.getCultivo().getId());

		List<Tratamiento> lista = cultivoAux.getTratamientos();

		lista.add(t);

		cultivoAux.setTratamientos(lista);

		Tratamiento nuevoTratamiento = service.save(t);

		return "redirect:/tratamiento/ver/" + nuevoTratamiento.getId();
	}

	@RequestMapping(value = "/fechavigor", method = RequestMethod.POST)
	public String mostrarTratamientosPorFechaVigor(Model model,
			@RequestParam(value = "fechavigor", required = true) LocalDate fechavigor) {

		List<Tratamiento> lista = repoTratamiento.findAllByFecharecoleccionAfter(fechavigor);
		List<Tratamiento> lista2 = repoTratamiento.findAllByFechaBefore(fechavigor);

		lista.retainAll(lista2);

		model.addAttribute("tratamientos", lista);

		return "tratamientos";
	}

	@GetMapping("/tratamientosPorEspecie")
	public String mostrarTratamientosPorEspecie(Model model) {

		model.addAttribute("tratamientos", repoTratamiento.findAllByOrderByCultivo_EspecieAsc());

		return "tratamientos";
	}

	@GetMapping("/tratamientosPorFechaReentrada")
	public String mostrarTratamientosPorFechaReentrada(Model model) {

		model.addAttribute("tratamientos", repoTratamiento.findAllByOrderByFechareentradaAsc());

		return "tratamientos";
	}

	@GetMapping("/tratamientosPorFechaRecoleccion")
	public String mostrarTratamientosPorFechaRecoleccion(Model model) {

		model.addAttribute("tratamientos", repoTratamiento.findAllByOrderByFecharecoleccionAsc());

		return "tratamientos";
	}

	@GetMapping("/tratamientosenvigor")
	public String mostrarTratamientosEnVigor(Model model) {

		model.addAttribute("tratamientos", service.findAll());

		return "tratamientosenvigor";
	}

	@RequestMapping(value = "/fechavigorordenada", method = RequestMethod.POST)
	public String mostrarTratamientosPorFechaVigorOrdenada(Model model,
			@RequestParam(value = "fechavigor", required = true) LocalDate fechavigor,
			@RequestParam(value = "option", required = true) int opcion) {

		List<Tratamiento> lista = repoTratamiento.findAllByFecharecoleccionAfter(fechavigor);
		List<Tratamiento> lista2 = repoTratamiento.findAllByFechaBefore(fechavigor);

		List<Tratamiento> listaAux = new ArrayList<>();

		lista.retainAll(lista2);

		if (opcion == 2) {
			listaAux = repoTratamiento.findAllByOrderByCultivo_EspecieAsc();
			listaAux.retainAll(lista);
			model.addAttribute("tratamientos", listaAux);
			return "tratamientosenvigor";
		}

		if (opcion == 3) {
			listaAux = repoTratamiento.findAllByOrderByFechareentradaAsc();
			listaAux.retainAll(lista);
			model.addAttribute("tratamientos", listaAux);
			return "tratamientosenvigor";
		}

		if (opcion == 4) {
			listaAux = repoTratamiento.findAllByOrderByFecharecoleccionAsc();
			listaAux.retainAll(lista);
			model.addAttribute("tratamientos", listaAux);
			return "tratamientosenvigor";
		}

		model.addAttribute("tratamientos", lista);

		return "tratamientosenvigor";

	}

}
