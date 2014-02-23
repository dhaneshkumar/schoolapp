package com.example.schoolapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import nav_drawer.commonDrawer;

public class profile extends commonDrawer{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		//setting the layout
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.profile, null, false);
	    mDrawerLayout.addView(contentView, 0);
	
	    
	
	
	}
		

}
