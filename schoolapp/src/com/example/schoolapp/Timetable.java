package com.example.schoolapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import nav_drawer.commonDrawer;

import library.DatabaseHandler;
import library.utils;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import com.example.schoolapp.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

	@SuppressLint("ValidFragment")
	public class Timetable extends Fragment {
		
		int index=0;
		
		
		public Timetable(int i)
		{
			index=i;
		}
		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.timetable, container, false);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    
		   LinearLayout l1 = (LinearLayout) getView().findViewById(R.id.LinearLayout);
		   
	        
	        List<HashMap<String, String>> timetableDetails = new ArrayList<HashMap<String, String>>();
			DatabaseHandler db = new DatabaseHandler(getActivity());
			timetableDetails = db.getTimeTable(index);
	        
	        Iterator<HashMap<String, String>> k=timetableDetails.iterator();
			while(k.hasNext())
			{
		        HashMap<String, String> user = new HashMap<String, String>();
		        user =k.next();
		        
	           	LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
	        	        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	           	LinearLayout.LayoutParams rlp1 = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT,3);
	           	LinearLayout.LayoutParams rlp2 = new LinearLayout.LayoutParams(0 , LayoutParams.WRAP_CONTENT,4);
	           	LinearLayout.LayoutParams rlp3 = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 5);
	        	rlp.setMargins(0, 4, 0, 3);
	        	LinearLayout linear = new LinearLayout(getActivity());      
	        	linear.setLayoutParams(rlp);
	           	 
	         
           	 
           	 //text1 details
             TextView text1 = new TextView(getActivity());
         	 text1.setText(user.get("from"));
           	 text1.setPadding(10, 5, 10, 0);
           	 text1.setWidth(0);
           	 text1.setTextSize(15);
           	 text1.setLayoutParams(rlp1);
           	 text1.setTextColor(getResources().getColor(R.color.profile_font_color));
           	 linear.addView(text1);
           	 
           	//text2 details
           	TextView text2 = new TextView(getActivity());
         	text2.setText(user.get("subject"));
         	text2.setWidth(0);
         	 text1.setTextSize(15);
          	 text2.setLayoutParams(rlp2);
          	 text2.setTextColor(getResources().getColor(R.color.profile_font_color));
           	 text2.setPadding(0,5,0,0);
           	 linear.addView(text2);
           	 
           	//text3 details
           	TextView text3 = new TextView(getActivity());
           	text3.setText(user.get("teachername"));
           	text3.setWidth(0);
        	text3.setTextSize(15);
         	text3.setLayoutParams(rlp3);
         	text3.setTextColor(getResources().getColor(R.color.profile_font_color));
           	text3.setPadding(0, 5, 0, 0);
           	linear.addView(text3);
           	 
           	l1.addView(linear);
           	 
          //showing horizontal line
           	TextView text4 = new TextView(getActivity());
           	LinearLayout.LayoutParams rlp4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 1);
           	text4.setText("");
         	text4.setLayoutParams(rlp4);
         	text4.setBackgroundColor(getResources().getColor(R.color.contact_back_color));
           	l1.addView(text4);
			}
		}
		
	}
