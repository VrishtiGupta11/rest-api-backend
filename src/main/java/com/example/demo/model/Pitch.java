package com.example.demo.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pitch {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String entrepreneur;
	@Column(name = "pitchTitle", columnDefinition="text")
	private String pitchTitle;
	@Column(name = "pitchIdea", columnDefinition="text")
	private String pitchIdea;
	@Column(name = "askAmount")
	private float askAmount;
	private float equity;
	@ElementCollection
	@CollectionTable(name = "Offers", joinColumns = @JoinColumn(name = "id"))
	private List<Investor> offers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntrepreneur() {
		return entrepreneur;
	}
	public void setEntrepreneur(String entrepreneur) {
		this.entrepreneur = entrepreneur;
	}
	public String getPitchTitle() {
		return pitchTitle;
	}
	public void setPitchTitle(String pitchTitle) {
		this.pitchTitle = pitchTitle;
	}
	public String getPitchIdea() {
		return pitchIdea;
	}
	public void setPitchIdea(String pitchIdea) {
		this.pitchIdea = pitchIdea;
	}
	public float getAskAmount() {
		return askAmount;
	}
	public void setAskAmount(float askAmount) {
		this.askAmount = askAmount;
	}
	public float getEquity() {
		return equity;
	}
	public void setEquity(float equity) {
		this.equity = equity;
	}
	public List<Investor> getOffers() {
		return offers;
	}
	public void setOffers(List<Investor> offers) {
		this.offers = offers;
	}
	
	public Pitch(String entrepreneur, String pitchTitle, String pitchIdea, float askAmount, float equity) {
		super();
		this.entrepreneur = entrepreneur;
		this.pitchTitle = pitchTitle;
		this.pitchIdea = pitchIdea;
		this.askAmount = askAmount;
		this.equity = equity;
	}
}
