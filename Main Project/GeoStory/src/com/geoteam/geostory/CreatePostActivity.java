package com.geoteam.geostory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePostActivity extends ActionBarActivity implements
		ServerCommunicatorListener {

	// view objects
	private TextView newStoryMesssage;
	private Button newStoryButton;
	public EditText storyText;

	private ImageView iV;
	private ImageView iV2;

	private static byte[] imageToSend = null; // TODO unused?

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	// variables for gallery select
	private static final int SELECT_PICTURE = 1;

	private static boolean isFull = false;

	public static final int MEDIA_TYPE_IMAGE = 1;

	// The preview of the picture that is taken

	/**
	 * onCreate is called when the activity is started
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);

		newStoryButton = (Button) findViewById(R.id.newStoryButton);
		newStoryButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText newStoryText = (EditText) findViewById(R.id.newStoryText);
				EditText newStoryTitle = (EditText) findViewById(R.id.newStoryTitle);
				GeoStory newStory = new GeoStory(newStoryTitle.getText()
						.toString());

				newStory.setLatitude(LocationServices.getLocation(false));
				newStory.setLongitude(LocationServices.getLocation(true));

				newStory.setText(newStoryText.getText().toString());
				newStory.setPhotoByteArray(imageToSend); // accepts null
															// currently

				// create and register to communicator
				AsyncServerCommuncator communicator = new AsyncServerCommuncator();
				communicator.registerListener(CreatePostActivity.this);

				// execute.
				communicator.executeCreateStoryAction(newStory);

			}
		});

	}

	/**
	 * imageFull toggles if the image is enlarged.
	 * 
	 * @param v
	 */
	public void imageFull(View v) {
		if (isFull == false) {
			iV2.setVisibility(View.VISIBLE);
			isFull = true;
		} else {
			iV2.setVisibility(View.INVISIBLE);
			isFull = false;
		}
	}

	/**
	 * this function launches the camera activity.
	 * 
	 * @param v
	 */
	public void gotoCamera(View v) {

		// create Intent to take a picture and return control to the calling
		// application
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// start the image capture Intent
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		Preview();

	}

	/**
	 * this function launches the gallery activity. select image from device
	 * 
	 * @param v
	 */
	public void gotoGallery(View v) {

		Intent intent = new Intent();// (Intent.ACTION_PICK);
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_PICK);
		// startActivityForResult(intent, SELECT_PICTURE);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				SELECT_PICTURE);
		Preview();
	}

	/**
	 * method that sets iV to the imageview to show up on the screen
	 */
	public void Preview() {
		iV = (ImageView) findViewById(R.id.cameraPreviews);
		iV2 = (ImageView) findViewById(R.id.cameraPreviewsFull);
		iV2.setVisibility(View.INVISIBLE);
	}

	/**
	 * This function is called when the gallery or camera activty return
	 */
	@SuppressLint("NewApi")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// switch case to determine how the photo was selected
		switch (requestCode) {
		case (CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE):

			try {

				if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE
						&& resultCode == RESULT_OK) {
					Bundle extras = data.getExtras();
					// get the photo
					Bitmap photo = (Bitmap) extras.get("data");

					photo = Bitmap.createScaledBitmap(photo, 600, 900, true);

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					imageToSend = stream.toByteArray();

					// set image to the screen
					iV.setImageBitmap(photo);
					iV2.setImageBitmap(photo);
				}
			} catch (Exception e) {
				// just use a test image
				try {
					AssetManager am = getResources().getAssets();
					InputStream is;

					is = am.open("test.jpg");

					// Create the byte array to hold the data
					byte[] bytes = new byte[0xFFFF];

					// Read in the bytes
					int offset = 0;
					int numRead = 0;
					while (offset < bytes.length
							&& (numRead = is.read(bytes, offset, bytes.length
									- offset)) >= 0) {
						offset += numRead;
					}

					// Close the input stream and return bytes
					is.close();
					imageToSend = bytes;

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		// photo selected from device
		case (SELECT_PICTURE):
			if (resultCode == RESULT_OK) {
				if (requestCode == SELECT_PICTURE) {
					Uri selectedImageUri = data.getData();

					String[] filePathColumn = { MediaStore.Images.Media.DATA };

					Cursor cursor = getContentResolver().query(
							selectedImageUri, filePathColumn, null, null, null);
					cursor.moveToFirst();

					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String filePath = cursor.getString(columnIndex);
					cursor.close();

					Bitmap photo = BitmapFactory.decodeFile(filePath);

					Matrix matrix = new Matrix();
					// setup rotation degree
					matrix.postRotate(180);
					Bitmap bmp = Bitmap.createBitmap(photo, 0, 0,
							photo.getWidth(), photo.getHeight(), matrix, true);
					photo = bmp;

					photo = Bitmap.createScaledBitmap(photo, 600, 900, true);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					imageToSend = stream.toByteArray();
					iV.setImageBitmap(photo);
					iV2.setImageBitmap(photo);
					iV2.setVisibility(View.INVISIBLE);
				}
			}

			break;
		}
	}

	
	/**
	 * this function is not used due to not using actionbar activity
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_post, menu);
		return true;
	}

	
	/**
	 * this function is not used due to not using actionbar activity
	 */
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

	// listener function
	@Override
	public void onServerJSONResponse(final JSONObject serverResponse) {
		
		//new story callback was triggered.
		Log.i("new story response", serverResponse.toString());
		final GeoStory newStoryFromServer = AsyncServerCommuncator
				.interpretCreateStoryAction(serverResponse);

		runOnUiThread(new Runnable() {
			@Override
			public void run() {

			}
		});

		Intent i = new Intent(CreatePostActivity.this, ViewStoryActivity.class);
		i.putExtra("storyId", newStoryFromServer.getId());
		startActivity(i);
		this.finish();

		
		
	}

	
	/**
	 * This function is not used do to this activity not downloading images.
	 */
	@Override
	public void onServerImageResponse(File serverResponse) {
		// DO NOTHING, THIS ACTIVITY DOES NOT DOWNLOAD IMAGES
		// TODO Auto-generated method stub

	}

}
