package com.tesi.gestione.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Sede;

@Repository
public class SedeDaoImpl implements SedeDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Sede findSedeByCityID(long theCitySedeID) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Sede> theQuery = currentSession.createQuery("from Sede where id=:cityID", Sede.class);
		theQuery.setParameter("cityID", theCitySedeID);
		
		Sede theSede = null;
		
		try {
			theSede = theQuery.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			theSede = null;
		}
		
		return theSede;
	}

	@Override
	@Transactional
	public List<Sede> getSedi() {
		
		System.out.println(" ********** SedeDaoImpl -> Entrato in getSedi().");
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Sede> theQuery = 
				currentSession.createQuery("from Sede", Sede.class);
		
		// execute query and get result list
		List<Sede> sedi = theQuery.getResultList();
		
		System.out.println(" ********** SedeDaoImpl -> Sedi: " + sedi.toString());
		
		// return the result
		return sedi;
	}

	@Override
	public Sede findSedeByCityID(String idSedePreferita) {
		Long tempLong = Long.parseLong(idSedePreferita);
		
		Sede theSede = findSedeByCityID(tempLong);
		
		return theSede;
	}




}
