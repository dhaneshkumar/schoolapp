package com.example.schoolapp;

import library.utils;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import nav_drawer.commonDrawer;

public class Messages extends commonDrawer{
	 	TextView name;
	    TextView date;
	    TextView details;
	    TextView sender;
	    
	    TextView adate;
	    TextView adetails;
	    TextView atitle;
	    TextView asender;
		
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.message, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	    //setting action bar title
	    getSupportActionBar().setTitle("Messages");
	    getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected)));  
	  //setting action bar title font
  		int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
  		  TextView actionBarTitleView = (TextView) findViewById(actionBarTitle);
  		  Typeface typeface = Typeface.createFromAsset(actionBarTitleView.getContext().getAssets(), actionBarTitleView.getContext().getString(R.string.fontname));
  		  if(actionBarTitleView != null){
  		      actionBarTitleView.setTypeface(typeface);
  		  }
    
	    
	    LinearLayout l1 = (LinearLayout ) findViewById(R.id.l1);
	    LinearLayout l2 = (LinearLayout ) findViewById(R.id.l2);
	    
	    l1.setClickable(true);
	    l2.setClickable(true);
	    
	    Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
	    
	     name=(TextView) findViewById(R.id.attitle1);
	     date=(TextView) findViewById(R.id.adate1);
	     details=(TextView) findViewById(R.id.adetails1);
	     sender=(TextView) findViewById(R.id.asender1);
	    
	     adate=(TextView) findViewById(R.id.adate);
	     adetails=(TextView) findViewById(R.id.adetails);
	     atitle=(TextView) findViewById(R.id.attitle);
	     asender=(TextView) findViewById(R.id.asender);
	    utils.ls("entered in home page4");
		
	    name.setTypeface(tf);
		date.setTypeface(tf);
		details.setTypeface(tf);
		sender.setTypeface(tf);
		
		atitle.setTypeface(tf);
		adate.setTypeface(tf);
		adetails.setTypeface(tf);
		asender.setTypeface(tf);
	    
		 l1.setOnClickListener( new View.OnClickListener(){
	            @Override
	            public void onClick(View v) {
	            	
	            //    Toast.makeText(getApplicationContext(), parts[1] + "+" + parts[4], Toast.LENGTH_SHORT).show();
	                Intent i=new Intent(getApplicationContext(), messagebox.class);
	                i.putExtra("name", name.getText());
	                i.putExtra("date", date.getText());
	                i.putExtra("details", details.getText());
	                i.putExtra("sender", sender.getText());
	                i.putExtra("image", "1");

	                startActivity(i);
	            }
	        });
	    
		 l2.setOnClickListener( new View.OnClickListener(){
	            @Override
	            public void onClick(View v) {
	            	
	            //    Toast.makeText(getApplicationContext(), parts[1] + "+" + parts[4], Toast.LENGTH_SHORT).show();
	                Intent i=new Intent(getApplicationContext(), messagebox.class);
	                i.putExtra("name", atitle.getText());
	                i.putExtra("date", adate.getText());
	                i.putExtra("details", adetails.getText());
	                i.putExtra("sender", asender.getText());
	                i.putExtra("image", "2");

	                startActivity(i);
	            }
	        });
	    
	}
	
	public void onBackPressed() {
		   Intent intent = new Intent(this, Home.class);
		   startActivity(intent);
		 }

}
