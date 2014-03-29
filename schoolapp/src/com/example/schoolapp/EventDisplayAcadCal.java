package com.example.schoolapp;

import library.utils;
import nav_drawer.commonDrawer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class EventDisplayAcadCal extends commonDrawer {
TextView title;
TextView start_date;
TextView start_time;
TextView end_date;
TextView end_time;
TextView description;
TextView special_guest;
TextView venue;
TextView extra_details;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.event_display);
		
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.event_display, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	  //setting action bar title
	    getSupportActionBar().setTitle("Contact");
	    
	    //setting background color of action bar
	    getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected)));
	    
	  //setting action bar title font
	  		int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
	  		  TextView actionBarTitleView = (TextView) findViewById(actionBarTitle);
	  		  Typeface typeface = Typeface.createFromAsset(actionBarTitleView.getContext().getAssets(), actionBarTitleView.getContext().getString(R.string.fontname));
	  		  if(actionBarTitleView != null){
	  		      actionBarTitleView.setTypeface(typeface);
	  		  }
	    
	    
	    utils ul =new utils();
	    
		 Intent intent = getIntent();
		  String[] myStrings = intent.getStringArrayExtra("firstKeyName");
		  title=(TextView)findViewById(R.id.title_txt);
		  title.setText(myStrings[0]);
		  start_date=(TextView)findViewById(R.id.start_date_txt);
		  start_date.setText(myStrings[1]);
		  start_time=(TextView)findViewById(R.id.start_time_txt);
		  start_time.setText(myStrings[2]);
		  end_date=(TextView)findViewById(R.id.end_date_txt);
		  end_date.setText(myStrings[3]);
		 end_time=(TextView)findViewById(R.id.end_time_txt);
		 end_time.setText(myStrings[4]);
		 description=(TextView)findViewById(R.id.description_txt);
		 description.setText(myStrings[5]);
		 special_guest=(TextView)findViewById(R.id.special_guest_txt);
		 special_guest.setText(myStrings[6]);
		 venue=(TextView)findViewById(R.id.venue_txt);
		 venue.setText("Venue : " + myStrings[7]);
		 extra_details=(TextView)findViewById(R.id.extra_details_txt);
		 extra_details.setText(myStrings[8]);
		 
		 ul.setFont(title);
		 ul.setFont(start_date);
		 ul.setFont(start_time);
		 ul.setFont(end_date);
		 ul.setFont(end_time);
		 ul.setFont(description);
		 ul.setFont(venue);
	}

	

}
