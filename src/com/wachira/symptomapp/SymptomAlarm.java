package com.wachira.symptomapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.util.Log;

public class SymptomAlarm {
	private static final String TAG = "SymptomAlarm";
	
	private AlarmManager mAlarmManager;
	private Intent mNotificationReceiverIntent;
	private PendingIntent mNotificationReceiverPendingIntent;
	private SharedPreferences sharedPref;
	private Context context;
	private long alarmInterval;
	private long default_next_alarm =   6 *60 *60*1000;

	
	public SymptomAlarm(SharedPreferences sharedPref,AlarmManager mAlarmManager,Context context){
		this.sharedPref = sharedPref;
		this.mAlarmManager = mAlarmManager;
		this.context = context;
		alarmInterval = sharedPref.getLong(context.getString(R.string.next_alarm_time),default_next_alarm);
		//Log.d(TAG, "In symptom alarm constructor with timeinterval:" + alarmInterval);
		mNotificationReceiverIntent = new Intent(context,
				AlarmNotificationReceiver.class);
		mNotificationReceiverPendingIntent = PendingIntent. getBroadcast(
				context, 0, mNotificationReceiverIntent, 0);
	}
	
	public  void   setSymptomAlarm(){	
	mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + alarmInterval, mNotificationReceiverPendingIntent);
	Log.d(TAG,"done setting alarm with interval" + alarmInterval);

	}
	
	public void setNextAlarmTime(long nextAlarmTime){
		Log.d(TAG, "setting net alarm time to"+ nextAlarmTime);
		alarmInterval = nextAlarmTime;
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong(context.getString(R.string.next_alarm_time), nextAlarmTime);
		editor.putBoolean(context.getString(R.string.is_alarm_set_key), true);
		editor.commit();
		setSymptomAlarm();
	}
	
	public void cancelAlarm(){
		mAlarmManager.cancel(mNotificationReceiverPendingIntent);
		Editor editor = sharedPref.edit();
		editor.putBoolean(context.getString(R.string.is_alarm_set_key), false);
		editor.commit();
	}

}
