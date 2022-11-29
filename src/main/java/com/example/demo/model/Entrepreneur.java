package com.example.demo.model;

import javax.persistence.Lob;

public class Entrepreneur {
	private String entrepreneur;
	@Lob
	private String pitchTitle;
	@Lob
	private String pitchIdea;
	private float askAmount;
	private float equity;
	
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
}
