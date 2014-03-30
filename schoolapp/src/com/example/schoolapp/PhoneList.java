package com.example.schoolapp;

	import com.example.schoolapp.R.color;

import nav_drawer.*;
	
	import library.DatabaseHandler;
import library.utils;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
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
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import nav_drawer.*;


public class PhoneList extends commonDrawer {		
		TableLayout table;
		TextView text1;
		TextView text2;
		TextView text3;
		String[] store;
		String tag;
		String storestring;
		
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.phonelist, null, false);
	    mDrawerLayout.addView(contentView, 0);

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
		
	    Intent intent = getIntent();
	     tag = intent.getStringExtra("tag");
	

		System.out.println("phonelist loading -----------tag :     " + tag + "     :---------.");
		
		getSupportActionBar().setTitle(tag);
		//table = ( TableLayout) findViewById(R.id.timeTable);
		
		LinearLayout l1 = (LinearLayout) findViewById(R.id.layout1);
		//l1.setBackgroundColor(Color.LTGRAY);
		
		System.out.println("drawer loaded--------------------."+ l1);
		
		DatabaseHandler db = new DatabaseHandler(this);
		String result1="";	    
		//db.setPhoneList();
		result1 = db.getPhoneList(tag);
		db.close();
		System.out.println("got result : " + result1);                 
		
		store = result1.split("###");

		Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));		
		for(int i=0; i<store.length;i++)
		{
			// Creating a new RelativeLayout
	        RelativeLayout rl = new RelativeLayout(this);
	        rl.setId(i+2000);
	        rl.setClickable(true);
	        rl.setPadding(10, 5, 5, 5);
	        // Defining the RelativeLayout layout parameters.
	        // In this case I want to fill its parent
	        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
	        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        rlp.setMargins(0, 8, 0, 0);
	        
	        //rlp.setMargins(left, top, right, bottom)
	        rl.setLayoutParams(rlp);
	        rl.setBackgroundColor(getResources().getColor(R.color.contact_back_color));
	        
	        l1.addView(rl);
	        
	       
	        
	        //ImageView image = new ImageView(this);
	        com.example.schoolapp.CircularImageView image = new com.example.schoolapp.CircularImageView(this);
		     
	        image.setId(i+1);
	       // image.setBackgroundResource(R.drawable.ic_launcher);
	        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(70, 70);
	        RelativeLayout.LayoutParams vp1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp1.addRule(RelativeLayout.RIGHT_OF, image.getId());
	      //  vp.setMargins(10, 2, 10, 2);
	        vp1.setMargins(10, 8, 4, 2);
	       // image.setBackgroundColor(color.contact_back_color);
	        image.setLayoutParams(vp);
	        image.setPadding(20, 4, 10, 4);
	        rl.addView(image);
	        image.setImageResource(R.drawable.ab100);
	        image.setBorderColor(getResources().getColor(R.color.GrayLight));
	        image.setBorderWidth(0);
	       // image.addShadow();
		                	 
	       
	        String[] parts = store[i].split("~~");
	        storestring =store[i];
	        
	        System.out.println("parts : -- " + parts[0]);
	        
	       
	        
	        
	        TextView text1 = new TextView(this);
	        text1.setId(i+1000);
	        text1.setText(parts[1]);
	        text1.setLayoutParams(vp1);
	        text1.setTextColor(getResources().getColor(R.color.contact_font_color));
	        text1.setTextSize(15);
	        text1.setTypeface(tf);
	        text1.setPadding(10, 0, 0, 5);
	        rl.addView(text1);
	        
	        
	        TextView text2 = new TextView(this);
	        RelativeLayout.LayoutParams vp2 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp2.addRule(RelativeLayout.BELOW, text1.getId());
	        vp2.setMargins(10, 3, 4, 2);
	        vp2.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        text2.setText(parts[2]);
	        text2.setLayoutParams(vp2);
	        text2.setTypeface(Typeface.DEFAULT_BOLD);
	        text2.setTextSize(12);
	        text2.setTypeface(tf);
	        text2.setTextColor(getResources().getColor(R.color.profile_selected));
	        text2.setPadding(10, 0, 0, 5);
	        rl.addView(text2);
	        
	        
	        rl.setOnClickListener( new View.OnClickListener(){
	            @Override
	            public void onClick(View v) {
	            	
	            //    Toast.makeText(getApplicationContext(), parts[1] + "+" + parts[4], Toast.LENGTH_SHORT).show();
	                Intent i=new Intent(getApplicationContext(), PhoneList_ext.class);
	                i.putExtra("tag", tag);
	                i.putExtra("details", storestring);
	                
	                System.out.println("parts : -----------  " + storestring);

	                
	                startActivity(i);
	            }
	        });
		    
		}
		              		 
		}
	
	
	}

			
			

