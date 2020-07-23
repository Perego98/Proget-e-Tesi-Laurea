package com.tesi.gestione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.tesi.gestione.service.CandidatoService;

@Controller
public class HomeController {


    @Autowired
    private CandidatoService candidatoService;
	
	@GetMapping("/")
	public String showHome(Model theModel) {
		
		
		int cn, chr, cm, cv, ca, cr;
		// devo recuperare i valori
		cn= (int) candidatoService.getCandidatiStatus("new");
		chr= (int) candidatoService.getCandidatiStatus("assegnato_hr");
		cm= (int) candidatoService.getCandidatiStatus("assegnato_manager");
		cv= (int) candidatoService.getCandidatiStatus("in_valutazione");
		ca= (int) candidatoService.getCandidatiStatus("assunto");
		cr= (int) candidatoService.getCandidatiStatus("rigettato");
		
		
		String canvas = "	<script>\n" + 
				"	// Bar chart\n" + 
				"	new Chart(document.getElementById(\"bar-chart\"), {\n" + 
				"	    type: 'horizontalBar',\n" + 
				"	    data: {\n" + 
				"	      labels: [\"New\", \"Assegnati all'hr\", \"Assegnati al manager\", \"In valutazione\", \"Assunti\", \"Rigettati\"],\n" + 
				"	      datasets: [\n" + 
				"	        {\n" + 
				"	          label: \"Numero\",\n" + 
				"	          backgroundColor: [\"#3e95cd\", \"#8e5ea2\",\"#3cba9f\",\"#e8c3b9\",\"#c45850\",\"#808000\"],\n" + 
				"	          //data: [${valori}]\n" + 
				"	          data: ["+ cn +","+chr+","+cm+","+cv+","+ca+","+cr+", 0]\n" + 
				"	        }\n" + 
				"	      ]\n" + 
				"	    },\n" + 
				"	    options: {\n" + 
				"	      legend: { display: false },\n" + 
				"	      title: {\n" + 
				"	        display: true,\n" + 
				"	        text: 'Grafico andamento delle candidature'\n" + 
				"	      }\n" + 
				"	    }\n" + 
				"	});\n" + 
				"	\n" + 
				"	</script>";
		
		
		
		theModel.addAttribute("valori", canvas);
		
		
		return "home";
	}
	

}










