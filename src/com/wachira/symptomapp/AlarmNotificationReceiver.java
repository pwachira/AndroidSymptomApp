package com.wachira.symptomapp;

import java.text.DateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

public class AlarmNotificationReceiver extends BroadcastReceiver {
	// Notification ID to allow for future updates
	private static final int MY_NOTIFICATION_ID = 1;
	private static final String TAG = "AlarmNotificationReceiver";

	// Notification Text Elements
	private final CharSequence tickerText = "Time to checkin your symptoms!";
	private final CharSequence contentTitle = "Symptoms Checkin";
	private final CharSequence contentText = "Please checkin to enter your symptoms";

	// Notification Action Elements
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;


	private long[] mVibratePattern = { 0, 200, 200, 300 };
	private AlarmManager mAlarmManager;
	private SharedPreferences prefs;
	private SymptomAlarm symptomAlarm;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG,"Received alarm at:" + DateFormat.getDateTimeInstance().format(new Date()));
		mNotificationIntent = new Intent(context, CheckinActivity.class);
		mContentIntent = PendingIntent.getActivity(context, 0,
				mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		 prefs = PreferenceManager.getDefaultSharedPreferences(context);

	    mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	    symptomAlarm = new SymptomAlarm(prefs, mAlarmManager, context);
		
		Notification.Builder notificationBuilder = new Notification.Builder(
				context).setTicker(tickerText)
				.setSmallIcon(android.R.drawable.stat_sys_warning)
				.setAutoCancel(true).setContentTitle(contentTitle)
				.setContentText(contentText).setContentIntent(mContentIntent)
				//.setSound(soundURI)
				.setVibrate(mVibratePattern);

		// Pass the Notification to the NotificationManager:
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(MY_NOTIFICATION_ID,
				notificationBuilder.build());
		
		Log.i(TAG,"Sending notification at:" + DateFormat.getDateTimeInstance().format(new Date()));
		symptomAlarm.setSymptomAlarm();
	
		
	
	}
}
