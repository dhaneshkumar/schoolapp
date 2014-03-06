package com.example.schoolapp;

import nav_drawer.commonDrawer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneList_ext extends commonDrawer {
		
	
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	TextView text5;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.phonelist_ext, null, false);
	    mDrawerLayout.addView(contentView, 0);
		
		
		//contact details
		String name = getIntent().getStringExtra("name");
		String post = getIntent().getStringExtra("post");
		String concPerson = getIntent().getStringExtra("concPerson");
		String contact = getIntent().getStringExtra("contact");
		String emailid = getIntent().getStringExtra("emailid");
		
		System.out.println( "extension :-------------- " + name + post + contact + emailid);
		
		
		//intialising textviews
		
		text1=(TextView) findViewById(R.id.phoneText1);
		text2=(TextView) findViewById(R.id.phoneTexxt2);
		text3=(TextView) findViewById(R.id.concperson);
		text4=(TextView) findViewById(R.id.cpdetails);
		text5=(TextView) findViewById(R.id.emailid);
		ImageView iw=(ImageView) findViewById(R.id.imgView);
		
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
		
		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
		Bitmap cmap = getRoundedShape(bMap);
		iw.setImageBitmap(cmap);
		
		
		
		//Toast.makeText(getApplicationContext(), name + "+" + post+" "+ concPerson + " " + contact + " " + emailid, Toast.LENGTH_SHORT).show();
	//	Toast.makeText(getApplicationContext()," ogt new pare", Toast.LENGTH_LONG).show();
	}
	
	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
		  // TODO Auto-generated method stub
		  int targetWidth = scaleBitmapImage.getHeight();
		  int targetHeight = scaleBitmapImage.getWidth();
		  Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, 
		                            targetHeight,Bitmap.Config.ARGB_8888);
		  
		                Canvas canvas = new Canvas(targetBitmap);
		  Path path = new Path();
		  path.addCircle(((float) targetWidth - 1) / 2,
		  ((float) targetHeight - 1) / 2,
		  (Math.min(((float) targetWidth), 
		                ((float) targetHeight)) / 2),
		          Path.Direction.CCW);
		  
		                canvas.clipPath(path);
		  Bitmap sourceBitmap = scaleBitmapImage;
		  canvas.drawBitmap(sourceBitmap, 
		                                new Rect(0, 0, sourceBitmap.getWidth(),
		    sourceBitmap.getHeight()), 
		                                new Rect(0, 0, targetWidth,
		    targetHeight), null);
		  return targetBitmap;
		 }
	
	
}
