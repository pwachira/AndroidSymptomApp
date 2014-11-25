package com.wachira.symptomapp.services;

import java.io.Serializable;


import java.util.List;


/**
 * The persistent class for the doctor database table.
 * 
 */
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer doctorId;

	private String firstName;

	private String lastName;

	private String nationalProviderId;

	private String userName;

	private List<Alert> alerts;

	private List<Patient> patients;

	public Doctor() {
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationalProviderId() {
		return this.nationalProviderId;
	}

	public void setNationalProviderId(String nationalProviderId) {
		this.nationalProviderId = nationalProviderId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Alert> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Alert addAlert(Alert alert) {
		getAlerts().add(alert);
		alert.setDoctor(this);

		return alert;
	}

	public Alert removeAlert(Alert alert) {
		getAlerts().remove(alert);
		alert.setDoctor(null);

		return alert;
	}

	public List<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Patient addPatient(Patient patient) {
		getPatients().add(patient);
		patient.setDoctor(this);

		return patient;
	}

	public Patient removePatient(Patient patient) {
		getPatients().remove(patient);
		patient.setDoctor(null);

		return patient;
	}

}