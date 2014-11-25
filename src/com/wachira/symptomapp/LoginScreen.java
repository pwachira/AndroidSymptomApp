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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
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
		setContentView(R.layout.loginscreen);
		ButterKnife.inject(this);
		prefs = getPreferences(MODE_PRIVATE);
		

	}
	
	@OnClick(R.id.BtnPatientLogin)
	public void PatientLogin() {
		final String user = uname.getText().toString();
		final String pass = passwd.getText().toString();
		final CheckinSvcApi svc = CheckinSvc.init(ChekinSvcUrl, user, pass);

		CallableTask.invoke(new Callable<OkResponse>() {

			@Override
			public OkResponse call() throws Exception {
				return svc.authenticateUser();
			}
		}, new TaskCallback<OkResponse>() {

			@Override
			public void success(OkResponse result) {
				
				//save prefs
				Editor editor = prefs.edit();
				editor.putString(getString(R.string.patient_username_key), user);
				editor.putString(getString(R.string.patient_username_key), pass);
				editor.commit();
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
		});
	}
	
	@OnClick(R.id.BtnDocLogin)
	public void DocLogin() {
		String user = uname.getText().toString();
		String pass = passwd.getText().toString();
		Log.d(TAG, "Doc Login");
		Intent ckinIntent = new Intent(this, CheckinActivity.class);
		startActivity(ckinIntent);
	}

}
