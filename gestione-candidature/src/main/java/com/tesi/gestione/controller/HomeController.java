package com.tesi.gestione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Controller
public class HomeController {

	 @Autowired
	 MessageSource messageSource;
	 
	@GetMapping("/")
	public String showHome() {
		
		return "home";
	}
	

}










