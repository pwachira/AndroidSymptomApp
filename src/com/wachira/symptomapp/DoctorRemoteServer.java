package com.wachira.symptomapp;

import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.wachira.symptomapp.db.DbOpenHelper;
import com.wachira.symptomapp.services.CheckinDTO;
import com.wachira.symptomapp.services.CheckinSvc;
import com.wachira.symptomapp.services.CheckinSvcApi;
import com.wachira.symptomapp.services.OkResponse;
import com.wachira.symptomapp.services.Patient;
import com.wachira.symptomapp.services.PatientMedsDTO;

public class DoctorRemoteServer {
	private static final String TAG = "RemoteServer";
	private static final String ChekinSvcUrl = "http://ec2-54-196-113-30.compute-1.amazonaws.com:8080/";
	private Context context;
	private Class<Activity> activity;
	private String user;
	private String password;
	private CheckinSvcApi svc;
	private SharedPreferences prefs;
	private DbOpenHelper mDbHelper;

	
	public DoctorRemoteServer(Context context, final Class activity , String user, String pass){
		this.context = context;
		this.activity = activity;
		this.user = user;
		this.password = pass;
		this.svc = CheckinSvc.init(ChekinSvcUrl, user, password,context.getApplicationContext());
		prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
		mDbHelper = new DbOpenHelper(context);
	}
	
	public void performPostLoginActivity( ){
		//final CheckinSvcApi svc = CheckinSvc.init(ChekinSvcUrl, user, password,context.getApplicationContext());

		CallableTask.invoke(new Callable<OkResponse>() {

			@Override
			public OkResponse call() throws Exception {
				return svc.authenticateUser();
			}
		}, new TaskCallback<OkResponse>() {

			@Override
			public void success(OkResponse result) {
				
				context.startActivity(new Intent(
						context,
						activity));
			}
			
			@Override
			public void error(Exception e) {
				Log.e(LoginActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						context,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
	
		boolean pt_fetched = prefs.getBoolean(context.getString(R.string.patients_fetched_key), false);
		if(!pt_fetched){
			getPatientList();
		}
		
	}
	
	public void getPatientList(){
	CallableTask.invoke(new Callable<List<Patient>>() {

		@Override
		public List<Patient> call() throws Exception {
			return svc.getPatients();
		}
	}, new TaskCallback<List<Patient>>() {

		@Override
		public void success(List<Patient> result) {
			for (Patient pt: result){
				//Log.d(TAG, "Med retrieved name: "+ med.getmedicationName());
				ContentValues values = new ContentValues();
				values.put(DbOpenHelper.PATIENT_COL_NAME,pt.getUserName() );
				mDbHelper.getWritableDatabase().insert(DbOpenHelper.PATIENTS_TABLE_NAME, null, values);
				Editor editor = prefs.edit();
				editor.putBoolean(context.getString(R.string.patients_fetched_key), true);
				editor.commit();
			}

			
			
		}
		
		@Override
		public void error(Exception e) {
			Log.e(LoginActivity.class.getName(), "Error Getting patient Meds.", e);
			
			Toast.makeText(
					context,
					"Error Getting patient.",
					Toast.LENGTH_SHORT).show();
		}
	});
	}

	/*public void postCheckin(final CheckinDTO checkinDTO) {
		CallableTask.invoke(new Callable<OkResponse>() {

			@Override
			public OkResponse call() throws Exception {
				return svc.postCheckin(checkinDTO);
			}
		}, new TaskCallback<OkResponse>() {

			@Override
			public void success(OkResponse result) {
				
			
				context.startActivity(new Intent(
						context,
						activity));
			}
			
			@Override
			public void error(Exception e) {
				Log.e(LoginActivity.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						context,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
		
	} */


	
	
}
