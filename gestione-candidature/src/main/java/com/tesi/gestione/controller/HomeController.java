package com.tesi.gestione.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tesi.gestione.bean.UserBean;
import com.tesi.gestione.service.CandidatoService;
import com.tesi.gestione.service.UserService;

@Controller
public class HomeController {


    @Autowired
    private CandidatoService candidatoService;
    
    @Autowired
    private UserService userService;
	
	@GetMapping("/")
	public String showHome(Model theModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		UserBean theUser = new UserBean(userService.findByUserName(currentPrincipalName));
		
		int cn, chr, cm, cv, ca, cr;
		// devo recuperare i valori
		cn= (int) candidatoService.getCandidatiStatus("new");
		chr= (int) candidatoService.getCandidatiStatus("assegnato_hr");
		cm= (int) candidatoService.getCandidatiStatus("assegnato_manager");
		cv= (int) candidatoService.getCandidatiStatus("in_valutazione");
		ca= (int) candidatoService.getCandidatiStatus("assunto");
		cr= (int) candidatoService.getCandidatiStatus("rigettato");
		
		
		String canvas = 
				"	<script>\n" + 
				"	// Bar chart\n" + 
				"	new Chart(document.getElementById(\"bar-chart\"), {\n" + 
				"	    type: 'horizontalBar',\n" + 
				"	    data: {\n" + 
				"	      labels: [\"New\", \"Assegnati all'hr\", \"Assegnati al manager\", \"In valutazione\", \"Assunti\", \"Rigettati\"],\n" + 
				"	      datasets: [\n" + 
				"	        {\n" + 
				"	          label: \"Numero\",\n" + 
				"	          backgroundColor: [\"#3e95cd\", \"#8e5ea2\",\"#54aedb\",\"#e8c3b9\",\"#3cba9f\",\"#c45850\"],\n" + 
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
		
		
		int candidatiTotali = candidatoService.totCandidati();
		int candidatiCollegati=candidatoService.totCandidatiCollegati(currentPrincipalName);

		
		String canvashr = 
				"	<script>\n" + 
				"	// Bar chart\n" + 
				"	new Chart(document.getElementById(\"grafico-candidati-assegnati-hr\"), {\n" + 
				"	    type: 'doughnut',\n" + 
				"	    data: {\n" + 
				"	      labels: [\"Candidati Totali\", \"Assegnati a "+currentPrincipalName+"\"],\n" + 
				"	      datasets: [\n" + 
				"	        {\n" + 
				"	          label: \"Numero\",\n" + 
				"	          backgroundColor: [\"#3e95cd\", \"#8e5ea2\"],\n" + 
				"	          //data: [${valori}]\n" + 
				"	          data: ["+ candidatiTotali +","+candidatiCollegati+"]\n" + 
				"	        }\n" + 
				"	      ]\n" + 
				"	    },\n" + 
				"	    options: {\n" + 
				"	      legend: { display: false },\n" + 
				"	      title: {\n" + 
				"	        display: true,\n" + 
				"	        text: 'Grafico andamento delle candidature assegnate al HR rispetto alle candidature totali'\n" + 
				"	      }\n" + 
				"	    }\n" + 
				"	});\n" + 
				"	\n" + 
				"	</script>";
		
		
		String canvasmanager = 
				"	<script>\n" + 
				"	// Bar chart\n" + 
				"	new Chart(document.getElementById(\"grafico-candidati-assegnati-manager\"), {\n" + 
				"	    type: 'doughnut',\n" + 
				"	    data: {\n" + 
				"	      labels: [\"Candidati Totali\", \"Assegnati a "+currentPrincipalName+"\"],\n" + 
				"	      datasets: [\n" + 
				"	        {\n" + 
				"	          label: \"Numero\",\n" + 
				"	          backgroundColor: [\"#3e95cd\", \"#8e5ea2\"],\n" + 
				"	          //data: [${valori}]\n" + 
				"	          data: ["+ candidatiTotali +","+candidatiCollegati+"]\n" + 
				"	        }\n" + 
				"	      ]\n" + 
				"	    },\n" + 
				"	    options: {\n" + 
				"	      legend: { display: false },\n" + 
				"	      title: {\n" + 
				"	        display: true,\n" + 
				"	        text: 'Grafico andamento delle candidature assegnate al Manager rispetto alle candidature totali'\n" + 
				"	      }\n" + 
				"	    }\n" + 
				"	});\n" + 
				"	\n" + 
				"	</script>";
		
		
		theModel.addAttribute("theUser", theUser);
		theModel.addAttribute("valori", canvas);
		theModel.addAttribute("valorihr", canvashr);
		theModel.addAttribute("valorimanager", canvasmanager);
		
		
		return "home";
	}
	

}










