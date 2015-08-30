package com.geoteam.geostory;
/**
 *WARNING - THIS FILE IS NOT CURRENTLY USED. WE ARE SIMPLY KEEPING IT HERE AS A CODE REFERENCE
 */
/*
package com.geoteam.geostory;

import android.util.Log;


import com.loopj.android.http.*;
import org.apache.http.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

*/
/*
import org.json.*;

import android.util.Log;
*/
//This class will contain all public static methods 
//Used for requesting data from the servers.
//It will also parse server responses.
/**
 * 
 * @author admin
 * @deprecated
 */
/*
public class ServerInterface {
	/*
	//Converts story to JSON and sends to server
	public static void createStory(GeoStory story) throws JSONException
	{

		
		

		RequestParams params = new RequestParams();
		params.put("action", "newStory");
		params.put("title",story.getTitle());
		params.put("storyText", story.getText());
		params.put("latitude", "" + story.getLatitude());
		params.put("longitude", "" + story.getLongitude());
		params.setUseJsonStreamer(true);


		JsonGeoStoryClient.post("", params, new JsonHttpResponseHandler(){
			
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //save result. send thread alert

            	Log.e("STORY CREATED",response.toString());
            	//listener.onNewStory("storyCreated");adm
            }
            
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                // Pull out the first event on the public timeline
     
            	Log.e("STORY CREATED",responseArray.toString());
            	//listener.onNewStory("storyCreated");

            }

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Log.e("json failed","json failed");

			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONArray errorResponse) {
				Log.e("json failed","json failed");
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, JSONObject errorResponse) {
				Log.e("json failed","json failed");
			}
            
            
        });
		

		//TODO now actually send.
	}
	*/

/*
	
	//Takes in a lattitude and longitude and returns a list of stories nearby
	public static ArrayList<GeoStory> GetStoriesByDistance(double lat, double lon)
	{
		try
		{
			JSONObject json = new JSONObject();

			json.put("action", "getStoriesByDistance");
			json.put("latitude", "" + lat );
			json.put("longitude", "" + lon);
			json.put("distance", "100");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO: contant server, parse each object and return as arraylist
		//TODO investigate JSON parser
		return null;
	}
	
}

*/
