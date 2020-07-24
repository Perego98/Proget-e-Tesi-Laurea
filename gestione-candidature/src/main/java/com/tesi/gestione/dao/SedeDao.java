package com.tesi.gestione.dao;

import java.util.List;

import com.tesi.gestione.entity.Sede;

public interface SedeDao {

	public Sede findSedeByCityID(long theCitySedeID);

	public List<Sede> getSedi();

	public Sede findSedeByCityID(String idSedePreferita);

}
