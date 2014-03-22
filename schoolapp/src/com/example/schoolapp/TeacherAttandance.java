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
	public class TeacherAttandance extends Fragment {
		
		int index=0;
		
		
		public TeacherAttandance(int i)
		{
			index=i;
		}
		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.teacherattendance, container, false);
			return rootView;
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    
		   LinearLayout l1 = (LinearLayout) getView().findViewById(R.id.LinearLayout);
		   
	        /*
	        List<HashMap<String, String>> timetableDetails = new ArrayList<HashMap<String, String>>();
			DatabaseHandler db = new DatabaseHandler(getActivity());
			timetableDetails = db.getTimeTable(index);*/
	        
	       
           	 
          //showing horizontal line
           	TextView text4 = new TextView(getActivity());
           	LinearLayout.LayoutParams rlp4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
           	text4.setText("shining");
         	text4.setLayoutParams(rlp4);
         	text4.setBackgroundColor(getResources().getColor(R.color.contact_back_color));
           	l1.addView(text4);
			
		}
		
	}
