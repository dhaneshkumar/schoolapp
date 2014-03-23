package com.example.schoolapp;

import java.util.HashMap;

import library.DatabaseHandler;
import library.utils;
import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class profile_student extends commonDrawer {
	TextView name;
	TextView clas;
	TextView school;
	TextView parents;
	TextView schoolname;
	TextView section;
	TextView classteacher;
	TextView noofstudents;
	TextView address;
	TextView contactdetails;
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// setting the layout
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(R.layout.profile_student, null,
				false);
		mDrawerLayout.addView(contentView, 0);
		
		
		
		//Setting action bar title and background color
		getSupportActionBar().setTitle("Student Profile");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(Color.parseColor("#58a533"))); 
		
		Typeface tf= Typeface.createFromAsset(getAssets(), "museo-300.ttf");
		utils.ls("Entered in student profile");
		name=(TextView) findViewById(R.id.name);
		clas=(TextView) findViewById(R.id.clas);
		school=(TextView) findViewById(R.id.school);
		parents=(TextView) findViewById(R.id.parents);
		schoolname=(TextView) findViewById(R.id.schoolname);
		section = (TextView) findViewById(R.id.section);
		ImageView iw=(ImageView) findViewById(R.id.imgView);
		classteacher=(TextView) findViewById(R.id.classteacher);
		noofstudents=(TextView) findViewById(R.id.noofstudents);
		address=(TextView) findViewById(R.id.address);
		contactdetails=(TextView) findViewById(R.id.contactdetails);
		
		
		name.setTypeface(tf);
		clas.setTypeface(tf);
		school.setTypeface(tf);
		parents.setTypeface(tf);
		schoolname.setTypeface(tf);
		section.setTypeface(tf);
		classteacher.setTypeface(tf);
		noofstudents.setTypeface(tf);
		address.setTypeface(tf);
		contactdetails.setTypeface(tf);
		
		
	//	Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab100);
	//	Bitmap cmap = utils.getRoundedShape(bMap);
	//	iw.setImageBitmap(cmap);
		
		HashMap<String, String> userDetails = new HashMap<String, String>();
		DatabaseHandler db = new DatabaseHandler(this);
		userDetails = db.getStudentProfile();
		
		
		name.setText(userDetails.get("name"));
		clas.setText( "Class : " + userDetails.get("class"));
		schoolname.setText(userDetails.get("schoolname"));
		section.setText("Section : "+userDetails.get("section"));
		classteacher.setText("Class Teacher : " + userDetails.get("classteacher"));
		noofstudents.setText("No of Students : " + userDetails.get("noofstudents"));
		address.setText("Address : " + userDetails.get("address"));
		
		//forwarding to contact list
		contactdetails.setClickable(true);
		final Intent intent = new Intent(this, ContactList.class);
		contactdetails.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	startActivity(intent);
            }
        });
		
		//forwarding to parent page
		parents.setClickable(true);
		final Intent intent1 = new Intent(this, profile_parent.class);
		intent1.putExtra("name",userDetails.get("name") );
		parents.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				startActivity(intent1);
			}
		});

	}

}
