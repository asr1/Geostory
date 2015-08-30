package com.geoteam.geostory;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.File;


/**
 * 
 * This helper class contains the code for activities to talk with the server.
 * There is a simple 
 * 
 * 
 * steps to use:
 * 1. In main Activity -> register listener()
 * 		//register the callback function of the activity
 * 
 * 2. In main Activity -> execute[name]Action()
 * 		//Call the Server action
 *
 * 2.5 (sometimes) In main Activity -> getActionType()
 * 		//call getAction to find out which action is tringgering the callback
 *
 * 3. In Activities listener function -> interpret[name]Action() 
 * 		//convert the response to expected output
 * 
 * 
 * 
 * NOTE:
 * to add a new server function, all we need to do is implement 
 * a execute[Name]Action() and a interpret[Name]Action() function!
 * 
 * @author forrest
 *
 */
public class AsyncServerCommuncator {
	
	  /* Local ip forwarding for debuging
	  private static final String JSON_URL= "http://192.168.0.3/geostory/json";
	  private static final String IMAGE_URL = "http://192.168.0.3/img/si/";
	  private static final String TEST_URL = "http://192.168.0.3/geostory/echo";
	  private static final String LOGIN_URL = "http://192.168.0.3/user/login";
	  private static final String LOGOUT_URL = "http://192.168.0.3/user/logout";
	  */
	
	  private static final String JSON_URL= "http://proj-309-f11.cs.iastate.edu/geostory/json";
	  private static final String IMAGE_URL = "http://proj-309-f11.cs.iastate.edu/img/si/";
	  private static final String TEST_URL = "http://proj-309-f11.cs.iastate.edu/geostory/echo";
	  private static final String LOGIN_URL = "http://proj-309-f11.cs.iastate.edu/user/login";
	  private static final String LOGOUT_URL = "http://proj-309-f11.cs.iastate.edu/user/logout";
	
	  private static AsyncHttpClient client = new AsyncHttpClient();
	
	  //listener activity that we return a story to.
	  ServerCommunicatorListener listener;

	  
	  
	  
	  /**
	 * @param listener
	 * This method is used for passing the custom listener object 
	 * Throughout the application. This is used for location.
	 */
	public void registerListener(ServerCommunicatorListener listener){
	    	this.listener = listener;
	  }
	  
	  
	  
