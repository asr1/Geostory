package com.example.camera;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements Serializable {

	//View for displaying error message
	 // LinearLayout lView = new LinearLayout(getBaseContext());
		
	
	// called when click on "Camera" button
	public void sendMessage(View view) {
		
	
		
		// creates new intent to start Camera activity
		Intent intent = new Intent(this, GotoCamera.class);
		intent.putExtra("MainAct", this);
		startActivity(intent);

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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


    public void setResponse(String str)
    {
   	  LinearLayout lView = new LinearLayout(getApplicationContext());
    	//Todo. Okay, make a textview here. 
    	//And display this on screen. 
		TextView myText = new TextView(getApplicationContext());
		myText.setText("My Text");

		//lView.addView(myText);
    }

}
