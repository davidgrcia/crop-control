package com.example.practica1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPrincipal {

	@GetMapping("/")
	public String inicio(Model model) {
		return "index";
	}

}
