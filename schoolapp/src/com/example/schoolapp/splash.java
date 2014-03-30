package com.example.schoolapp;

import library.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class splash extends ActionBarActivity{
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		/*
		 * Intialising all tables
		 */
		
		//setting action bar title and background
		getSupportActionBar().setTitle("Hashtagkid");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected))); 

		  int apino =Integer.valueOf(android.os.Build.VERSION.SDK);
		  if(apino>=11)
		  {
			//setting action bar title font
				int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
			
				TextView actionBarTitleView = (TextView) findViewById(actionBarTitle);
				  Typeface typeface = Typeface.createFromAsset(actionBarTitleView.getContext().getAssets(), actionBarTitleView.getContext().getString(R.string.fontname));
				  if(actionBarTitleView != null){
				      actionBarTitleView.setTypeface(typeface);
				  }
		  }
    
		
		DatabaseHandler db = new DatabaseHandler(this);
				
		String[] tableList=DatabaseHandler.tableList;
		
		
		
		if(db.getCount()==0)
		{
			db.setup(tableList);
			db.setDefaultID();
			db.updateCount();
		}
		
		
		
		TextView school=(TextView) findViewById(R.id.signin);
		Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
		school.setTypeface(tf);
		
		school.setClickable(true);
		final Intent intent1 = new Intent(this, Home.class);
		school.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(intent1);
			}
		});
		
	}
	
	
}

