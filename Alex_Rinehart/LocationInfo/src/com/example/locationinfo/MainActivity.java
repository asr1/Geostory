package com.example.locationinfo;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Let's update every time we move.
        LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locList = new LocationListener_custom();
        locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, locList);
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 , 0, locList);
    }

    
    public class LocationListener_custom implements LocationListener
    {
    	@Override
    	public void onLocationChanged(Location loc)
    	{
    		
    		//This will popup with the present location whenever it changes
    		String display = "Lat: " + loc.getLatitude() + "\nLon: " + loc.getLongitude();
    		Toast.makeText(getApplicationContext(), display, Toast.LENGTH_SHORT).show();
    	}
    	
    	@Override
    	public void onProviderDisabled(String Provider)
    	{
    		Toast.makeText(getApplicationContext(), "GPS Disabled: " + Provider, Toast.LENGTH_SHORT).show();
    	}
    	
    	@Override
    	public void onProviderEnabled(String Provider)
    	{
    		Toast.makeText(getApplicationContext(), "GPS Enabled: " + Provider, Toast.LENGTH_SHORT).show();
    	}
    	
    	@Override
    	public void onStatusChanged(String Provider, int status, Bundle extras)
    	{
    		//Do Nothing
    	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
}
