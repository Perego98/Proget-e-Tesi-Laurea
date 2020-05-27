package it.synclab.gestionecandidature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.synclab.gestionecandidature.entity.Sede;


public class SedeDAONoFRM {

	
	private SessionFactory sessionFactory;
	
	public SedeDAONoFRM() {
		
		
	}
	
	public List<Sede> getSedi() {
		// create session factory
		sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Sede.class)
				.buildSessionFactory();
		
		// create the session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// result
		List<Sede> sedi = null;
		
			
		try {
			// start a transaction
			currentSession.beginTransaction();
					
			// create a query  ... sort by last name
			Query<Sede> theQuery = 
					currentSession.createQuery("from sede order by nomeCittà",
							Sede.class);
			
			// execute query and get result list
			sedi = theQuery.getResultList();
			
			// commit transaction
			currentSession.getTransaction().commit();


			}
			finally {
				currentSession.close();
				sessionFactory.close();
			}
		
		
		// return the results		
		return sedi;
	}

}
