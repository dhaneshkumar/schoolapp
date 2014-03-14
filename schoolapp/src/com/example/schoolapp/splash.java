package com.example.schoolapp;

import library.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class splash extends Activity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		/*
		 * Intialising all tables
		 */
		
		
		DatabaseHandler db = new DatabaseHandler(this);
				
		String[] tableList=DatabaseHandler.tableList;
		db.setup(tableList);
		
		
		
		//Delaying for 1 sec.
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(splash.this, ContactList.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        }, 1000);		
	}
}

