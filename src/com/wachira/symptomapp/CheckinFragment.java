package com.wachira.symptomapp;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


public class CheckinFragment extends Fragment{

	@InjectView(R.id.spinnerqpain)
	protected Spinner spinnerqpain;

	@InjectView(R.id.spinnerqeating)
	protected Spinner spinnerqeating;
	
	@InjectView(R.id.switchqmeds)
	protected Switch switchqmeds;
	
	@InjectView(R.id.submitCheckin)
	protected Button submitCheckin;
	
	private RemoteServer rserver;
	//private String[] myMeds;
	
	//private SharedPreferences prefs; 
	private Context context;
	private OnSubmitCheckinListener  mCallback;
	
	public interface OnSubmitCheckinListener{
		public void onSubmitCheckin(String painResponse, String eatingResponse);
	}
	
	@Override
    public void onAttach(Activity activity) {
		super.onAttach(activity);
		 try {
	            mCallback = (OnSubmitCheckinListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnSubmitCheckinListener");
	        }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		this.context = this.getActivity();
		//prefs =PreferenceManager.getDefaultSharedPreferences(context);
		this.rserver = new RemoteServer(this.getActivity(), null,
				CheckinActivity.username, CheckinActivity.password);
		View view = inflater.inflate(R.layout.checkin_fragment, container, false);
		 ButterKnife.inject(this, view);
		 return view;
	}
	
	@OnClick(R.id.submitCheckin)
	public void submitCheckin(){
		mCallback.onSubmitCheckin(spinnerqpain.getSelectedItem().toString(),
				spinnerqeating.getSelectedItem().toString());
			
	}
	

}