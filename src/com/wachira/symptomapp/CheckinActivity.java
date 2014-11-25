package com.wachira.symptomapp;

//import course.examples.Alarms.AlarmCreate.AlarmCreateActivity;
import com.wachira.symptomapp.AlarmNotificationReceiver;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;

public class CheckinActivity extends Activity {

	private SharedPreferences sharedPref;
	private SymptomAlarm symptomAlarm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);

		
		sharedPref = getPreferences(Context.MODE_PRIVATE);
		AlarmManager malarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		symptomAlarm = new SymptomAlarm(sharedPref, malarmManager, this);
		boolean alarm_set = sharedPref.getBoolean(getString(R.string.is_alarm_set_key), false);
		if(!alarm_set){
			symptomAlarm.setSymptomAlarm();
		}
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
