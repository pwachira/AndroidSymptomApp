package com.wachira.symptomapp.services;

public class PatientMedsDTO {

	private String medicationName;
	private Integer medicationId;
	private String patientUsername;
	private Integer patientId;
	public String getmedicationName() {
		return medicationName;
	}
	public void setmedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
	public Integer getmedicationId() {
		return medicationId;
	}
	public void setmedicationId(Integer medicationId) {
		this.medicationId = medicationId;
	}
	public String getPatientUsername() {
		return patientUsername;
	}
	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}
	public Integer getpatientId() {
		return patientId;
	}
	public void setpatientId(Integer patientId) {
		this.patientId = patientId;
	}

}
