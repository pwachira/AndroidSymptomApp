package com.wachira.symptomapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

public class SymptomAlarm {
	private static final String TAG = "SymptomAlarm";
	
	private AlarmManager mAlarmManager;
	private Intent mNotificationReceiverIntent;
	private PendingIntent mNotificationReceiverPendingIntent;
//	private static final long INITIAL_ALARM_DELAY = 1 * 60 * 1000L;
	private boolean isAlarmSet ;
	private SharedPreferences sharedPref;
	private Context context;
	private long alarmInterval;
	private int isAlarmSetKey = R.string.is_alarm_set_key;
	private long default_next_alarm =   6 *60 *60*1000;

	
	public SymptomAlarm(SharedPreferences sharedPref,AlarmManager mAlarmManager,Context context){
		this.sharedPref = sharedPref;
		this.mAlarmManager = mAlarmManager;
		this.context = context;
		alarmInterval = sharedPref.getLong(context.getString(R.string.next_alarm_time),default_next_alarm);
		Log.d(TAG, "In symptom alarm constructor with timeinterval:" + alarmInterval);
	}
	
	public  void   setSymptomAlarm(){

		Log.d(TAG, "In set alarm method");

	// Create PendingIntent to start the AlarmNotificationReceiver
	mNotificationReceiverIntent = new Intent(context,
			AlarmNotificationReceiver.class);
	mNotificationReceiverPendingIntent = PendingIntent. getBroadcast(
			context, 0, mNotificationReceiverIntent, 0);
	
	mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + alarmInterval, mNotificationReceiverPendingIntent);
	
	/*mAlarmManager.setInexactRepeating(
			AlarmManager.ELAPSED_REALTIME,
			SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
			AlarmManager.INTERVAL_FIFTEEN_MINUTES,
			mNotificationReceiverPendingIntent); */

	}
	
	public void setNextAlarmTime(long nextAlarmTime){
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(context.getString(R.string.next_alarm_time), nextAlarmTime);
		editor.putBoolean(context.getString(R.string.is_alarm_set_key), true);
		editor.commit();
	}

}
