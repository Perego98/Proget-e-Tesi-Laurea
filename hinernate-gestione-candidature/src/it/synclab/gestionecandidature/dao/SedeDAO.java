package it.synclab.gestionecandidature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it.synclab.gestionecandidature.entity.Sede;


@Repository
public class SedeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Sede> getSedi() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Sede> theQuery = 
				currentSession.createQuery("from sede order by nomeCittà",
						Sede.class);
		
		// execute query and get result list
		List<Sede> sedi = theQuery.getResultList();
				
		// return the results		
		return sedi;
	}

}
