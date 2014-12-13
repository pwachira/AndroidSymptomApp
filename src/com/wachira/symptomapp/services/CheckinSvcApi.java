package com.wachira.symptomapp.services;

import java.util.Collection;
import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


public interface CheckinSvcApi {
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";

	
	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String AUTHENTICATE_PATH = "/authenticate";

	public static final String PATIENT_MEDS_PATH =  "patient/getMedications";
	
	public static final String CHECKIN_PATH =  "/patient/checkin";
	public static final String PATIENTS_PATH = "/doctor/getPatients"  ;
	
	@GET(AUTHENTICATE_PATH)
	public OkResponse authenticateUser();
	
	@GET(PATIENT_MEDS_PATH)
	public List<PatientMedsDTO> getPatientMeds();
	
	@POST(CHECKIN_PATH)
	public OkResponse postCheckin(@Body CheckinDTO checkinDTO);
	
	@GET(PATIENTS_PATH)
	public List<Patient> getPatients();
	

}
