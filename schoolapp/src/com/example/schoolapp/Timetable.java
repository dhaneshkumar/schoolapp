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

public class Timetable extends Activity {
	
	TableLayout table;
	TextView text1;
	TextView text2;
	TextView text3;
	
	
@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.timetable);
	
	//Initializing all variables
	table = ( TableLayout) findViewById(R.id.timeTable);
	
	
	// Calling getTimeTable function to get timetable details of a specific day.
	UserFunctions userFunction = new UserFunctions();
	JSONObject json = userFunction.getTimetable("timetable", "WED");
	
	//System.out.println("result : **************************" + json);
	
	String result1="";
	
	
	try {
         if (json.getString("success") != null) {
             int res = json.getInt("success");
        
             if(res == 1){
                 result1 = json.getString("response");
                 
                 System.out.println("got result : " + result1);
                 
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
         }
             else{
                 Toast.makeText(getApplicationContext(), "Error in retriving timetable ", Toast.LENGTH_LONG).show();
             }
         
     } catch (JSONException e) {
      Toast.makeText(getApplicationContext(), "Error  null in retriving timetable ", Toast.LENGTH_LONG).show();
         e.printStackTrace();
     }
	
	// Toast.makeText(getApplicationContext(), result1, Toast.LENGTH_LONG).show();
	
	

	
}


	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timetable, menu);
		return true;
	}

}

