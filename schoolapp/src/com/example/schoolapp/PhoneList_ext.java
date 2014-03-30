package com.example.schoolapp;

import library.utils;
import nav_drawer.commonDrawer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneList_ext extends commonDrawer {
		
	
	TextView name;
	TextView clas;
	TextView subjects;
	TextView emailid;
	TextView contactno;
	TextView call;
	String[] parts;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.phonelist_ext, null, false);
	    mDrawerLayout.addView(contentView, 0);
		
	    getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected))); 
	    Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));

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
	    
		name=(TextView) findViewById(R.id.name);
		clas=(TextView) findViewById(R.id.clas);
		subjects=(TextView) findViewById(R.id.subjects);
		contactno=(TextView) findViewById(R.id.contactno);
		emailid=(TextView) findViewById(R.id.emailid);
		call = (TextView) findViewById(R.id.callbutton);
		ImageView iw=(ImageView) findViewById(R.id.imgView);
	    
		name.setTypeface(tf);
		clas.setTypeface(tf);
		subjects.setTypeface(tf);
		contactno.setTypeface(tf);
		emailid.setTypeface(tf);
		call.setTypeface(tf);
	    
		//contact details
		String tag = getIntent().getStringExtra("tag");
		String details = getIntent().getStringExtra("details");
		
		
		System.out.println("Details ---------- : " +details);
		parts=details.split("~~");
		
		if(tag.trim().equals("Teacher"))
		{
			
			name.setText(parts[1]);
			clas.setText("Class: " +parts[4]);
			subjects.setText("Subject: " + parts[3]);
			contactno.setText(parts[5]);
			emailid.setText(parts[6]);
			getSupportActionBar().setTitle("Teacher's Profile");
			
		}
		else
		{	
			name.setText(parts[1]);
			clas.setText(parts[2]);
			if(parts[4].equals("") || parts[4].equals(" "))
			{
				subjects.setVisibility(View.GONE);
				
			}
			
			
			subjects.setText("Conc. Person: " + parts[4]);
			contactno.setText(parts[5]);
			emailid.setText(parts[6]);
			getSupportActionBar().setTitle(tag);
			
		}
		
		
		call.setClickable(true);
		
		
		call.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	Intent callIntent = new Intent(Intent.ACTION_CALL);
            	callIntent.setData(Uri.parse("tel:" + parts[5]));
            	startActivity(callIntent);
            }
        });
		
		
		/*
		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab100);
		Bitmap cmap = utils.getRoundedShape(bMap);
		iw.setImageBitmap(cmap);
		*/
		
		
		//Toast.makeText(getApplicationContext(), name + "+" + post+" "+ concPerson + " " + contact + " " + emailid, Toast.LENGTH_SHORT).show();
	//	Toast.makeText(getApplicationContext()," ogt new pare", Toast.LENGTH_LONG).show();
	}
	
	
	
}
