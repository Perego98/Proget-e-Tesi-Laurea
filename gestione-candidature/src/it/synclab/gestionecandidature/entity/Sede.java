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
	@Column(name="id_citt�")
	private int id_citt�;
	
	@Column(name="nomeCitt�")
	private String nomeCitt�;
	
	@Column(name="via")
	private String via;
	
	@Column(name="cap")
	private String cap;
	
	@Column(name="numeroCivico")
	private int numeroCivico;
	
	public Sede() {
		// TODO Auto-generated constructor stub
	}

	public int getId_citt�() {
		return id_citt�;
	}

	public void setId_citt�(int id_citt�) {
		this.id_citt� = id_citt�;
	}

	public String getNomeCitt�() {
		return nomeCitt�;
	}

	public void setNomeCitt�(String nomeCitt�) {
		this.nomeCitt� = nomeCitt�;
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
		return "Sede [id_citt�=" + id_citt� + ", nomeCitt�=" + nomeCitt� + ", via=" + via + ", cap=" + cap
				+ ", numeroCivico=" + numeroCivico + "]";
	}
	
	

}
