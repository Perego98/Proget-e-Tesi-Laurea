package it.synclab.gestionecandidature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sede")
public class Sede {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_città")
	private int id_città;
	
	@Column(name="nomeCittà")
	private String nomeCittà;
	
	@Column(name="via")
	private String via;
	
	@Column(name="cap")
	private String cap;
	
	@Column(name="numeroCivico")
	private int numeroCivico;
	
	public Sede() {
		// TODO Auto-generated constructor stub
	}

	public int getId_città() {
		return id_città;
	}

	public void setId_città(int id_città) {
		this.id_città = id_città;
	}

	public String getNomeCittà() {
		return nomeCittà;
	}

	public void setNomeCittà(String nomeCittà) {
		this.nomeCittà = nomeCittà;
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

	public int getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	@Override
	public String toString() {
		return "Sede [id_città=" + id_città + ", nomeCittà=" + nomeCittà + ", via=" + via + ", cap=" + cap
				+ ", numeroCivico=" + numeroCivico + "]";
	}
	
	

}
