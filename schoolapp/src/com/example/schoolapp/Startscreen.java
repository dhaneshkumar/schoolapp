package com.example.schoolapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

public class Startscreen extends ActionBarActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startscreen);
		
		getSupportActionBar().hide();
		
		  
		
		new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Startscreen.this, splash.class);
                Startscreen.this.startActivity(mainIntent);
                Startscreen.this.finish();
            }
        }, 500);
	}
}
