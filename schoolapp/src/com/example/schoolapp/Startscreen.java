package com.example.schoolapp;

import library.DatabaseHandler;
import library.utils;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Startscreen extends ActionBarActivity{
	DatabaseHandler db;
	String[] tableList;
	int screenTime = 5000;
	ProgressBar progressbar;
	ImageView image;
	TextView tex;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		
		db = new DatabaseHandler(this);
		tableList=DatabaseHandler.tableList;
		
		progressbar= (ProgressBar ) findViewById(R.id.progressBar);
		tex = (TextView) findViewById(R.id.text);
		utils ul =new utils();
		ul.setFont(tex);
		
		progressbar.getIndeterminateDrawable().setColorFilter(0xFF0091c1,
		                        android.graphics.PorterDuff.Mode.MULTIPLY);
		
		
		progressbar.setVisibility(View.GONE);
		tex.setVisibility(View.GONE);
		
		image = (ImageView) findViewById(R.id.imgView);
		
		getSupportActionBar().hide();
		
		new LoadData(this).execute("random",  "random");
		
		
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*final Intent mainIntent = new Intent(Startscreen.this, splash.class);
                Startscreen.this.startActivity(mainIntent);
                Startscreen.this.finish();*/
            	image.setVisibility(View.GONE);
            	progressbar.setVisibility(View.VISIBLE);
            	tex.setVisibility(View.VISIBLE);
            }
        }, screenTime);
	}
	
	
	
	
	 private class LoadData extends AsyncTask<String, Void, String> {
	    	
		 Context context;
	     int counter=0;
	        
	        
	        public LoadData(Context context1){

	            this.context=context1;
	          }
	        
	        @Override
	        protected String doInBackground(String... params) {

	    		/*
	        	Thread closeActivity = new Thread(new Runnable() {
	        		  @Override
	        		  public void run() {
	        		    try {
	        		      Thread.sleep(3000);
	        		      // Do some stuff
	        		    } catch (Exception e) {
	        		      e.getLocalizedMessage();
	        		    }
	        		  }
	        		});	*/
	        	
	        	
	        	
	    	
	    		if(db.getCount()!=10)
	    		{
	    			db.setup(tableList);
	    			db.setDefaultID();
	    			db.updateCount();
	    		}
	    		else
	    			screenTime=300;
	    		
	        	
	        	/* for (int i = 0; i < 10; i++) {
	                 try {
	                     Thread.sleep(1000);
	                 } catch (InterruptedException e) {
	                     Thread.interrupted();
	                 }
	             }
	        	*/
	        	
	    		return null;
	        }

	        @Override
	        protected void onPostExecute(String result) {
	        	context.startActivity(new Intent(context, splash.class));
	        	
	        }

	        @Override
	        protected void onPreExecute() {}

	        @Override
	        protected void onProgressUpdate(Void... values) {
	        	
	        	
	        }

			
	    }
	
}