	/* START EXECUTE ACTION FUNCTIONS */
	
	
	 /**
	 * This method executes the downloadImage interface.
	 * The callback onServerImageResponse() will be triggered
	 * @param image
	 */
	public void downloadImage(String image){
		  getImage(image);
		  
	  }
	
	  
	/**
	 * This function executes the login action
	 * 
	 * @param account - email account
	 * @param password - account password
	 */
	public void executeLoginAction(String account, String password) {
		client.setEnableRedirects(true, true, true);
		RequestParams postParams = new RequestParams();
		postParams.put("identity", account);
		postParams.put("credential", password);
		
		//initiate custom server post
		client.post(LOGIN_URL, postParams, new AsyncHttpResponseHandler() {

			
			/**
			 * onFailure is called when there is an invalid account password combo, or
			 * anything else fails
			 */
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				try {

					Log.i("error", error.getMessage());
					Log.i("login fail responseBody", ""
							+ new String(responseBody));
				} catch (Exception e) {

				}

				//create a custom JSONObject to remain consistent with the other actions
				JSONObject response = new JSONObject();

				try {
					response.put("success", "false");
					response.put("action", "loginAction");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				listener.onServerJSONResponse(response);

			}

			/**
			 * onSuccess triggers if an account is already logged in or the login
			 *  was successful
			 */
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {

			
				//create a custom JSONObject to remain consistent with the other actions
				JSONObject response = new JSONObject();
				String responseString = new String(responseBody);
				if (responseString.contains("Hello,")) {
					try {
						response.put("success", "true");
						response.put("action", "loginAction");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.put("success", "false");
						response.put("action", "loginAction");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				listener.onServerJSONResponse(response);
			}

		});
	}
	
	/**
	 * This function executes the Get current user action.
	 * It is useful for checking if the app already has a session with the server.
	 * The server handles the logic
	 * 
	 */
	public void executeGetCurrentUserAction(){
		RequestParams postParams = new RequestParams();
		postParams.put("action", "getSessionInfo");
		postParams.setUseJsonStreamer(true);
		post(postParams);
	}
	
	/**
	 * This function executes the set rating action.
	 * It uses the current user, a given rating, and a giving storyId
	 * to create a rating between one user and one story.
	 * 
	 * The server handles the logic
	 * 
	 * @param rating
	 * @param storyID
	 */
	public void executeSetRatingAction(int rating, int storyID){
		RequestParams postParams = new RequestParams();
		postParams.put("action","setStoryRating");
		postParams.put("rating","" + rating);
		postParams.put("storyId","" + storyID);
		postParams.setUseJsonStreamer(true);
		post(postParams);
	}
	
	
	
	/**
	 * This function handles the execute logout Action
	 * The server will logout the current session.
	 * 
	 * The server handles the logic
	 * 
	 */
	public void executeLogoutAction() {
		//enable redirects to prevent asyncHttpResponse breaking when the server redirects after logout.
		client.setEnableRedirects(true, true, true);
		
		//custom post function
		client.post(LOGOUT_URL, new AsyncHttpResponseHandler() {
			
			/**
			 * onFailure is rarely called, and is often ignored even what it does.
			 */
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
			{
				Log.e("logout failed",
						"Async login onFailure function 1 was called. status" + statusCode);
				JSONObject response = new JSONObject();
				try {
					response.put("success", "false");
					response.put("action", "logoutAction");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listener.onServerJSONResponse(response);
				
			}
			
			/**
			 * onSuccess is called when the logout was sucessful.
			 */
			 @Override
		     public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				 
					JSONObject response = new JSONObject();
					try {
						response.put("success", "true");
						response.put("action", "logoutAction");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listener.onServerJSONResponse(response);
				 
			 }
		
		});
		
	}
	
	
	
	/**
	 * This function executes the get stories by email action
	 * It takes a given email (currentUser) and callsback a list of stories by the email.
	 * @param email
	 */
	public void executeGetStoriesByEmailAction(String email){
		
		RequestParams postParams = new RequestParams();
		postParams.put("action","getStoriesByEmail");
		postParams.put("email",email);
		
		postParams.setUseJsonStreamer(true);
		post(postParams);
		
		
	}
	
	
	/**
	 * 
	 * @param newStory
	 *            This method takes in a story and sends a request to the Server
	 *            to make and register a new Story object with the given story
	 *            as a default, along with the current location.
	 */
	  public void executeCreateStoryAction(GeoStory newStory){
		 
			RequestParams postParams = new RequestParams();
			postParams.put("action", "newStory");
			postParams.put("title",newStory.getTitle());
			postParams.put("storyText", newStory.getText());
			postParams.put("latitude", "" + newStory.getLatitude());
			postParams.put("longitude", "" + newStory.getLongitude());
			if (newStory.hasPhotoDownloaded())
			{
				postParams.put("image", newStory.getImageBase64());
			}
			
			postParams.setUseJsonStreamer(true);
			post(postParams);
			
	  }
	  
	  
	  /**
	   * This method asks the server for an arrraylist of stories
	   * That are within the specified distance (100m default) 
	   * Of the user. These stories are added to the screen.
	   */
	  public void executeGetStoriesByDistanceyAction(){
		  //TODO
			RequestParams postParams = new RequestParams();
			postParams.put("action", "getStoriesByDistance");
			postParams.put("latitude", LocationServices.getLocation(false));
			postParams.put("longitude", LocationServices.getLocation(true));
			postParams.put("distance", "100");
			
			postParams.setUseJsonStreamer(true);

			post(postParams);

	  }
	 
	  /**
	   * Given an ID, this method displays on screen the 
	   * Story on the server with the corresponding ID.
	   * @param id
	   */
	  public void executeGetStoryByIdAction(int id){
		  RequestParams postParams = new RequestParams();
		  postParams.put("action", "getStoryById");
		  postParams.put("id", "" + id);
		  postParams.setUseJsonStreamer(true);
		  post(postParams);
	  }
	  
	  
	  
	 /**
	  * This function looks at the characteristics of the response to determine 
	  * the appropriate action. This info can be used by the callie action to
	  * user the correct interpreter.
	  * 
	 * @param response
	 * @return actionName as String
	 */
	public static String getActionType(JSONObject response){
		  
		  try {
			  return response.getString("action");
		  } catch (JSONException e) {
			  return "unknown, action's type not set";
		}
		  
	  }
	  
	/*
	  /* START INTERPRET  ACTION FUNCTIONS 
	 // The interpreter functions take data from the server
	 // And turn it into meaningful results (screen display, etc)
	*/
	
	/**
	 * This function interprets the create story action.
	 * returns null if the there was a failure.
	 * 
	 * @param response
	 * @return updatedGeostory.
	 * 			
	 */
	  public static GeoStory interpretCreateStoryAction(JSONObject response){
		try {
			//TODO: check for any errors?
			return new GeoStory(response.getJSONObject("newStory"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	  }
	  
	  
	  
	  /**
	   * This function interprets the login action
	   * returns false if there was a failure
	 * @param response
	 * @return
	 */
	public static boolean interpretLoginAction(JSONObject response){
		try {
			return (response.get("success").equals("true"));
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	  }
	  
	  
	 /**
	  * This function interprets the get current user action
	  * returns an empty user if there was a failure.
	 * @param response
	 * @return currentUser User
	 */
	public static User interpretGetCurrentUserAction(JSONObject response){

		try {
			return new User(response.getJSONObject("user"));
		} catch (JSONException e) {
			return new User();
		}

	  }
	  
	 
	  
	  
	  /**
	   * This function interprets get story by id action.
	   * returns null if there was a failure.
	   * @param response
	   * @return geostory
	   */
	  public static GeoStory interpretGetStoryByIdAction(JSONObject response){
		 //note: does not process image
		//TODO: check for any errors?
		try {
			return new GeoStory(response.getJSONObject("story"));
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		 
	  }
	  
	  //Interpreter for getStoryByDistance
	  
	  
	  /**
	   * this function interprets the get-story-by-distance action.
	   * returns empty list if no stories found.
	   * @param response
	   * @return
	   */
	  public static ArrayList<GeoStory> interpretGetStoryByDistanceAction(JSONObject response){
		  Log.i("stories list",response.toString());
		  ArrayList<GeoStory> ret = new ArrayList<GeoStory>();
		  try {
			
			JSONArray stories = response.getJSONArray("stories");
			for ( int i = 0; i < stories.length(); i++)
			{
				ret.add(new GeoStory((JSONObject)stories.get(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		  return ret;
	  }

	  
	  /**
	   * This function interprets the get-stories-by-email action.
	   * returns an empty list of no stories found.
	   * @param response
	   * @return
	   */
	  public static ArrayList<GeoStory> interpretGetStoriesByEmailAction(JSONObject response){;
		  ArrayList<GeoStory> ret = new ArrayList<GeoStory>();
		  try {
			
			JSONArray stories = response.getJSONArray("stories");
			for ( int i = 0; i < stories.length(); i++)
			{
				ret.add(new GeoStory((JSONObject)stories.get(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		  return ret;
	  }
		 

	  /* END INTERPRET ACTION FUNCTIONS */
	  
	  /**
	   * This function is for debug purposes only.
	   * @param params
	   */
//	  private void testParams(RequestParams params){
//		  client.post(TEST_URL, params, new JsonHttpResponseHandler(){
//				
//	            @Override
//	            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//
//	            	
//	            	//TODO: check for error codes within the JSON object
//	            	Log.i("JSON TEST",response.toString());
//	            }
//
//
//				@Override
//				public void onFailure(int statusCode, Header[] headers,
//						String responseString, Throwable throwable) {
//						
//					Log.e("json failed","Async JSON test failed");
//
//				}
//		  });  
//	  }
	  

	  /**
	   * This function is used privately to convert the 
	   * request params into server calls
	   * @param params
	   */
	  private void post(RequestParams params){
		  Log.i("Sending Action", params.toString());
		  client.post(JSON_URL, params, new JsonHttpResponseHandler(){
				
			  
			  /**
			   * onSuccess function. it triggers the onServerJSONReponse callback.
			   */
	            @Override
	            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

	            	
	            	//TODO: check for error codes within the JSON object
	            	try {
						if(response.getString("success").equals("false")){
							Log.e("JSON error!",response.toString());
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
	            	
	            	listener.onServerJSONResponse(response);
	            }


	            
	            /**
	             * onFailure functions
	             */
				@Override
				public void onFailure(int statusCode, Header[] headers,
						String responseString, Throwable throwable) {
					Log.e("json failed","Async JSON onFailure function 1 was called");

				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						Throwable throwable, JSONArray errorResponse) {
					Log.e("json failed","Async JSON onFailure function 2 was called");
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,
						Throwable throwable, JSONObject errorResponse) {
					Log.e("json failed","Async JSON onFailure function 3 was called");
				}


	            
	            
	        });
		  
		  
	  }
	  
	  /**
	   * This method gets an image from the server and passes it to the
	   * appropriate places within the app.
	   * @param url
	   */

	 private void getImage(String url){

		 client.get(IMAGE_URL + url, new FileAsyncHttpResponseHandler((Context) this.listener) {
			 
			 	
			    @Override
			    public void onSuccess(int statusCode, Header[] headers, File response) {
			        listener.onServerImageResponse(response);
			    }

				@Override
				public void onFailure(int arg0, Header[] arg1, Throwable arg2,
						File arg3) {
				}
			});
		 
	 }
	  
	  

	
}
