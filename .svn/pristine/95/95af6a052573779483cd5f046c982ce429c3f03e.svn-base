package com.geoteam.geostory;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyPostsListActivity extends Activity implements ServerCommunicatorListener {

	
	private AsyncServerCommuncator communicator = new AsyncServerCommuncator();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_posts_list_actvity);

        communicator.registerListener(MyPostsListActivity.this);
        communicator.executeGetCurrentUserAction();
       
	}
	
	
	@Override
	public void onServerJSONResponse(JSONObject serverResponse) {
		
		
		
		if(AsyncServerCommuncator.getActionType(serverResponse).equals("getSessionInfo")){
			
			
				
			User currentUser = AsyncServerCommuncator.interpretGetCurrentUserAction(serverResponse);
			Log.i("current user response","Username:" + currentUser.getName());
			if (currentUser.isAnonymous()){
				Intent intent = new Intent(this, HomeScreenActivity.class);
				Toast toast = Toast.makeText(getApplicationContext(), "User is not logged in", Toast.LENGTH_LONG);
				toast.show();
				startActivity(intent);
				this.finish();
			} else{
				 communicator.executeGetStoriesByEmailAction(currentUser.getName());
			}

				
				
		} else {
			
			
			final ArrayList<GeoStory> geoStoryList = AsyncServerCommuncator.interpretGetStoriesByEmailAction(serverResponse);

		
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
								Intent i = new Intent(MyPostsListActivity.this, ViewStoryActivity.class);
							    i.putExtra("storyId", geoStoryList.get(position).getId());
							    startActivity(i);
							};
						}); 
					
						listView1.setAdapter(adapter);
					} else {
						Toast toast = Toast.makeText(getApplicationContext(), "You have created no stories", Toast.LENGTH_LONG);
						toast.show();
					}				   
				}
			});
			
			
			
		}
		
		

		
	}


	
	
	@Override
	public void onServerImageResponse(File serverResponse) {
		// TODO Auto-generated method stub
		
	}

}
