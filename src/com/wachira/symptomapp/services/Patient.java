package com.wachira.symptomapp.services;

import java.io.Serializable;


import java.util.List;


public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer patientId;
	private String firstName;

	private String lastName;
	private String userName;


	private List<Checkin> checkins;

	private List<MedicationHistory> medicationHistories;

	private Doctor doctor;

	public Patient() {
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}






	public List<Checkin> getCheckins() {
		return this.checkins;
	}

	public void setCheckins(List<Checkin> checkins) {
		this.checkins = checkins;
	}

	public Checkin addCheckin(Checkin checkin) {
		getCheckins().add(checkin);
		checkin.setPatient(this);

		return checkin;
	}

	public Checkin removeCheckin(Checkin checkin) {
		getCheckins().remove(checkin);
		checkin.setPatient(null);

		return checkin;
	}

	public List<MedicationHistory> getMedicationHistories() {
		return this.medicationHistories;
	}

	public void setMedicationHistories(List<MedicationHistory> medicationHistories) {
		this.medicationHistories = medicationHistories;
	}

	public MedicationHistory addMedicationHistory(MedicationHistory medicationHistory) {
		getMedicationHistories().add(medicationHistory);
		medicationHistory.setPatient(this);

		return medicationHistory;
	}

	public MedicationHistory removeMedicationHistory(MedicationHistory medicationHistory) {
		getMedicationHistories().remove(medicationHistory);
		medicationHistory.setPatient(null);

		return medicationHistory;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}