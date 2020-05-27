package it.synclab.gestionecandidature.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.synclab.gestionecandidature.entity.Sede;

public class addSedi {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Sede.class)
									.buildSessionFactory();
		
		// create the session
		Session session = factory.getCurrentSession();
		
		try {
			// create an obj
			System.out.println("Creating a new Sede Obj");
			
			Sede sede1 = new Sede("Napoli", "Via G. Porzio", "80143", 8);
			Sede sede2 = new Sede("Roma", "Largo Carlo Salinari", "00142", 18);
			Sede sede3 = new Sede("Padova", "Galleria Spagna", "35127", 28);
			Sede sede4 = new Sede("Verona", "Via Albere", "37138", 19);
			Sede sede5 = new Sede("Milano", "Via Giovanni Durando", "20158 ", 38);
			
			// start a transaction
			session.beginTransaction();
			
			// save the obj
			System.out.println("Saving the sede...");
			session.save(sede1);
			session.save(sede2);
			session.save(sede3);
			session.save(sede4);
			session.save(sede5);

			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Sede Saved");

	}
	finally {
		
		session.close();
		
		factory.close();
	}


	}

}
