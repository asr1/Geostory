package com.geoteam.geostory;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NearbyListActivity extends Activity implements ServerCommunicatorListener
{

	private AsyncServerCommuncator communicator = new AsyncServerCommuncator();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stories_list_view);
     
        communicator.registerListener(NearbyListActivity.this);
        communicator.executeGetStoriesByDistanceyAction();
	}

	
	@Override
	public void onServerJSONResponse(JSONObject serverResponse) {
		final ArrayList<GeoStory> geoStoryList = AsyncServerCommuncator.interpretGetStoryByDistanceAction(serverResponse);

	
		final ArrayAdapter<GeoStory> adapter = new ArrayAdapter<GeoStory>(this,
		        android.R.layout.simple_list_item_1, geoStoryList);	

		
		runOnUiThread( new Runnable() {
			@Override
			public void run() {


				if (!adapter.isEmpty()){
					ListView listView1 = (ListView) findViewById(R.id.listView1);
					listView1.setOnItemClickListener(new OnItemClickListener()  {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent i = new Intent(NearbyListActivity.this, ViewStoryActivity.class);
						    i.putExtra("storyId", geoStoryList.get(position).getId());
						    startActivity(i);
						};
					}); 
				
					listView1.setAdapter(adapter);
				} else {
					
					Toast toast = Toast.makeText(getApplicationContext(), "no nearby Stories", Toast.LENGTH_LONG);
					
					toast.show();
				}

				
				
				   
			}
		});
		
	}

	@Override
	public void onServerImageResponse(File serverResponse) {
		// TODO Auto-generated method stub
		
	}

	
}