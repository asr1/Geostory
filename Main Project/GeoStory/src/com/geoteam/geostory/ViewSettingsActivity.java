package com.geoteam.geostory;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSettingsActivity extends ActionBarActivity {

	public int proximity = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

    
	final TextView t1=(TextView)findViewById(R.id.textView2); 
     
	final SeekBar sk=(SeekBar) findViewById(R.id.seekBar1);     
	sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {       

	    @Override       
	    public void onStopTrackingTouch(SeekBar seekBar) {      
	        // TODO Auto-generated method stub      
	    }       

	    @Override       
	    public void onStartTrackingTouch(SeekBar seekBar) {     
	        // TODO Auto-generated method stub      
	    }       

	    @Override       
	    public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {     
	        // TODO Auto-generated method stub      
	        t1.setText("" + proximity + " meters");
	    	proximity = progress;

	    }       
	});   
	}
}
