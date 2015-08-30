package com.geoteam.geostory;

import java.io.File;

import org.json.JSONObject;


public interface ServerCommunicatorListener {
	public void onServerJSONResponse(final JSONObject serverResponse);
	public void onServerImageResponse(final File serverResponse);

}