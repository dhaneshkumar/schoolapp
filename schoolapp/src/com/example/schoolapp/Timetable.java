package com.example.schoolapp;

import java.util.Vector;

import nav_drawer.commonDrawer;

import library.DatabaseHandler;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Timetable extends commonDrawer{
	
	TableLayout table;
	TextView text1;
	TextView text2;
	TextView text3;
	String currentDay="MON";
	DatabaseHandler db;
	

@SuppressLint("NewApi")
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//setContentView(R.layout.timetable);
	
	
	
	
	
	LayoutInflater inflater = (LayoutInflater) this
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View contentView = inflater.inflate(R.layout.timetable, null, false);
    mDrawerLayout.addView(contentView, 0);
	
	
	
	
	
	db = new DatabaseHandler(this);
	db.setTimeTable("1", "A");
	table = ( TableLayout) findViewById(R.id.timeTable);
	
	System.out.println("table : ---   "+table);
	
	System.out.println("db started");
	//display(currentDay);
	
	System.out.println("db working");
	/*
	 * Action bar actions
	 */
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.days,
	          android.R.layout.simple_spinner_dropdown_item);
	getSupportActionBar().setNavigationMode(1);
	
	
	
	
	OnNavigationListener mOnNavigationListener = new OnNavigationListener() {
		  // Get the same strings provided for the drop-down's ArrayAdapter
		  String[] strings = getResources().getStringArray(R.array.days);
		 
		  @Override
		  public boolean onNavigationItemSelected(int position, long itemId) {
		   
			 final String s =strings[position];
			 //System.out.println("today timetable : ----" + s);
			  display(s);
			 // Toast.makeText(getApplicationContext(), strings[position], Toast.LENGTH_SHORT).show(); 
		    return true;
		  }
		  
		  
		};
	
		getSupportActionBar().setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
		//getSupportActionBar().LayoutParams(right);
	
	/**************************************************/
              // Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();     
    
}

	public void display(String currentDay)
	{
		db = new DatabaseHandler(this);
		System.out.println("display called : " + currentDay);
		
		
		table.removeAllViews();
		//Initializing all variables
		String result1 =db.getTimeTable(currentDay);
		
		/*
		 *	
		 *	getTimetable(); + refreshing options
		 *	setTimetable(class, section , day, timestamp);----check timestamp
		 *	getTimetable(day, changeFlag) : ---check setchanged 
		 */
		
		// Calling getTimeTable function to get timetable details of a specific day.
		//db.init();
		//init start= new init();
		
	                 String[] store = result1.split("~");
	          
	                 
	       
	        
	                 for(int i=0; i<store.length;i++)
	                 {
	                	 //create a new table row
	                	 TableRow row= new TableRow(this);
	                	 TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT );
	                	 TableRow.LayoutParams lp5 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,5 );
	                	 TableRow.LayoutParams lp4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,4 );
	                     row.setLayoutParams(lp);
	                	 
	                	 String[] parts = store[i].split(",");
	              		 
	                	 //text1 details
	                	 text1 = new TextView(this);
	              		 text1.setText(parts[0]+ " - " + parts[1]);
	                	 text1.setLayoutParams(lp5);
	                	 text1.setPadding(10, 10, 0, 10);
	                	 //text1.setWidth(1);
	                	 row.addView(text1);
	                	 
	                	//text2 details
	                	 text2 = new TextView(this);
	              		 text2.setText(parts[2]);
	                	 text2.setLayoutParams(lp4);
	                	 text2.setPadding(0,10,0,10);
	                	 row.addView(text2);
	                	 
	                	//text3 details
	                	 text3 = new TextView(this);
	              		 text3.setText(parts[3]);
	                	 text3.setLayoutParams(lp5);
	                	 text3.setPadding(0, 10, 10, 10);
	                	 row.addView(text3);
	                	 
	                	 table.addView(row);
	                	 
	                	 //a new row for displaying space
	                	 TableRow row1= new TableRow(this);
	                	 TableRow.LayoutParams lp11 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1 );
	                     row1.setLayoutParams(lp11);
	                     row1.setBackgroundColor(Color.parseColor("#BDBDBD"));
	                     
	                     TextView text4 = new TextView(this);
	                     text4.setHeight(1);
	                     text4.setText("");
	                     row1.addView(text4);
	                     
	                     table.addView(row1);
	                     
	                 }
	                 
	}

        
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main_activity_actions, menu);
		 return super.onCreateOptionsMenu(menu);
	}



}

