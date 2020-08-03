package com.tesi.gestione.bean;

import com.tesi.gestione.entity.Sede;

public class SedeBean {

	private Long id;

	private String nameCity;

	private String via;

	private String cap;

	private String civicNumber;

	private String printFormatted;

	private String printFormatted2;

	public SedeBean() {
	}

	public SedeBean(Sede theSede) {
		this.id = theSede.getId();
		this.nameCity = theSede.getNameCity();
		this.via = theSede.getVia();
		this.cap = theSede.getCap();
		this.civicNumber = theSede.getCivicNumber();
		this.printFormatted = format();
		this.printFormatted2 = format2();
	}

	private String format() {
		String temp = "";
		temp = "città: " + nameCity + ", via: " + via + ", numero civico: " + civicNumber;
		return temp;
	}

	private String format2() {
		String temp = "";
		temp = nameCity + ", " + via + " " + civicNumber + ", " + cap;
		return temp;
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

	public String getPrintFormatted() {
		return printFormatted;
	}

	public void setPrintFormatted(String printFormatted) {
		this.printFormatted = printFormatted;
	}

	public String getPrintFormatted2() {
		return printFormatted2;
	}

	public void setPrintFormatted2(String printFormatted2) {
		this.printFormatted2 = printFormatted2;
	}

	@Override
	public String toString() {
		return "città: " + nameCity + ", via: " + via + ",\n numero civico: " + civicNumber + ", cap: " + cap;
	}

}
