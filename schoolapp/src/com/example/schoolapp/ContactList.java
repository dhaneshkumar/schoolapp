package com.example.schoolapp;

import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class ContactList extends commonDrawer implements View.OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.contactlist, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	    
	    Button teacher =  ( Button) findViewById(R.id.teacher);
	    Button admin =  ( Button) findViewById(R.id.administration);
	    Button auth =  ( Button) findViewById(R.id.authority);
	    Button others =  ( Button) findViewById(R.id.others);
	    
	    teacher.setOnClickListener(this);
        admin.setOnClickListener(this);
        auth.setOnClickListener(this);
        others.setOnClickListener(this);
	    
	}
	
	
	@Override
	   public void onClick(View v) {
		Intent myIntent = new Intent(ContactList.this, PhoneList.class);
	       switch(v.getId()) {
	           case R.id.teacher:
	        	   myIntent.putExtra("tag", "teacher");
	           break;
	           
	           case R.id.administration:
	        	   myIntent.putExtra("tag", "administration");
	           break;
	           
	           case R.id.authority:
	        	   myIntent.putExtra("tag", "authority");
	           break;
	           
	           case R.id.others:
	        	   myIntent.putExtra("tag", "others");
	           break;
	           
	       }
	       
	       startActivity(myIntent);
	}

}
