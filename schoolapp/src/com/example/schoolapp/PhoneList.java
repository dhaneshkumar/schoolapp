package com.example.schoolapp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;
import library.DatabaseHandler;
import library.UserFunctions;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import library.*;

public class PhoneList extends ActionBarActivity {
	
	TableLayout table;
	TextView text1;
	TextView text2;
	TextView text3;
	
	
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.phonelist);
	
	//Initializing all variables
	//table = ( TableLayout) findViewById(R.id.timeTable);
	
	DatabaseHandler db = new DatabaseHandler(this);
	String result1="";	    
	db.setPhoneList();
	result1 = db.getPhoneList();
	System.out.println("got result : " + result1);                 
	String[] store = result1.split("~");
	                 
	for(int i=0; i<store.length;i++)
	{
	    	 //create a new table row
//	                	 TableRow row= new TableRow(this);
//	                	 TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT );
//	                	 TableRow.LayoutParams lp5 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,5 );
//	                	 TableRow.LayoutParams lp4 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,4 );
//	                     row.setLayoutParams(lp);
	                	 
	        String[] parts = store[i].split(",");
	}
	              		 
		
		

}

}