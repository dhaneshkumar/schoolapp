package com.example.schoolapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import library.DatabaseHandler;
import library.utils;
import nav_drawer.commonDrawer;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
	LinearLayout studentLayout;
	LinearLayout parentLayout;
	TextView addguardian;
	List<HashMap<String, String>> parentDetails;
	
	
	
	
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
      
		 
		
		
		
		TextView tt = (TextView)findViewById(R.id.title);
		
		Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
		
		
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
		studentLayout =(LinearLayout) findViewById(R.id.studentLayout);
		parentLayout =(LinearLayout) findViewById(R.id.parentLayout);
		//addguardian =(TextView) findViewById(R.id.addguardian);
		
		parentLayout.setVisibility(View.GONE);
		
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
		
		//retrieving student details from sqlite
		HashMap<String, String> userDetails = new HashMap<String, String>();
		DatabaseHandler db = new DatabaseHandler(this);
		userDetails = db.getStudentProfile();
		
		//Retrieving parent details
		parentDetails = new ArrayList<HashMap<String, String>>();
		parentDetails = db.getParentProfile();
		db.close();
		
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
		parents.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				showParent();
			}
		});
		
			
		school.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				school.setClickable(false);
				parents.setClickable(true);
				
				studentLayout.setVisibility(View.VISIBLE);
				parentLayout.setVisibility(View.GONE);
				
				school.setBackgroundResource(R.drawable.customshape);
				parents.setBackgroundResource(R.drawable.selectedshape);
				getSupportActionBar().setTitle("Student Profile");
			}
		});
	}

	public void showParent()
	{
		school.setClickable(true);
		parents.setClickable(false);
		school.setBackgroundResource(R.drawable.selectedshape);
		parents.setBackgroundResource(R.drawable.customshape);
		
		parentLayout.setVisibility(View.VISIBLE);
		studentLayout.setVisibility(View.GONE);
		
		getSupportActionBar().setTitle("Parent Profile");
		
		Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
		utils.ls("Entered in parent profile");
		//name=(TextView) findViewById(R.id.name);
	//	addguardian=(TextView) findViewById(R.id.addguardian);
		
		
		
		LinearLayout l1 = (LinearLayout) findViewById(R.id.layout1);
		l1.removeAllViews();
		
		int i=1;
		Iterator<HashMap<String, String>> k=parentDetails.iterator();
		while(k.hasNext())
		{
			
			// Creating a new RelativeLayout
	        RelativeLayout rl = new RelativeLayout(this);
	        rl.setId(i+2000);
	        // Defining the RelativeLayout layout parameters.
	        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
	        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        rlp.setMargins(0, 8, 0, 4);
	        //rlp.setMargins(left, top, right, bottom)
	        rl.setLayoutParams(rlp);
	        rl.setBackgroundColor(getResources().getColor(R.color.app_back_color));
	        l1.addView(rl);
	        
	        com.example.schoolapp.CircularImageView image = new com.example.schoolapp.CircularImageView(this);
	      //  ImageView image = new ImageView(this);
	        image.setId(i+1);
	        image.setImageResource(R.drawable.tushar100);
	       // image.setBackgroundResource(R.drawable.ic_launcher);
	        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(80, 80);
	        RelativeLayout.LayoutParams vp1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp1.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        vp1.setMargins(10, 5, 4, 2);
	        vp.setMargins(10, 20, 0, 0);
	       // image.setBackgroundColor(color.contact_back_color);
	        image.setBorderColor(getResources().getColor(R.color.GrayLight));
	        image.setBorderWidth(0);
	       // image.addShadow();
	        image.setLayoutParams(vp);
	        //image.setPadding(20, 20, 10, 4);
	       
	        rl.addView(image);
	        
	       /* Bitmap bMap1 = BitmapFactory.decodeResource(getResources(), R.drawable.ab50);
			Bitmap cmap1 = utils.getRoundedShape(bMap1);
			image.setImageBitmap(cmap1);*/
	        
	        
		                	 
	        HashMap<String, String> user = new HashMap<String, String>();
	        user =k.next();
	        
	        TextView text1 = new TextView(this);
	        text1.setId(i+1000);
	        text1.setText(user.get("firstname") + " " + user.get("lastname"));
	        text1.setLayoutParams(vp1);
	        text1.setTextColor(getResources().getColor(R.color.profile_font_color));
	        text1.setTextSize(15);
	        text1.setTypeface(tf);
	        
	        rl.addView(text1);
	        
	        TextView text2 = new TextView(this);
	        text2.setTypeface(tf);
	        text2.setId(i+1001);
	        RelativeLayout.LayoutParams vp2 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams vp3 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams vp4 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams vp5 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp3.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        vp4.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        vp5.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        vp3.setMargins(10, 0, 4, 0);
	        vp4.setMargins(10, 0, 4, 0);
	        vp5.setMargins(10, 0, 4, 4);
	        vp2.addRule(RelativeLayout.BELOW, text1.getId());
	        vp2.setMargins(10, 0, 4, 0);
	        vp2.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        text2.setText("Relation : " + user.get("relation"));
	        text2.setTextColor(getResources().getColor(R.color.profile_font_color));
	        text2.setLayoutParams(vp2);
	        text2.setTextSize(14);
	        rl.addView(text2);
	        
	        TextView text3 = new TextView(this);
	        text3.setId(i+1002);
	        text3.setTypeface(tf);
	        vp3.addRule(RelativeLayout.BELOW, text2.getId());
	        text3.setText("Occupation : "+user.get("profession"));
	        text3.setLayoutParams(vp3);
	        text3.setTextColor(getResources().getColor(R.color.profile_font_color));
	        text3.setTextSize(14);
	        rl.addView(text3);
	        
	        TextView text4 = new TextView(this);
	        text4.setId(i+1003);
	        text4.setTypeface(tf);
	        vp4.addRule(RelativeLayout.BELOW, text3.getId());
	        text4.setText(user.get("emailid"));
	        text4.setTextColor(getResources().getColor(R.color.profile_font_color));
	        text4.setLayoutParams(vp4);
	        text4.setTextSize(14);
	        rl.addView(text4);
	        
	        TextView text5 = new TextView(this);
	        text5.setId(i+1004);
	        text5.setTypeface(tf);
	        text5.setTextColor(getResources().getColor(R.color.profile_font_color));
	        vp5.addRule(RelativeLayout.BELOW, text4.getId());
	        text5.setText(user.get("contactno"));
	        text5.setLayoutParams(vp5);
	        text5.setTextSize(14);
	       // text2.setPadding(0, 0, 0, 5);
	        rl.addView(text5);
	        
	        //Drawing a horizontal line
	      //  RelativeLayout.LayoutParams vp5 = new RelativeLayout.LayoutParams(1, LayoutParams.WRAP_CONTENT);
	        
	        TextView text6 = new TextView(this);
	        text6.setText("");
	        text6.setTypeface(tf);
	        text6.setPadding(0, 3, 0, 0);
	        text6.setHeight(1);
	        text6.setBackgroundColor(getResources().getColor(R.color.contact_back_color));
	        l1.addView(text6);
	        
	        i+=10;
		}
		
		
		
		
		
		//Setting button action.
		
	/*	//forwarding to Add guardian page
		addguardian.setClickable(true);
		//final Intent intent = new Intent(this, addGuardian.class);
		addguardian.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	//startActivity(intent);
            }
        });*/
		
		
	}
	public void onBackPressed() {
		   Intent intent = new Intent(this, Home.class);
		   startActivity(intent);
		 }
	
}
