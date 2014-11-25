package com.wachira.symptomapp.services;

import java.io.Serializable;

/**
 * The persistent class for the alert database table.
 * 
 */
public class Alert implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer alertId;

	private String alertDescription;

	private Doctor doctor;

	private Patient patient;

	public Alert() {
	}

	public Integer getAlertId() {
		return this.alertId;
	}

	public void setAlertId(Integer alertId) {
		this.alertId = alertId;
	}

	public String getAlertDescription() {
		return this.alertDescription;
	}

	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}