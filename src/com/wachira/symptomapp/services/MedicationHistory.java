package com.wachira.symptomapp.services;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the medication_history database table.
 * 
 */
public class MedicationHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer medicationHistoryId;
	private Timestamp timeTaken;
	private Checkin checkin;

	private Medication medication;

	private Patient patient;

	public MedicationHistory() {
	}

	public Integer getMedicationHistoryId() {
		return this.medicationHistoryId;
	}

	public void setMedicationHistoryId(Integer medicationHistoryId) {
		this.medicationHistoryId = medicationHistoryId;
	}

	public Timestamp getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(Timestamp timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Checkin getCheckin() {
		return this.checkin;
	}

	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}

	public Medication getMedication() {
		return this.medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}