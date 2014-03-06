package com.example.schoolapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class AttendanceView extends View{
	String [] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
	RectF oval1;
	RectF oval;
	Paint black;
	Paint white_fill;
	Paint pink_fill;
	Paint light_blue;
	float center_x;
	float center_y;
	Path p;
	float theta;
	float offset=(float) 12;               //offset is 12 for 31 and 30 and 29
								   //offset is 13.85 for 28
	int days=30;
	
	float radius=0;
	float inner_radius=0;
	Context context1;
	public AttendanceView(Context context) {
		super(context);
		context1=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		center_x=canvas.getWidth()/2;
		center_y=canvas.getHeight()/2;
		
		
		white_fill=new Paint();
		white_fill.setColor(Color.WHITE);
		white_fill.setStyle(Paint.Style.FILL);
		
		p=new Path();
		
		black=new Paint();
		black.setColor(Color.BLACK);
		black.setStyle(Paint.Style.STROKE);
		
		light_blue=new Paint();
		light_blue.setColor(Color.BLUE);
		light_blue.setStyle(Paint.Style.FILL);
		
		
		 oval = new RectF();
		  oval1= new RectF();
		
		pink_fill=new Paint();
		pink_fill.setColor(0x88FF0000);
		pink_fill.setStyle(Style.FILL);
		
		
	//	canvas.draw
		if(canvas.getHeight()>=canvas.getWidth()){

		 radius=(float) (canvas.getWidth()*0.5);
		 inner_radius=(float) (canvas.getWidth()*0.4);

		}
		else{
			
			radius=(float) (canvas.getHeight()*0.5);
			inner_radius=(float) (canvas.getHeight()*0.4);
		}
		canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,(float)radius,black);

		oval.set(center_x - radius, 

				center_y - radius, 

				center_x + radius, 

				center_y + radius);
		oval1.set(center_x - inner_radius, 

				center_y - inner_radius, 

				center_x + inner_radius, 

				center_y + inner_radius);
		
		float temp=0;
		p.addOval(oval1, Direction.CW);
		canvas.drawArc(oval,temp, offset, true, pink_fill);
		canvas.drawArc(oval, temp, offset, true, black);	
		theta= (float)(360-offset)/(days-1);

		
		for(int i=0;i<days-1;++i){
			temp=offset+i*theta;
			canvas.drawArc(oval,temp, theta, true, pink_fill);
			canvas.drawArc(oval, temp, theta, true, black);
			
			canvas.drawTextOnPath(Integer.toString(i+1),p, (float) ((inner_radius*theta*3.14/180)*(0.5+i)) , -15 ,black);
			
			//canvas.drawtext
			
		}
		canvas.drawTextOnPath(Integer.toString(days),p, (float) ((inner_radius*theta*3.14/180)*(0.5+days-1)) , -15 ,black);

		System.out.print("check--------" + inner_radius);
		
		
		canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,(float) inner_radius,white_fill);
		canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,(float) inner_radius,black);
		draw_weeks(canvas);
		}
		
	
		public void draw_weeks(Canvas c){
			
			float outer_radius=(float) (c.getWidth()*0.35);
			float inner_radius_2=(float) (c.getWidth()*0.25);
			c.drawCircle(c.getWidth()/2,c.getHeight()/2,outer_radius,black);
			c.drawCircle(c.getWidth()/2,c.getHeight()/2,inner_radius_2,black);
			float phi=(float) 30;
			oval=new RectF();
			oval1=new RectF();
			oval.set(center_x - outer_radius, 

					center_y - outer_radius, 

					center_x + outer_radius, 

					center_y + outer_radius);
			oval1.set(center_x - inner_radius_2, 

					center_y - inner_radius_2, 

					center_x + inner_radius_2, 

					center_y + inner_radius_2);
			p=new Path();
			p.addOval(oval1, Direction.CW);
			for(int i=0;i<12;++i){
				c.drawArc(oval,i*phi,phi, true, light_blue);
				c.drawArc(oval,i*phi, phi, true, black);
				c.drawTextOnPath(months[i],p, (float) ((inner_radius_2*phi*3.14/180)*(0.5+i)) , -15 ,black);

			}
			c.drawCircle(c.getWidth()/2,c.getHeight()/2,(float) inner_radius_2,white_fill);
			c.drawCircle(c.getWidth()/2,c.getHeight()/2,(float) inner_radius_2,black);
		}
	

		
}
