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
import com.wachira.symptomapp.services.PatientMedsDTO;

public class RemoteServer {
	private static final String TAG = "RemoteServer";
	private static final String ChekinSvcUrl = "http://ec2-54-196-113-30.compute-1.amazonaws.com:8080/";
	private Context context;
	private Class<Activity> activity;
	private String user;
	private String password;
	private CheckinSvcApi svc;
	private SharedPreferences prefs;
	private DbOpenHelper mDbHelper;

	
	public RemoteServer(Context context, final Class activity , String user, String pass){
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
	//Get Patients medications
		Log.d(TAG, "Before Checking for Meds");
		if(!prefs.getBoolean(context.getString(R.string.meds_fetched_key), false)
				){
			Log.d(TAG, "Meds notfound calling server");
			getPatientMeds();
		}
		
	}
	
	public void getPatientMeds(){
	CallableTask.invoke(new Callable<List<PatientMedsDTO>>() {

		@Override
		public List<PatientMedsDTO> call() throws Exception {
			return svc.getPatientMeds();
		}
	}, new TaskCallback<List<PatientMedsDTO>>() {

		@Override
		public void success(List<PatientMedsDTO> result) {
			for (PatientMedsDTO med: result){
				//Log.d(TAG, "Med retrieved name: "+ med.getmedicationName());
				ContentValues values = new ContentValues();
				values.put(DbOpenHelper.MED_NAME,med.getmedicationName() );
				mDbHelper.getWritableDatabase().insert(DbOpenHelper.MEDS_TABLE_NAME, null, values);
				Editor editor = prefs.edit();
				editor.putBoolean(context.getString(R.string.meds_fetched_key), true);
				editor.commit();
			}

			
			
		}
		
		@Override
		public void error(Exception e) {
			Log.e(LoginActivity.class.getName(), "Error Getting patient Meds.", e);
			
			Toast.makeText(
					context,
					"Error Getting patient Meds.",
					Toast.LENGTH_SHORT).show();
		}
	});
	}

	public void postCheckin(final CheckinDTO checkinDTO) {
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
		
	}


	
	
}
