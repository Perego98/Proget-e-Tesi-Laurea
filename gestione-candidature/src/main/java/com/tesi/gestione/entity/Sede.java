package com.tesi.gestione.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sede")
public class Sede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name_city")
	private String nameCity;
	
	@Column(name = "via")
	private String via;
	
	@Column(name = "cap")
	private String cap;
	
	@Column(name = "civic_number")
	private String civicNumber;
	
	@OneToOne(mappedBy="sedeAssegnamento", cascade=CascadeType.ALL)
	private User user;

	public Sede() {
	}

	public Sede(String nameCity, String via, String cap, String civicNumber) {
		super();
		this.nameCity = nameCity;
		this.via = via;
		this.cap = cap;
		this.civicNumber = civicNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCivicNumber() {
		return civicNumber;
	}

	public void setCivicNumber(String civicNumber) {
		this.civicNumber = civicNumber;
	}

	@Override
	public String toString() {
		return "id(" + id + ") città=" + nameCity + ", via=" + via + ", numero civico="
				+ civicNumber + ", cap=" + cap ;
	}

	
}
