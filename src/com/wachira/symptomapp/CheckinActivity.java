package com.wachira.symptomapp;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.OnClick;

import com.wachira.symptomapp.CheckinFragment.OnSubmitCheckinListener;
import com.wachira.symptomapp.services.CheckinDTO;
import com.wachira.symptomapp.services.MedicationHistoryDTO;
import com.wachira.symptomapp.services.PatientMedsDTO;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class CheckinActivity extends Activity
	implements OnSharedPreferenceChangeListener,OnTimeSetListener,
	OnDateSetListener,OnSubmitCheckinListener   {

	private static final String TAG = "CheckinActivity";
	private SharedPreferences sharedPref;
	private SymptomAlarm symptomAlarm;
	private FragmentManager fragMgr;
	public static  String username;
	public static  String password;
	private CheckinDTO checkin;
	private MedicationHistoryDTO currMedHx;
	private PatientMedsDTO currPtMedsDTO;
	//private Timestamp currTimestamp;
	private Calendar currMedTakenCal;
	private int currMedYear;
	private int currMedMonth;
	private int currMedDay;
	private int currMedHour;
	private int currMedMinute;
	
	private List<MedicationHistoryDTO> medHxList;
	private RemoteServer rserver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_checkin);
		
		fragMgr = getFragmentManager();
		FragmentTransaction ft = fragMgr
				.beginTransaction();
		ft.add (R.id.content_frame, new CheckinFragment());
		//ft.addToBackStack("checkin");
		ft.commit();
		
		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		username = sharedPref.getString(getString(R.string.username_key), null);
		password = sharedPref.getString(getString(R.string.pass_key), null);
		
		rserver = new RemoteServer(this,PatientsCheckinsListActivity.class,username,password);
		AlarmManager malarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		symptomAlarm = new SymptomAlarm(sharedPref, malarmManager, this);
		boolean alarm_set = sharedPref.getBoolean(getString(R.string.is_alarm_set_key), false);
		if(!alarm_set){
			symptomAlarm.setSymptomAlarm();
		}
		checkin = new CheckinDTO();
		medHxList = new ArrayList<MedicationHistoryDTO>();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkin, menu);
		Log.d(TAG,"called menu inflater");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
	
		   FragmentTransaction ft= fragMgr.beginTransaction();
			ft.replace(R.id.content_frame, new SettingsFragment());
			ft.addToBackStack(null);
			ft.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onResume() {
	    super.onResume();
		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
				
		sharedPref.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
	    super.onPause();
	    sharedPref
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		
		if (key.equals("alarm_freq") ){
			String strval = prefs.getString(key, "");
			if(strval.equals("0")){
				symptomAlarm.cancelAlarm();

			}else{
			Long longvalue = Long.parseLong(strval );
			Long next_alarm = 24/longvalue * 60 *60 *1000;
			symptomAlarm.setNextAlarmTime(next_alarm);
			}
		}
	}
	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((Switch) view).isChecked();
	    
	    if (on) {
	    	checkin.setMedicationtaken(true);
			FragmentTransaction ft = fragMgr
					.beginTransaction();
			ft.replace(R.id.content_frame, new MedsListFragment());
			ft.addToBackStack(null);
			ft.commit();
	    } else {
	    	checkin.setMedicationtaken(false);
	  
	    }
	}
	
	public void onMedSelected(ListView l, View v, int position, long id){
		Log.d(TAG,"On Medselected called");
		currMedHx  = new MedicationHistoryDTO(); 
		currMedTakenCal = Calendar.getInstance();
		
		//currTimestamp = new Timestamp(2014, 0, 0, 0, 0, 0, 0);
		currPtMedsDTO = new PatientMedsDTO();
		Cursor cursor =(Cursor) l.getItemAtPosition(position);
		currPtMedsDTO.setmedicationName(cursor.getString(1) );;
		currMedHx.setMedication(currPtMedsDTO);
		FragmentTransaction ft = fragMgr.beginTransaction();
		ft.replace(R.id.content_frame, new EditMedDetailFragment());
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}


	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		Log.d(TAG,"YEar from picker are: "+ year);
		Log.d(TAG,"Month from picker are: "+ monthOfYear);
		Log.d(TAG,"Days from picker are: "+ dayOfMonth);
		
		/*currTimestamp.setYear(year);
		currTimestamp.setMonth(monthOfYear);
		currTimestamp.setDate(dayOfMonth);
	*/
		currMedYear = year;
		currMedMonth = monthOfYear;
		currMedDay = dayOfMonth;
		
	} 

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		
		Log.d(TAG,"Hours from picker are: "+ hourOfDay);
		Log.d(TAG,"Minutes from picker are: "+ minute);
		//currTimestamp.setHours(hourOfDay);
		//currTimestamp.setMinutes(minute);
		
		currMedHour = hourOfDay;
		currMedMinute = minute;
		}

	@Override
	public void onSubmitCheckin(String painResponse, String eatingResponse) {
		
		Calendar cal = Calendar.getInstance();
		checkin.setCheckindate(new Timestamp(cal.getTimeInMillis()));
		
		checkin.setPainseverity(painResponse);
		checkin.setEatingimpact(eatingResponse);
		checkin.setMedicationHistories(medHxList);
		rserver.postCheckin(checkin);
		
	}
	
	public void submitMedDetail(View view){
		currMedTakenCal.set(currMedYear, currMedMonth, 
				currMedDay, currMedHour, currMedMinute,0);
		currMedHx.setTimeTaken(new Timestamp(currMedTakenCal.getTimeInMillis()));
		medHxList.add(currMedHx);
		Log.d(TAG,"added medHx to list: "+ currMedHx.getMedication().getmedicationName() );
	}

}
