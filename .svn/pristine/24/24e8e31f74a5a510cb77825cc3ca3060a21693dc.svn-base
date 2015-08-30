package com.geoteam.geostory;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * GoogleMapsView handles the map interface.
 * @author admin
 *
 */
public class GoogleMapsView extends Activity implements ServerCommunicatorListener
{
	
	private AsyncServerCommuncator communicator = new AsyncServerCommuncator();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps_view);
        
        communicator.registerListener(GoogleMapsView.this);
        communicator.executeGetStoriesByDistanceyAction();
        
	}
        
        
        
//        // Get a handle to the Map Fragment
//        GoogleMap map = ((MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map)).getMap();
//        
//        map.setMyLocationEnabled(true);
//        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(ames, 13));
//        
//        
//        //make a coord
//        LatLng library = new LatLng(42.0288782,-93.6479567);
//        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(library, 13));
//        map.addMarker(new MarkerOptions()
//        .title("Parks Library")
//        .position(library));
	
	
	/**
	 * Callback function
	 */
	@Override
	public void onServerJSONResponse(JSONObject serverResponse) 
	{
		final ArrayList<GeoStory> geoStoryList1 = AsyncServerCommuncator.interpretGetStoryByDistanceAction(serverResponse);
		
		runOnUiThread( new Runnable() {
			@Override
			public void run() {

				// Get a handle to the Map Fragment
		        GoogleMap map = ((MapFragment) getFragmentManager()
		                .findFragmentById(R.id.map)).getMap();
		        
		        map.setMyLocationEnabled(true);
		        
				if (geoStoryList1.size() != 0)
				{
					int firstStoryFlag = 0;
					//put Stories on the map
			        for(int i = 0; geoStoryList1.size()> i; i++)
			        {
			        	GeoStory temp = geoStoryList1.get(i);
//			        	if(temp.getTitle() != null && temp.getId() != 0 && temp.getText() != null)
//			        	{
//			        		LatLng tempCoord = new LatLng(temp.getLatitude(),temp.getLongitude());
//			        		map.addMarker(new MarkerOptions()
//		                    .title(temp.toString())
//		                    .position(tempCoord));
//			        		if(firstStoryFlag == 0)
//			        		{
//			        			map.moveCamera(CameraUpdateFactory.newLatLngZoom(tempCoord, 13));
//			        			firstStoryFlag = 1;
//			        		}
//			        	}
			        	
			        		LatLng tempCoord = new LatLng(temp.getLatitude(),temp.getLongitude());
			        		map.addMarker(new MarkerOptions()
		                    .title(temp.toString())
		                    .position(tempCoord));
			        		if(firstStoryFlag == 0)
			        		{
			        			map.moveCamera(CameraUpdateFactory.newLatLngZoom(tempCoord, 13));
			        			firstStoryFlag = 1;
			        		}
			        	
			        }
				} else 
				{
					//nothing
				
				}
			}
		});	
	}



	@Override
	public void onServerImageResponse(File serverResponse) {
		// TODO Auto-generated method stub
		
	}
	
}