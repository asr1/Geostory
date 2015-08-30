package GeoStory;

import java.util.Date;

public class GeoStory {

	private int id;
	private int accountId;
	private String title;
	private double latitude;
	private double longitude;
	private Date dateCreated;
	private String photoFile;
	private boolean deleted = false;
	private double cachedRating;
	private String text;
	
	//without image
	public GeoStory(String title)
	{
		this.title = title;
	}
	
	//setters
	public void setID(int id)
	{
		this.id = id;
	}
	public void setAccountID(int accountId)
	{
		this.accountId = accountId;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public void setPhotoFile(String photoFile)
	{
		this.photoFile = photoFile;
	}
	public void delete()
	{
		this.deleted = true;
	}
	public void undelete()
	{
		this.deleted = false;
	}
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}
	
	public void setCachedRating(double cachedRating)
	{
		this.cachedRating = cachedRating;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	
	//getters
	public int getId()
	{
		return id;
	}
	public int getAccountId()
	{
		return accountId;
	}
	public String getTitle()
	{
		return title;
	}
	public String getPhotoFile()
	{
		return photoFile;
	}
	public boolean isDeleted()
	{
		return deleted;
	}
	public double getLatitude()
	{
		return latitude;
	}
	public double getLongitude()
	{
		return longitude;
	}
	public Date getTimeCreated()
	{
		return dateCreated;
	}
	public double getCachedRating()
	{
		return cachedRating;
	}
	public String getText(String text)
	{
		return text;
	}
	
}


