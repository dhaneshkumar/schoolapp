package com.example.schoolapp;

import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactList extends commonDrawer implements View.OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.contactlist, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	    //setting action bar title
	    getSupportActionBar().setTitle("Contact");
	    
	    //setting background color of action bar
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
	    
	    Button teacher =  ( Button) findViewById(R.id.teacher);
	    Button admin =  ( Button) findViewById(R.id.administration);
	    Button auth =  ( Button) findViewById(R.id.authority);
	    Button others =  ( Button) findViewById(R.id.others);
	    
	    teacher.setOnClickListener(this);
        admin.setOnClickListener(this);
        auth.setOnClickListener(this);
        others.setOnClickListener(this);
        
        Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
        teacher.setTypeface(tf);
        admin.setTypeface(tf);
        auth.setTypeface(tf);
        others.setTypeface(tf);
	    
	}
	
	
	@Override
	   public void onClick(View v) {
		Intent myIntent = new Intent(ContactList.this, PhoneList.class);
	       switch(v.getId()) {
	           case R.id.teacher:
	        	   myIntent.putExtra("tag", "Teacher");
	           break;
	           
	           case R.id.administration:
	        	   myIntent.putExtra("tag", "Administration");
	           break;
	           
	           case R.id.authority:
	        	   myIntent.putExtra("tag", "Authority");
	           break;
	           
	           case R.id.others:
	        	   myIntent.putExtra("tag", "Others");
	           break;
	           
	       }
	       
	       startActivity(myIntent);
	}

	public void onBackPressed() {
		   Intent intent = new Intent(this, Home.class);
		   startActivity(intent);
		 }
}
