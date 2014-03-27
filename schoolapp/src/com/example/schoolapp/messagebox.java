package com.example.schoolapp;

import library.utils;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import nav_drawer.commonDrawer;

public class messagebox extends commonDrawer{
		
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.messagebox, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	    //setting action bar title
	    getSupportActionBar().setTitle("Messages");
	    getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected)));  
	    
	    Intent intent = getIntent();
	    
	    
	    Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
	    
	    TextView name=(TextView) findViewById(R.id.attitle1);
	    TextView date=(TextView) findViewById(R.id.adate1);
	    TextView details=(TextView) findViewById(R.id.adetails1);
	    TextView sender=(TextView) findViewById(R.id.asender1);
	    ImageView img =(ImageView) findViewById(R.id.attendanceimg1);
	  
	    name.setTypeface(tf);
		date.setTypeface(tf);
		details.setTypeface(tf);
		sender.setTypeface(tf);
		
		
		name.setText(intent.getStringExtra("name"));
		date.setText(intent.getStringExtra("date"));
		details.setText(intent.getStringExtra("details"));
		sender.setText(intent.getStringExtra("sender"));
		
		if(intent.getStringExtra("image").equals("1"))
		{
			img.setImageDrawable(getResources().getDrawable(R.drawable.jaideep100));
		}
		else if(intent.getStringExtra("image").equals("2"))
		{
			img.setImageDrawable(getResources().getDrawable(R.drawable.ab100));
		}
		else
			img.setImageDrawable(getResources().getDrawable(R.drawable.application));
		
	    
	    
	    
	}
	
	
}
