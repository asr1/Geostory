package com.geoteam.geostory;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.location.LocationClient;

/**
 * This is a helper class that handles functions related to retrieving location coordinates. 
 * @author admin
 *
 */
public class LocationServices {

	private static Location loc;
	private static Context context;
	private static LocationManager  locaMan;
	private static LocationListener locList = new LocationListener_custom();
	private static LocationClient LocCli;
	
	
	//Used to pass the ONE location manager/context throughout the
	//App. Used for server interaction.
	public static void setContext(Context NewContext)
	{
		context = NewContext;
		 locaMan = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		 //Done here to remain current.
	}

	/**
	 * Simple setter.
	 * @param newLoc
	 */
	public static void setLocation(Location newLoc)
	{
		loc = newLoc;
	}
	
	/**
	 * Used for passing ONE location client through the app.
	 * @return The current global location client
	 */
	public static LocationClient getLocCli()
	{
		return LocCli;
	}
	
	//Simple setter for location CLient. Called once.
	public static void createLocli(LocationClient client)
	{
		LocCli = client;
	}
	
 
  //Return the current latitude or longitude
  public static double getLocation(boolean lon)
  {
	  try {

  LocCli.connect();
  locaMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, locList);
  locaMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 , 0, locList);
  if (loc != null)
  {
	  //One function for long and latt
	  if(lon == true)
	  {
		  return loc.getLongitude();
	  }
	  else
		  return loc.getLatitude();
	
	  }
	  else //Default value. Server expects zero is an error.
		  return -1;
  } catch (Exception e){
	  return -1;
  }
	  
  }
  
  
	
}
