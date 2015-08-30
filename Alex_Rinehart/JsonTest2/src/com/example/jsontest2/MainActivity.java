package com.example.jsontest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	

	
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        
    }

    
    public void sendMessage(View view) 
    {
        Intent intent = new Intent(FromActivity.this, ToActivity.class);
        startActivity(intent);
    }
//    
//    /** Called when the user clicks the Send button 
//     * @throws JSONException */
//    public void sendMessage(View view) throws JSONException {
//
//    	
//    	
//    	
//    	Toast.makeText(getApplicationContext(), "Running!", Toast.LENGTH_SHORT).show();
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://proj-309-f11.cs.iastate.edu/geostory", new AsyncHttpResponseHandler() {
//           
//  
//			@Override
//			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
//					Throwable arg3) {
//				Toast.makeText(getApplicationContext(), "Failure!", Toast.LENGTH_SHORT).show();
//				
//			}
//
//			@Override
//			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
//				System.out.println("success");
//				Toast.makeText(getApplicationContext(), "Victory!", Toast.LENGTH_SHORT).show();
//				
//			}
//        });}
//    	
//        //JSON TESTING
//        
////  	  JSONObject obj=new JSONObject();
////  	  obj.put("name","foo");
////  	  obj.put("num",new Integer(100));
////  	  obj.put("balance",new Double(1000.21));
////  	  obj.put("is_vip",new Boolean(true));
////  	  obj.put("nickname",null);
////  	  
////  	  
////  	StringEntity se = null;
////  	try {
////  	    se = new StringEntity(obj.toString());
////  	} catch (UnsupportedEncodingException e) {
////  	    // handle exceptions properly!
////  	}
////  	se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
////
////	};
////  			
////  	client.post(null, "http://proj-309-f11.cs.iastate.edu/geostory", se, "application/json",  response.getStatusLine().getStatusCode());
////
////  
////    }
//    
    

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
