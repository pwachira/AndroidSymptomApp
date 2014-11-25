package com.wachira.symptomapp.services;

import java.io.Serializable;

import java.util.List;


/**
 * The persistent class for the medication database table.
 * 
 */
public class Medication implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer medicationId;

	private String medicationName;

	private List<MedicationHistory> medicationHistories;

	public Medication() {
	}

	public Integer getMedicationId() {
		return this.medicationId;
	}

	public void setMedicationId(Integer medicationId) {
		this.medicationId = medicationId;
	}

	public String getMedicationName() {
		return this.medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public List<MedicationHistory> getMedicationHistories() {
		return this.medicationHistories;
	}

	public void setMedicationHistories(List<MedicationHistory> medicationHistories) {
		this.medicationHistories = medicationHistories;
	}

	public MedicationHistory addMedicationHistory(MedicationHistory medicationHistory) {
		getMedicationHistories().add(medicationHistory);
		medicationHistory.setMedication(this);

		return medicationHistory;
	}

	public MedicationHistory removeMedicationHistory(MedicationHistory medicationHistory) {
		getMedicationHistories().remove(medicationHistory);
		medicationHistory.setMedication(null);

		return medicationHistory;
	}

}