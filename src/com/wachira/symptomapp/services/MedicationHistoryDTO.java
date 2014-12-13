package com.wachira.symptomapp.services;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the medication_history database table.
 * 
 */
public class MedicationHistoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer medicationHistoryId;
	private Timestamp timeTaken;
	private CheckinDTO checkin;

	private PatientMedsDTO medication;

	private Patient patient;

	public MedicationHistoryDTO() {
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

	public CheckinDTO getCheckin() {
		return this.checkin;
	}

	public void setCheckin(CheckinDTO checkin) {
		this.checkin = checkin;
	}

	public PatientMedsDTO getMedication() {
		return this.medication;
	}

	public void setMedication(PatientMedsDTO medication) {
		this.medication = medication;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}