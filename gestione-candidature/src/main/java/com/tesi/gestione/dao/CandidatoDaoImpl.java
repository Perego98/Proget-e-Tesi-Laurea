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
import com.tesi.gestione.entity.User;
import com.tesi.gestione.service.UserService;

@Repository
public class CandidatoDaoImpl implements CandidatoDao {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserService userService;
	
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

	@Override
	public void deleteCandidato(String codFiscale) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the obj with PK
		Query theQuery = currentSession.createQuery("delete from Candidato where codiceFiscale=:theCodFiscale");
		
		theQuery.setParameter("theCodFiscale", codFiscale);
		
		theQuery.executeUpdate();
	}

	@Override
	@Transactional
	public Blob dowloadCurriculum(String codFiscale) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using CF
		Query<Blob> theQuery = currentSession.createQuery("select curriculum from Candidato where codiceFiscale=:codFiscale",
				Blob.class);
		theQuery.setParameter("codFiscale", codFiscale);

		Blob theCandidato = null;

		try {
			theCandidato = theQuery.getSingleResult();
		} catch (Exception e) {
			theCandidato = null;
		}
		
//		long lunghezza = 0;
//		try {
//			lunghezza = theCandidato.length();
//			System.out.println("Lunghezza: " + lunghezza);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(lunghezza == 0)
//			return null;

		
		return theCandidato;
		
	}

	@Override
	@Transactional
	public void triggerActionOnDeleteUser(String username) {
		
		// devo trovare tutti i candidati associati all'utente con username username
		// devo fare un update del campo stato_candidatura, in new
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the obj with PK
		Query theQuery = currentSession.createQuery("update Candidato set stato_candidatura='new' where "
				+ "supervisore=:theUser and (stato_candidatura='assegnato_hr' or stato_candidatura='assegnato_manager')");
		
		User theUser = userService.findByUserName(username); 
		theQuery.setParameter("theUser", theUser);
		
		theQuery.executeUpdate();
		
	}
	
	@Override
	public List<Candidato> search(String theSearchName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;

		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery("from Candidato where lower(codiceFiscale) "
					+ "like :theName or lower(nome) like :theName " + "or lower(cognome) like :theName",
					Candidato.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all users
			theQuery = currentSession.createQuery("from Candidato", Candidato.class);
		}

		// execute query and get result list
		List<Candidato> candidati = theQuery.getResultList();

		// return the results
		return candidati;

	}

	@Override
	public boolean CVpresente(String codiceFiscale) {
		
		Blob tempCur = dowloadCurriculum(codiceFiscale);
		
		if(tempCur != null)
			return true;
		
		return false;
	}

	@Override
	public int getPeriodoPreavviso(String codiceFiscale) {
		return findCandidatoByCF(codiceFiscale).getPreavviso();
	}

	@Override
	@Transactional
	public List<Candidato> getCandidatiAssociati(String userUsername) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Candidato> theQuery = 
				currentSession.createQuery("from Candidato where supervisore=:theSupervisore", Candidato.class);
		User theUser = userService.findByUserName(userUsername);
		theQuery.setParameter("theSupervisore", theUser);

		// execute query and get result list
		List<Candidato> candidati = theQuery.getResultList();
		
		
		// return the result
		return candidati;
	}

	@Override
	@Transactional
	public long getCandidatiStatus(String stato) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using CF
		Query<Long> theQuery = currentSession.createQuery("SELECT COUNT(*) FROM Candidato where stato_candidatura=:stato", Long.class);
		theQuery.setParameter("stato", stato);
		
		long numCandidati = 0l;
		
		try {
			numCandidati = theQuery.getSingleResult();
		} catch (Exception e) {
			numCandidati = 0l;
		}
		
		return numCandidati;
	}

	

}
