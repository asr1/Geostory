package com.geoteam.geostory;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	
	private String name;
	private int id;
	private boolean anonymous;

	public User(String name, int id) {
		super();
		this.name = name;
		this.id = id;
		
		this.anonymous = (this.name == "anonymous") || (this.name.equals("testUser@proj-309-f11.cs.iastate.edu"));
	}
	
	public User(){
		this("anonymous",-1);
	
	}
	public User(JSONObject json){
		
		try {
			this.name = json.getString("email");
			this.id = Integer.parseInt(json.getString("id"));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
				
		this.anonymous = (this.name == "anonymous") || (this.name.equals("testUser@proj-309-f11.cs.iastate.edu"));
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	boolean isAnonymous(){
		return this.anonymous;
	}
	
	
	
}
