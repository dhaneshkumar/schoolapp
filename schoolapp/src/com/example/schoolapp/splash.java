package com.example.schoolapp;

import library.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class splash extends ActionBarActivity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		/*
		 * Intialising all tables
		 */
		
		getSupportActionBar().setTitle("Log In");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(Color.parseColor("#58a533"))); 
		
		DatabaseHandler db = new DatabaseHandler(this);
				
		String[] tableList=DatabaseHandler.tableList;
		db.setup(tableList);
		db.setDefaultID();
		
		TextView school=(TextView) findViewById(R.id.signin);
		Typeface tf= Typeface.createFromAsset(getAssets(), "museo-300.ttf");
		school.setTypeface(tf);
		
		school.setClickable(true);
		final Intent intent1 = new Intent(this, profile_student.class);
		school.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(intent1);
			}
		});
		
	}
}

