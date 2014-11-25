package com.wachira.symptomapp;


import java.util.Collection;
import java.util.concurrent.Callable;

import retrofit.client.Response;

import com.wachira.symptomapp.CallableTask;
import com.wachira.symptomapp.TaskCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.wachira.symptomapp.R;
import com.wachira.symptomapp.services.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity {
	
	private static final String TAG = "LoginScreen";
	private static final String ChekinSvcUrl = "http://ec2-54-196-113-30.compute-1.amazonaws.com:8080/";
	private SharedPreferences prefs;
	
	@InjectView(R.id.username_edittext)
	protected EditText uname;

	@InjectView(R.id.password_edittext)
	protected EditText passwd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//prefs = getSharedPreferences("SymptomApp", MODE_PRIVATE); 
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		boolean loggedIn = prefs.getBoolean(getString(R.string.isauthenticated_key), false);
		String user = prefs.getString(getString(R.string.username_key), null);
		String pass = prefs.getString(getString(R.string.pass_key), null);
		Log.d(TAG, " isauthenticated key and value are:"+ getString(R.string.isauthenticated_key) + ": " + loggedIn);
		//Log.d(TAG, "User is logged in ");
		//Log.d(TAG, "User is logged in ");
		if (loggedIn){
			Log.d(TAG, "User is logged in ");
			performPostLoginAtivity(CheckinActivity.class, user, pass);
		}
		Log.d(TAG, "Before setcontent ");
		setContentView(R.layout.loginscreen);
		Log.d(TAG, "Before butterknnife ");
		ButterKnife.inject(this);
		Log.d(TAG, "after butterknife ");



	}
	
	@OnClick(R.id.BtnPatientLogin)
	public void PatientLogin() {
		final String user = uname.getText().toString();
		final String pass = passwd.getText().toString();
		performPostLoginAtivity(CheckinActivity.class, user, pass);
	/*	final CheckinSvcApi svc = CheckinSvc.init(ChekinSvcUrl, user, pass,this.getApplicationContext());

		CallableTask.invoke(new Callable<OkResponse>() {

			@Override
			public OkResponse call() throws Exception {
				return svc.authenticateUser();
			}
		}, new TaskCallback<OkResponse>() {

			@Override
			public void success(OkResponse result) {
				
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				
				startActivity(new Intent(
						LoginScreen.this,
						CheckinActivity.class));
			}
			
			@Override
			public void error(Exception e) {
				Log.e(LoginScreen.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						LoginScreen.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});*/
	}
	
	@OnClick(R.id.BtnDocLogin)
	public void DocLogin() {
		String user = uname.getText().toString();
		String pass = passwd.getText().toString();
		Log.d(TAG, "Doc Login");
		Intent ckinIntent = new Intent(this, CheckinActivity.class);
		startActivity(ckinIntent);
	}
	
	private void performPostLoginAtivity( final Class activity, String user, String pass){
		final CheckinSvcApi svc = CheckinSvc.init(ChekinSvcUrl, user, pass,this.getApplicationContext());

		CallableTask.invoke(new Callable<OkResponse>() {

			@Override
			public OkResponse call() throws Exception {
				return svc.authenticateUser();
			}
		}, new TaskCallback<OkResponse>() {

			@Override
			public void success(OkResponse result) {
				
				// OAuth 2.0 grant was successful and we
				// can talk to the server, open up the video listing
				
				startActivity(new Intent(
						LoginScreen.this,
						activity));
			}
			
			@Override
			public void error(Exception e) {
				Log.e(LoginScreen.class.getName(), "Error logging in via OAuth.", e);
				
				Toast.makeText(
						LoginScreen.this,
						"Login failed, check your Internet connection and credentials.",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
