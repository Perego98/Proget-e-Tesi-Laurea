package com.tesi.gestione.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	

}










