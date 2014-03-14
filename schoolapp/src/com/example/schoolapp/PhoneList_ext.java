package com.example.schoolapp;

import nav_drawer.commonDrawer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
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
		
	
	TextView name;
	TextView clas;
	TextView subjects;
	TextView emailid;
	TextView contactno;
	TextView call;
	String[] parts;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.phonelist_ext, null, false);
	    mDrawerLayout.addView(contentView, 0);
		
		
		name=(TextView) findViewById(R.id.name);
		clas=(TextView) findViewById(R.id.clas);
		subjects=(TextView) findViewById(R.id.subjects);
		contactno=(TextView) findViewById(R.id.contactno);
		emailid=(TextView) findViewById(R.id.emailid);
		call = (TextView) findViewById(R.id.callbutton);
		ImageView iw=(ImageView) findViewById(R.id.imgView);
	    
	    
		//contact details
		String tag = getIntent().getStringExtra("tag");
		String details = getIntent().getStringExtra("details");
		
		
		System.out.println("Details ---------- : " +details);
		parts=details.split("~~");
		
		if(tag.trim().equals("Teacher"))
		{
			
			name.setText(parts[1]);
			clas.setText("Class: " +parts[4]);
			subjects.setText("Subject: " + parts[3]);
			contactno.setText(parts[5]);
			emailid.setText(parts[6]);
			getSupportActionBar().setTitle("Teacher's Profile");
			
		}
		else
		{	
			name.setText(parts[1]);
			clas.setText(parts[2]);
			if(parts[4].equals("") || parts[4].equals(" "))
			{
				subjects.setVisibility(View.GONE);
				
			}
			
			
			subjects.setText("Conc. Person: " + parts[4]);
			contactno.setText(parts[5]);
			emailid.setText(parts[6]);
			getSupportActionBar().setTitle(tag);
		}
		
		
		call.setClickable(true);
		
		
		call.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            	
            //    Toast.makeText(getApplicationContext(), parts[1] + "+" + parts[4], Toast.LENGTH_SHORT).show();
            	Intent callIntent = new Intent(Intent.ACTION_CALL);
            	
            	System.out.println("contact no -----------" + parts[5]);
            	callIntent.setData(Uri.parse("tel:" + parts[5]));
            	startActivity(callIntent);
            }
        });
		
		
		
		Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ab100);
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
