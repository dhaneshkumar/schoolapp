package com.example.schoolapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneList_ext extends ActionBarActivity {
		
	
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	TextView text5;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phonelist_ext);
		
		
		//contact details
		String name = getIntent().getStringExtra("name");
		String post = getIntent().getStringExtra("post");
		String concPerson = getIntent().getStringExtra("concPerson");
		String contact = getIntent().getStringExtra("contact");
		String emailid = getIntent().getStringExtra("emailid");
		
		
		//intialising textviews
		
		text1=(TextView) findViewById(R.id.phoneText1);
		text2=(TextView) findViewById(R.id.phoneTexxt2);
		text3=(TextView) findViewById(R.id.concperson);
		text4=(TextView) findViewById(R.id.cpdetails);
		text5=(TextView) findViewById(R.id.emailid);
		
		//table row to hide/display concernperson
		TableRow r1 =(TableRow) findViewById(R.id.row1);
		TableRow r2 =(TableRow) findViewById(R.id.row2);
		
		text1.setText(post);
		text2.setText(name);
		text3.setText(concPerson);
		text4.setText(contact);
		text5.setText(emailid);
		
		if(concPerson.equals(""))
		{
			r1.setVisibility(View.GONE);
			r2.setVisibility(View.GONE);
			
		}
		
		
		//Toast.makeText(getApplicationContext(), name + "+" + post+" "+ concPerson + " " + contact + " " + emailid, Toast.LENGTH_SHORT).show();
	//	Toast.makeText(getApplicationContext()," ogt new pare", Toast.LENGTH_LONG).show();
	}
	
	
}
