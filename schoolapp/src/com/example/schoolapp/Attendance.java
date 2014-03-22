package com.example.schoolapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


public class Attendance extends Activity  {

	AttendanceView v;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		v=new AttendanceView(this);
		setContentView(v);
	}
/*	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "("+Float.toString(event.getX())+","+Float.toString(event.getY())+")", Toast.LENGTH_LONG).show();

		return true;
	}*/


}
