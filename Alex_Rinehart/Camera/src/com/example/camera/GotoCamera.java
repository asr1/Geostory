package com.example.camera;

import java.io.ByteArrayOutputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class GotoCamera extends ActionBarActivity  {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	// The preview of the picture that is taken
	private ImageView iV;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goto_camera);
	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    // start the image capture Intent
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	    Preview();
	    
	}
	// method that sets iV to the imageview to show up on the screen
	public void Preview()
	{
		iV = (ImageView) findViewById(R.id.cameraPreviews);
	}

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
          	Bundle extras = data.getExtras();
          	//get the photo
            Bitmap photo = (Bitmap) extras.get("data"); 
           // set image to the screen
            iV.setImageBitmap(photo);
            
    		JSONObject json = new JSONObject();
    		
   		try {  
//    			json.put("action", "newStory");
//    			json.put("title", "JSON Story From Phone");
//    			json.put("storyText", "This is a test of the photo sending");
//    			json.put("lattitude", "93.6200");
//    			json.put("longitude", "42.0347");
    		
   			
   			
			json.put("action", "getStoriesByDistance");
			json.put("lattitude", "93.6200");
			json.put("longitude", "42.0347");
			json.put("distance", 100);
    		
    		
    			//json.put("image", getStringFromBitmap(photo));
    		} catch (JSONException e1) {
    			e1.printStackTrace();
    		}
    		
//    		TextView myText = new TextView(getBaseContext());
//    		myText = (TextView)findViewById(android.R.id.text2);
//    		setContentView(lView);
//    		myText.setText("My Text");
//    
//    		lView.addView(myText);
//    		
    		
    		//Toast.makeText(getApplicationContext(), json.toString(),Toast.LENGTH_LONG ).show();
    	
    		//new SendJsonToServer(getApplicationContext(), (MainActivity) getIntent().getSerializableExtra("MainAct")).execute(json);
   
    		new SendJsonToServer(getApplicationContext()).execute(json);
    		
    	// new SendJsonToServer(getApplicationContext(), lView).execute(json);
 		//In theory my async task just updated lview
 		//setContentView(lView);
        }  
    } 
    

    
    @SuppressLint("NewApi") private String getStringFromBitmap(Bitmap bitmapPicture) {
      	 /*
      	 * This functions converts Bitmap picture to a string which can be
      	 * JSONified.
      	 * */
      	 final int COMPRESSION_QUALITY = 100;
      	 String encodedImage;
      	 ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
      	 bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
      	 byteArrayBitmapStream);
      	 byte[] b = byteArrayBitmapStream.toByteArray();
      	 encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
      	 return encodedImage;
      	 }
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.goto_camera, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_goto_camera,
					container, false);
			return rootView;
		}
	}*/
}
