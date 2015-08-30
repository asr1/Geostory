package com.example.jsontest3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SendJSON extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_json);
		JSONObject json = new JSONObject();
		String tString = "helloWorld";
		
		try {
			json.put("test", tString);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		Toast.makeText(getApplicationContext(), "Running!", Toast.LENGTH_SHORT).show();
		try {
			Toast.makeText(getApplicationContext(), Boolean.toString(json.getBoolean("Test")) , Toast.LENGTH_SHORT).show();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
		HttpConnectionParams.setSoTimeout(httpParams, 10000);
		HttpClient client = new DefaultHttpClient(httpParams);
		HttpPost request = new HttpPost("http://proj-309-f11.cs.iastate.edu/geostory");
		try {
			request.setEntity(new ByteArrayEntity(json.toString().getBytes("UTF-8")));
			HttpResponse  response = client.execute(request);
			Toast.makeText(getApplicationContext(), response.toString() , Toast.LENGTH_SHORT).show();
		} catch (UnsupportedEncodingException e) {
			Toast.makeText(getApplicationContext(), "one!", Toast.LENGTH_SHORT).show();
			//e.printStackTrace();
		} catch (ClientProtocolException e) {
			Toast.makeText(getApplicationContext(), "two!", Toast.LENGTH_SHORT).show();
			//e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "three!", Toast.LENGTH_SHORT).show();
			//e.printStackTrace();
		}
		catch (Exception e)
		{
			Log.e("ERRORERRORERRORERRORERRORERRORERRORERRORERROR", e.getStackTrace()[0].toString());
			Toast.makeText(getApplicationContext(), "four!", Toast.LENGTH_SHORT).show();
		}
		;
		
		
//	      AsyncHttpClient client = new AsyncHttpClient();
//	      client.get("http://proj-309-f11.cs.iastate.edu/geostory", new AsyncHttpResponseHandler() {
//	         
//
//
//				@Override
//				public void onFailure(int arg0, org.apache.http.Header[] arg1,
//						byte[] arg2, Throwable arg3) {
//					Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
//					
//				}
//
//				@Override
//				public void onSuccess(int arg0, org.apache.http.Header[] arg1,
//						byte[] arg2) {
//					Toast.makeText(getApplicationContext(), "Victory!", Toast.LENGTH_SHORT).show();
//					
//				}
//	      });
//	      
	      
        //JSON TESTING
        
//  	  JSONObject obj=new JSONObject();
//  	  obj.put("name","foo");
//  	  obj.put("num",new Integer(100));
//  	  obj.put("balance",new Double(1000.21));
//  	  obj.put("is_vip",new Boolean(true));
//  	  obj.put("nickname",null);
//  	  
//  	  
//  	StringEntity se = null;
//  	try {
//  	    se = new StringEntity(obj.toString());
//  	} catch (UnsupportedEncodingException e) {
//  	    // handle exceptions properly!
//  	}
//  	se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//
//	};
//  			
//  	client.post(null, "http://proj-309-f11.cs.iastate.edu/geostory", se, "application/json",  response.getStatusLine().getStatusCode());
//
//  
//    }
    
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_json, menu);
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
