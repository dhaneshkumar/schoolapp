package com.example.schoolapp;

import nav_drawer.commonDrawer;
import library.utils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class settings_page extends commonDrawer {
	TextView mTextView1 ;
	TextView mTextView2 ;
	TextView mTextView3 ;
	TextView mTextView4 ;
	TextView mTextView5 ;
	TextView mTextView6;
	SeekBar s_bar1;
	SeekBar s_bar2;
	SeekBar s_bar3;
	SeekBar s_bar4;
	SeekBar s_bar5;
	int[] progress_note={10,10,10,10,10};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(R.layout.settings, null,
				false);
		mDrawerLayout.addView(contentView, 0);
		
		getSupportActionBar().setTitle("Settings");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(Color.parseColor("#58a533"))); 
		
		utils.ls("Entered in setting page  : ---");
		Typeface tf= Typeface.createFromAsset(getAssets(), "museo-300.ttf");
		
		utils.ls("Entered in setting page  : ---2222");
		 mTextView2 =(TextView) findViewById(R.id.blog_tx);
		 mTextView3 =(TextView) findViewById(R.id.notification_tx);
		 mTextView4 =(TextView) findViewById(R.id.event_tx);
		 mTextView5 =(TextView) findViewById(R.id.daily_attendance_tx);
		 mTextView6 =(TextView) findViewById(R.id.grades_tx);
		 
		
		 utils.ls("Entered in setting page  : ---55");
		 
			 mTextView2.setTypeface(tf);
			 mTextView3.setTypeface(tf);
			 mTextView4.setTypeface(tf);
			 mTextView5.setTypeface(tf);
			 mTextView6.setTypeface(tf);
			 utils.ls("Entered in setting page  : ---66665");
		 
		 s_bar1=(SeekBar) findViewById(R.id.seekBar1);
		 s_bar2=(SeekBar) findViewById(R.id.seekBar2);
		 s_bar3=(SeekBar) findViewById(R.id.seekBar3);
		 s_bar4=(SeekBar) findViewById(R.id.seekBar4);
		 s_bar5=(SeekBar) findViewById(R.id.seekBar5);
		 
		 s_bar1.setProgress(1);
		 s_bar1.incrementProgressBy(1);
		 s_bar1.setMax(1);
		 
		 s_bar2.setProgress(1);
		 s_bar2.incrementProgressBy(1);
		 s_bar2.setMax(1);
		 
		 s_bar3.setProgress(1);
		 s_bar3.incrementProgressBy(1);
		 s_bar3.setMax(1);
		 
		 s_bar4.setProgress(1);
		 s_bar4.incrementProgressBy(1);
		 s_bar4.setMax(1);
		 
		 s_bar5.setProgress(1);
		 s_bar5.incrementProgressBy(1);
		 s_bar5.setMax(1);
		 
		 utils.ls("Entered in setting page  : ---123");
		 
		 s_bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

			    @Override
			    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			      
			    	if(progress==0){
			    		//progress_note[0]=0;
			        	s_bar5.setProgress(0);
			        	 s_bar4.setProgress(0);
			        	 s_bar3.setProgress(0);
			        	 s_bar2.setProgress(0);
			        }
			    	else{
			    		s_bar5.setProgress(10);
			        	 s_bar4.setProgress(10);
			        	 s_bar3.setProgress(10);
			        	 s_bar2.setProgress(10);
			    	}
			       
			    }

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
			    
			});
		
	}



}
