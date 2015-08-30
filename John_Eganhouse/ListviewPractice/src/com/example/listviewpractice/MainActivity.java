package com.example.listviewpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        ListView listView1 = (ListView) findViewById(R.id.listView1);
       
        GeoStory geo1 = new GeoStory("GeoTest1");
        GeoStory geo2 = new GeoStory("GeoTest2");
        GeoStory geo3 = new GeoStory("GeoTest3");
        GeoStory geo4 = new GeoStory("GeoTest4");
        GeoStory geo5 = new GeoStory("GeoTest5");
        GeoStory[] items = {
        		geo1,
        		geo2,
        		geo3,
        		geo4,
        		geo5
        };
       
        
        ArrayAdapter<GeoStory> adapter = new ArrayAdapter<GeoStory>(this,
                    android.R.layout.simple_list_item_1, items);
       
        listView1.setAdapter(adapter);
    }
}
