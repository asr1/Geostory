package com.example.jsontest3;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendJsonToServer extends AsyncTask<JSONObject, Void, String> {

	private Context appContext;
	
	public SendJsonToServer(Context context)
	{
		appContext = context;
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
		
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 10000);
			HttpClient client = new DefaultHttpClient(httpParams);
			HttpPost request = new HttpPost("http://proj-309-f11.cs.iastate.edu/geostory");
			try {
				request.setEntity(new ByteArrayEntity(params.toString().getBytes("UTF-8")));
				HttpResponse  response = client.execute(request);

				String ResponseBody = getResponseBody(response);
				return ResponseBody;
			}
			catch (Exception e)
			{
				Log.e("ERROR", e.getStackTrace()[0].toString());
				return "failure";
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
	}
	
	
}
