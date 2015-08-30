package com.example.mapstest2;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapBasic extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_basic);
        
   
        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        //make a coord
        
        ArrayList<LatLng> places = new ArrayList<LatLng>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> snippets = new ArrayList<String>();
        
        LatLng lake = new LatLng(42.0239, -93.6476);
        LatLng library = new LatLng(42.0288782,-93.6479567);
        LatLng ames = new LatLng(42.0266187,-93.6464654);
        LatLng geo1 = new LatLng(42,-93);
        LatLng geo2 = new LatLng(43,-94);
        LatLng geo3 = new LatLng(42,-94);
        LatLng geo4 = new LatLng(43,-93);
        
        places.add(lake);
        places.add(library);
        places.add(ames);
        places.add(geo1);
        places.add(geo2);
        places.add(geo3);
        places.add(geo4);
        
        names.add("Lake LeVerne");
        names.add("Parks Library");
        names.add("Ames");
        names.add("A GeoStory");
        names.add("Another GeoStory");
        names.add("Geo3");
        names.add("Geo4");
        
        snippets.add(null);
        snippets.add(null);
        snippets.add("Iowa State University");
        snippets.add("(42,-93)");
        snippets.add("(43,-94)");
        snippets.add("(42.-94)");
        snippets.add("(43.-93)");
        //LatLng ames = new LatLng(42.0239, -93.6476);
        
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ames, 13));

        for(int i = 0; places.size() > i; i++)
        {
        	if(names.get(i) != null)
        	{
        		if(snippets.get(i) != null)
        		{
        			map.addMarker(new MarkerOptions()
                    .title(names.get(i))
                    .snippet(snippets.get(i))
                    .position(places.get(i)));
        		}
        		else
        		{
        			map.addMarker(new MarkerOptions()
                    .title(names.get(i))
                    .position(places.get(i)));
        		}
        	}
        	else
        	{
        		map.addMarker(new MarkerOptions()
                .position(places.get(i)));
        	}
        }
//        map.addMarker(new MarkerOptions()
//                .title("ames")
//                .snippet("Iowa State University.")
//                .position(ames));
//        
//        map.addMarker(new MarkerOptions()
//				.title("Lake LeVerne")
//				.position(lake));
        
       
    }

   
    
}



