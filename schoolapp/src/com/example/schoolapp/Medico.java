package com.example.schoolapp;

import nav_drawer.commonDrawer;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;

	import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

	public class Medico extends commonDrawer implements OnClickListener {

	    Button btn;
	    

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.events);
	        btn = (Button) findViewById(R.id.button1);
	        // because we implement OnClickListener we only have to pass "this"
	        // (much easier)
	        btn.setOnClickListener(this);
	    }

	    public void onClick(View view) {
	        // detect the view that was "clicked"
	        switch (view.getId()) {
	        case R.id.button1:
	            new LongOperation().execute("");
	            break;
	        }
	    }

	    private class LongOperation extends AsyncTask<String, Void, String> {
	    	

	        @Override
	        protected String doInBackground(String... params) {
	            for (int i = 0; i < 5; i++) {
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    Thread.interrupted();
	                }
	            }
	            return "Executed";
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            TextView txt = (TextView) findViewById(R.id.output);
	            txt.setText("Executed"); // txt.setText(result);
	            // might want to change "executed" for the returned string passed
	            // into onPostExecute() but that is upto you
	        }

	        @Override
	        protected void onPreExecute() {}

	        @Override
	        protected void onProgressUpdate(Void... values) {
	        	
	        }
	    }
	}
		


