package com.example.camera;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendJsonToServer extends AsyncTask<JSONObject, Void, String> {

	MainActivity obj;
	
	private Context appContext;
//	private LinearLayout lView;
	
//	public SendJsonToServer(Context context, LinearLayout view)
//	{
//		appContext = context;
//		lView = view;
//	}
//	public SendJsonToServer(Context context, MainActivity instance)
//	{
//		appContext = context;
//		obj = instance;
//	}
	public SendJsonToServer(Context context)
	{
		appContext = context;
		Toast.makeText(appContext, "toast", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected String doInBackground(JSONObject... params) {
	new Thread()
	{
		public void run()
		{
			runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
				}
			});
		}
	}.start();

<<<<<<< .mine
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpClient client = new DefaultHttpClient(httpParams);
			HttpPost request = new HttpPost("http://proj-309-f11.cs.iastate.edu/geostory/echo");
			try {
				request.setEntity(new ByteArrayEntity(params.toString().getBytes("UTF-8")));
				HttpResponse  response = client.execute(request);
=======
//			HttpParams httpParams = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
//			HttpConnectionParams.setSoTimeout(httpParams, 10000);
//			HttpClient client = new DefaultHttpClient(httpParams);
//			HttpPost request = new HttpPost("http://proj-309-f11.cs.iastate.edu/geostory/echo");
	
	
	JSONObject json = new JSONObject();
	
	try {  
//		json.put("action", "newStory");
//		json.put("title", "Sending from Android app");
//		json.put("storyText", "Woah, a story! I did story-worthy things.");
//		json.put("latitude", "93.6200");
//		json.put("longitude", "42.0347");
		
		json.put("action", "getStoriesByDistance");
		json.put("latitude", "93.6200");
		json.put("longitude", "42.0347");
		json.put("distance", "100");
		
		
		
		//json.put("image", getStringFromBitmap(photo));
	} catch (JSONException e1) {
		e1.printStackTrace();
	}
	
	
	
	HttpClient client = new DefaultHttpClient();
	HttpPost post = new HttpPost("http://proj-309-f11.cs.iastate.edu/geostory/json");
	StringEntity se;
	try {
		se = new StringEntity(json.toString());
>>>>>>> .r118

	se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "Application/Json"));
	post.setHeader("Accept", "Application/Json");
	post.setHeader("Conenet-type","Application/Json");
	post.setEntity(se);
	HttpResponse response;
	response = client.execute(post);


	String ResponseBody = getResponseBody(response);
	return ResponseBody;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "error";
	}

}
	
	
	
	public static String getResponseBody(HttpResponse response) {

	    String response_text = null;
	    HttpEntity entity = null;
	    try {
	        entity = response.getEntity();
	        response_text = _getResponseBody(entity);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        if (entity != null) {
	            try {
	                entity.consumeContent();
	            } catch (IOException e1) {
	            }
	        }
	    }
	    return response_text;
	}

	public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

	    if (entity == null) {
	        throw new IllegalArgumentException("HTTP entity may not be null");
	    }

	    InputStream instream = entity.getContent();

	    if (instream == null) {
	        return "";
	    }

	    if (entity.getContentLength() > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException(

	        "HTTP entity too large to be buffered in memory");
	    }

	    String charset = getContentCharSet(entity);

	    if (charset == null) {

	        charset = HTTP.DEFAULT_CONTENT_CHARSET;

	    }

	    Reader reader = new InputStreamReader(instream, charset);

	    StringBuilder buffer = new StringBuilder();

	    try {

	        char[] tmp = new char[1024];

	        int l;

	        while ((l = reader.read(tmp)) != -1) {

	            buffer.append(tmp, 0, l);

	        }

	    } finally {

	        reader.close();

	    }

	    return buffer.toString();

	}

	public static String getContentCharSet(final HttpEntity entity) throws ParseException {

	    if (entity == null) {
	        throw new IllegalArgumentException("HTTP entity may not be null");
	    }

	    String charset = null;

	    if (entity.getContentType() != null) {

	        HeaderElement values[] = entity.getContentType().getElements();

	        if (values.length > 0) {

	            NameValuePair param = values[0].getParameterByName("charset");

	            if (param != null) {

	                charset = param.getValue();

	            }

	        }

	    }

	    return charset;

	}

	protected void runOnUiThread(Runnable runnable) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	protected void onPostExecute(String result)
	{
		Toast.makeText(appContext, result, Toast.LENGTH_LONG).show();
//call super.setReponse
//obj.setResponse(result);		
	Log.e("TAKE NOTE OF ME", result);
//		TextView myText = new TextView(appContext);
//		myText.setText("My Text");
//
//		lView.addView(myText);
//	Toast.makeText(appContext, result, Toast.LENGTH_LONG).show();
	}
	
	
}
