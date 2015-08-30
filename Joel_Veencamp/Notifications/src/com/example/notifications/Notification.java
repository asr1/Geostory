package com.example.notifications;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Notification extends ActionBarActivity {
		
	
	
	// Sets an ID for the notification
	int mNotificationId = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
		// get runnable
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			// override the run function to display notification every 10 seconds
		  @Override
		  public void run() {
		    sendNotification(null);
		  }
		}, 0, 10, TimeUnit.SECONDS);

	}
	
	
	public void sendNotification(View view)
	{
		// creates the notification builder object
		NotificationCompat.Builder mBuilder =
			    new NotificationCompat.Builder(this)
				.setAutoCancel(true)
			    .setSmallIcon(R.drawable.ic_launcher)
			    .setContentTitle("There is a story nearby!")
			    .setContentText("click to view");
	
		Intent resultIntent = new Intent(this, ResultActivity.class);
		// Because clicking the notification opens a new ("special") activity, there's
		// no need to create an artificial back stack.
		PendingIntent resultPendingIntent =
		    PendingIntent.getActivity(this,
		    0,
		    resultIntent,
		    PendingIntent.PARCELABLE_WRITE_RETURN_VALUE
		);
		
		mBuilder.setContentIntent(resultPendingIntent);
		
		
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr = 
		        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
		mNotificationId++;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
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
