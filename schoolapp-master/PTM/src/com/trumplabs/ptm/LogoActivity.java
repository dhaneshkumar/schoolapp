package com.trumplabs.ptm;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class LogoActivity extends Activity {
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);

		handler = new Handler();
		Log.v("APP","Timer started...");
	    handler.postDelayed(new Runnable() { 
	         public void run() { 
	        	 Log.v("APP","Starting next activity...");
	        	 Intent intent = new Intent("android.intent.action.LOGIN");
	             startActivityForResult(intent,0);   
	         } 
	    }, 4000); 
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if(resultCode == 0) {
	    	Log.v("APP","Closing the app...");
	        finish();
	    }
	}
	
	public void onBackPressed(){
		Log.v("APP","Removing all callbacks...");
		handler.removeCallbacksAndMessages(null);
		Log.v("APP","Closing the app...");
		finish();
	}

}
