package com.example.schoolapp;

import library.utils;
import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class settings_page extends commonDrawer implements OnClickListener{
	TextView mTextView1 ;
	TextView mTextView2 ;
	TextView mTextView3 ;
	TextView mTextView4 ;
	TextView mTextView5 ;
	TextView mTextView6 ;
	ImageView notificationImg;
	ImageView gradesImg;
	ImageView eventsImg;
	ImageView blogImg;
	ImageView dailyattendanceImg;
	String notice = "Set notifications ON";
	
	int notificationStatus = 0;
	int gradesStatus = 0;
	int eventsStatus = 0;
	int blogStatus = 0;
	int dailyattendanceStatus = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(R.layout.settings, null,
				false);
		mDrawerLayout.addView(contentView, 0);
		
		utils.ls("1111");
		
		getSupportActionBar().setTitle("Settings");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected)));  
		
		utils.ls("Entered in setting page  : ---");
		Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
		utils.ls("2222");
		utils.ls("Entered in setting page  : ---2222");
		 mTextView2 =(TextView) findViewById(R.id.blog_tx);
		 mTextView3 =(TextView) findViewById(R.id.notification_tx);
		 mTextView4 =(TextView) findViewById(R.id.event_tx);
		 mTextView5 =(TextView) findViewById(R.id.daily_attendance_tx);
		 mTextView6 =(TextView) findViewById(R.id.grades_tx);

		 notificationImg = (ImageView) findViewById(R.id.notifications);
		 gradesImg = (ImageView) findViewById(R.id.grades);
		 eventsImg = (ImageView) findViewById(R.id.events);
		 blogImg = (ImageView) findViewById(R.id.blog);
		 dailyattendanceImg = (ImageView) findViewById(R.id.dailyattendance);
		 
		 notificationImg.setOnClickListener(this); 
		 gradesImg.setOnClickListener(this);
		 eventsImg.setOnClickListener(this);
		 blogImg.setOnClickListener(this);
		 dailyattendanceImg.setOnClickListener(this);
		 
			 mTextView2.setTypeface(tf);
			 mTextView3.setTypeface(tf);
			 mTextView4.setTypeface(tf);
			 mTextView5.setTypeface(tf);
			 mTextView6.setTypeface(tf);
			 
			 utils.ls("Entered in setting page  : ---66665");		 
				
	}

	public void onBackPressed() {
		   Intent intent = new Intent(this, Home.class);
		   startActivity(intent);
		 }

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
        case R.id.notifications :
        	if(notificationStatus==0)
        	{
        		notificationStatus=1;
        		gradesStatus=1;
        		blogStatus=1;
        		eventsStatus=1;
        		dailyattendanceStatus=1;
        		notificationImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		gradesImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		blogImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		eventsImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		dailyattendanceImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        	}
        	else
        	{
        		notificationStatus=0;
        		gradesStatus=0;
        		blogStatus=0;
        		eventsStatus=0;
        		dailyattendanceStatus=0;
        		notificationImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        		gradesImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        		blogImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        		eventsImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        		dailyattendanceImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        	}
        	break;
        	
        case R.id.grades :
        	if(gradesStatus==0){
        		if(notificationStatus==1)
        		{
        			gradesStatus=1;
        			gradesImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        	}
    		else
        			Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
    	
        	}
        	else
        	{
        		gradesStatus=0;
        		gradesImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        	}
        	break;
        	
        case R.id.events :
        	if(eventsStatus==0){
        		if(notificationStatus==1)
        		{
        			eventsStatus=1;
        			eventsImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		}
        		else
            			Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
        	}
        	else
        	{
        		eventsStatus=0;
        		eventsImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        	}
        	break;
        	
        case R.id.blog :
        	if(blogStatus==0){
        		if(notificationStatus==1)
        		{
        			blogStatus=1;
        			blogImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		}
        		else
            			Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
        	}
        	else
        	{
        		blogStatus=0;
        		blogImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        	}
        	break;
        	
        case R.id.dailyattendance :
        	if(dailyattendanceStatus==0){
        		if(notificationStatus==1)
        		{
        			dailyattendanceStatus=1;
        			dailyattendanceImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchon));
        		}
        		else
        			Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
        	}
        	else
        	{
        			dailyattendanceStatus=0;
        			dailyattendanceImg.setImageDrawable(v.getResources().getDrawable(R.drawable.switchoff));
        	}
        	break;
        	
        	
        	
		}
		
	}

	
}
