package com.wachira.symptomapp;

import butterknife.ButterKnife;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditMedDetailFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.edit_med_fragment, container, false);
		 //super.onCreateView(inflater, container, savedInstanceState);  
		ButterKnife.inject(this, view);
		 return view;	
	}
	

}
