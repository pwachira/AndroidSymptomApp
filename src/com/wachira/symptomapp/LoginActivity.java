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

public class LoginActivity extends Activity {
	
	private static final String TAG = "LoginScreen";
	private static final String ChekinSvcUrl = "http://ec2-54-196-113-30.compute-1.amazonaws.com:8080/";
	private SharedPreferences prefs;
	private RemoteServer rserver;
	
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
		
		if (loggedIn){
			rserver = new RemoteServer(this,CheckinActivity.class, user, pass);
			rserver.performPostLoginActivity();
		}
		setContentView(R.layout.loginscreen);
		ButterKnife.inject(this);

	}
	
	@OnClick(R.id.BtnPatientLogin)
	public void PatientLogin() {
		final String user = uname.getText().toString();
		final String pass = passwd.getText().toString();
		rserver = new RemoteServer(this,CheckinActivity.class, user, pass);
		
		rserver.performPostLoginActivity();
	}
	
	@OnClick(R.id.BtnDocLogin)
	public void DocLogin() {
		final String user = uname.getText().toString();
		final String pass = passwd.getText().toString();
		DoctorRemoteServer rserver = new DoctorRemoteServer(this,DoctorActivity.class, user, pass);
		
		rserver.performPostLoginActivity();
	}
	
	

}
