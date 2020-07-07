package com.tesi.gestione.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Candidato;

@Repository
public class CandidatoDaoImpl implements CandidatoDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Candidato findCandidatoByCF(String codFiscale) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using CF
		Query<Candidato> theQuery = currentSession.createQuery("from Candidato where codiceFiscale=:codFiscale", Candidato.class);
		theQuery.setParameter("codFiscale", codFiscale);
		
		Candidato theCandidato = null;
		
		try {
			theCandidato = theQuery.getSingleResult();
		} catch (Exception e) {
			theCandidato = null;
		}
		
		return theCandidato;
	}

	@Override
	@Transactional
	public List<Candidato> getCandidati() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Candidato> theQuery = 
				currentSession.createQuery("from Candidato", Candidato.class);
		
		// execute query and get result list
		List<Candidato> candidati = theQuery.getResultList();
		
		
		// return the result
		return candidati;
	}

	@Override
	public void save(Candidato candidato) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the candidato 
		currentSession.saveOrUpdate(candidato);

	}

}
