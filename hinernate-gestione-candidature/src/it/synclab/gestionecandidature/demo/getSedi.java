package it.synclab.gestionecandidature.demo;

import java.util.List;

import it.synclab.gestionecandidature.dao.SedeDAO;
import it.synclab.gestionecandidature.dao.SedeDAONoFRM;
import it.synclab.gestionecandidature.entity.Sede;

public class getSedi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SedeDAONoFRM sedeDAO = new SedeDAONoFRM();
		
		List<Sede> sedi = sedeDAO.getSedi();
		
		for(Sede tempSede : sedi) {
			System.out.println("Sede" + tempSede.toString());
		}
		
	}

}
