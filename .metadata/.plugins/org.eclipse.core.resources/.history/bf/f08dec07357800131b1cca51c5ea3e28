package com.trumplabs.ptm;

import android.app.Activity;
import android.os.Bundle;
import library.DatabaseHandler;
import library.UserFunctions;
import android.widget.TextView;

public class DashboardActivity extends Activity{
	TextView t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash);
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
		
		t = (TextView)findViewById(R.id.text_dash);
		
		UserFunctions u = new UserFunctions();
		
		DatabaseHandler d = new DatabaseHandler(this);
	}
}