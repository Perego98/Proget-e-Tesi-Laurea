package com.tesi.gestione.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tesi.gestione.entity.Candidato;
import com.tesi.gestione.entity.Role;
import com.tesi.gestione.entity.Schedavalutazione;
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.UserService;

@Repository
public class SchedaDiValutazioneDaoImpl implements SchedaDiValutazioneDao {


	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Schedavalutazione schedaDiValutazione) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save schedaDiValutazione
		currentSession.saveOrUpdate(schedaDiValutazione);		
	}

	@Override
	public Schedavalutazione findByCodiceFiscaleAndUsername(String codFiscale, String userUsername) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
//		Query<SchedaDiValutazione> theQuery = currentSession.createQuery("from Schedavalutazione where utente_relatore=:userUsername and "
//				+ "candidato_relativo=:codFiscale", SchedaDiValutazione.class);
		
		Query<Schedavalutazione> theQuery = currentSession.createQuery("from Schedavalutazione where utente_relatore=:userUsername and "
				+ "candidato_relativo=:codFiscale", Schedavalutazione.class);
		
		theQuery.setParameter("codFiscale", codFiscale);
		theQuery.setParameter("userUsername", userUsername);
		
		Schedavalutazione theSchedaDiValutazione = null;
		
		try {
			theSchedaDiValutazione = theQuery.getSingleResult();
		} catch (Exception e) {
			theSchedaDiValutazione = null;
		}
		
		return theSchedaDiValutazione;
	}

	@Override
	@Transactional
	public List<Schedavalutazione> findByCodiceFiscale(String codFiscale) {
		System.out.println(" ********** RoleDaoImpl -> Entrato in getRoles().");
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Schedavalutazione> theQuery = 
				currentSession.createQuery("from Schedavalutazione where candidato_relativo=:codFiscale", Schedavalutazione.class);
		theQuery.setParameter("codFiscale", codFiscale);
		
		// execute query and get result list
		List<Schedavalutazione> schedeDiValutazione = theQuery.getResultList();
		
		
		// return the result
		return schedeDiValutazione;
	}

	@Override
	@Transactional
	public List<Schedavalutazione> findByUsername(String userUsername) {
		System.out.println(" ********** RoleDaoImpl -> Entrato in getRoles().");
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Schedavalutazione> theQuery = 
				currentSession.createQuery("from Schedavalutazione where utente_relatore=:userUsername", Schedavalutazione.class);
		theQuery.setParameter("userUsername", userUsername);
		
		// execute query and get result list
		List<Schedavalutazione> schedeDiValutazione = theQuery.getResultList();
		
		
		// return the result
		return schedeDiValutazione;
	}


	

}
