package com.example.schoolapp;

	import nav_drawer.*;
	
	import library.DatabaseHandler;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import nav_drawer.*;


public class PhoneList extends commonDrawer {		
		TableLayout table;
		TextView text1;
		TextView text2;
		TextView text3;
		String[] store;
		String tag;
		String storestring;
		
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.phonelist, null, false);
	    mDrawerLayout.addView(contentView, 0);

	    //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	    Intent intent = getIntent();
	     tag = intent.getStringExtra("tag");
	

		System.out.println("phonelist loading -----------tag :     " + tag + "     :---------.");
		
		getSupportActionBar().setTitle(tag);
		//table = ( TableLayout) findViewById(R.id.timeTable);
		
		LinearLayout l1 = (LinearLayout) findViewById(R.id.layout1);
		//l1.setBackgroundColor(Color.LTGRAY);
		
		System.out.println("drawer loaded--------------------."+ l1);
		
		DatabaseHandler db = new DatabaseHandler(this);
		String result1="";	    
		//db.setPhoneList();
		result1 = db.getPhoneList(tag);
		System.out.println("got result : " + result1);                 
		
		store = result1.split("~");

		
		
		for(int i=0; i<store.length;i++)
		{
			
			// Creating a new RelativeLayout
	        RelativeLayout rl = new RelativeLayout(this);
	        rl.setId(i+2000);
	        rl.setClickable(true);
	        // Defining the RelativeLayout layout parameters.
	        // In this case I want to fill its parent
	        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(
	        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        rlp.setMargins(4, 0, 0, 0);
	        rl.setLayoutParams(rlp);
	        rl.setBackgroundColor(Color.WHITE);
	        l1.addView(rl);
	        
	       
	        
	        ImageView image = new ImageView(this);
	        image.setId(i+1);
	       // image.setBackgroundResource(R.drawable.ic_launcher);
	        LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams vp1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp1.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        vp.setMargins(2, 2, 2, 2);
	        vp1.setMargins(4, 4, 4, 4);
	        image.setLayoutParams(vp);
	        rl.addView(image);
	        
	        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.dp);
			Bitmap cmap = getRoundedShape(bMap);
			image.setImageBitmap(cmap);
		                	 
	       
	        String[] parts = store[i].split(",");
	        storestring =store[i];
	        
	        System.out.println("parts : -- " + parts[0]);
	        
	       
	        
	        
	        TextView text1 = new TextView(this);
	        text1.setId(i+1000);
	        text1.setText(parts[1]);
	        text1.setLayoutParams(vp1);
	        text1.setTextSize(15);
	        rl.addView(text1);
	        
	        
	        TextView text2 = new TextView(this);
	        RelativeLayout.LayoutParams vp2 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        vp2.addRule(RelativeLayout.BELOW, text1.getId());
	        vp2.setMargins(4, 4, 4, 4);
	        vp2.addRule(RelativeLayout.RIGHT_OF, image.getId());
	        text2.setText(parts[2]);
	        text2.setLayoutParams(vp2);
	        text2.setTypeface(Typeface.DEFAULT_BOLD);
	        text2.setTextSize(13);
	        text2.setPadding(0, 0, 0, 5);
	        rl.addView(text2);
	        
	        
	        rl.setOnClickListener( new View.OnClickListener(){
	            @Override
	            public void onClick(View v) {
	            	
	            //    Toast.makeText(getApplicationContext(), parts[1] + "+" + parts[4], Toast.LENGTH_SHORT).show();
	                Intent i=new Intent(getApplicationContext(), PhoneList_ext.class);
	                i.putExtra("tag", tag);
	                i.putExtra("details", storestring);
	                
	                System.out.println("parts : -----------  " + storestring);

	                
	                startActivity(i);
	            }
	        });
		    
		}
		              		 
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

			
			

