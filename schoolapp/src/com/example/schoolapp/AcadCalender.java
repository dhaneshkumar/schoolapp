package com.example.schoolapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import library.DatabaseHandler;
import library.utils;
import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

public class AcadCalender extends commonDrawer{
	TextView display_event;
	String [] store;
	String date_string = "2014-02-24";
	String date_string_2 ="2014-02-25";
	String event="Midsem over party";
	String event2="endsem lukha";
	Boolean month_selected=true;
	private CaldroidFragment caldroidFragment;
	Date date1;
	int display_event_flag=0;
	
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	ListView list;
	int size=0;
	LinearLayout calenderview;
	 String[] date_array = new String[] { "2014-02-24", 
             "2014-02-25"
            };
	 String[] event_array = new String[] { "Midsem over party", 
             "endsem lukha"
            };
	ArrayList<String> date_start_array=new ArrayList<String>();
	ArrayList<String> date_end_array=new ArrayList<String>();;
	ArrayList<String>time_start_array=new ArrayList<String>();;
	ArrayList<String> time_end_array=new ArrayList<String>();;
	ArrayList<String>title_array=new ArrayList<String>();;
	ArrayList<String> description_array=new ArrayList<String>();;
	ArrayList<String> special_guest_array=new ArrayList<String>();;
	ArrayList<String> venue_array=new ArrayList<String>();;
	ArrayList<String> extra_details_array=new ArrayList<String>();;
	 
	private void setCustomResourceForDates() {
		Calendar cal = Calendar.getInstance();
		Date blueDate;
		try {
			
			for(int i=0;i<date_start_array.size() ;++i){
				if (caldroidFragment != null) {
					blueDate = formatter.parse(date_start_array.get(i));
				//	caldroidFragment.setBackgroundResourceForDate(R.color.blue,
					//		blueDate);
					
					//	caldroidFragment.setBackgroundResourceForDate(R.color.green,
					//		greenDate);
					
					utils.ls("entered in acad calendar ------");
					
					//****************************< ALL EVENT'S TEXT COLOR >*****************************************
					caldroidFragment.setTextColorForDate(R.color.white, blueDate);
					
					//****************************< ALL EVENT'S BACKGROUND COLOR >*****************************************
					
					caldroidFragment.setBackgroundResourceForDate(R.drawable.absent,
							blueDate);
					
					
					utils.ls("entered in acad calendar ------111");
				//	caldroidFragment.refreshView();
					
				//	caldroidFragment.setTextColorForDate(R.color.white, greenDate);
				}
			}
		
		
       

		// Min date is last 7 days
		//cal.add(Calendar.DATE, -18);
		//Date blueDate = cal.getTime();

		// Max date is next 7 days
		//cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, 2);
		//Date greenDate = new SimpleDateFormat("yyyy-MM-dd").parse(date_string_2);

	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.acadcalender);
		
		// setting the layout
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View contentView = inflater.inflate(R.layout.acadcalender, null,
				false);
		mDrawerLayout.addView(contentView, 0);
				
		
		getSupportActionBar().setTitle("Academic Calender");
		getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(getResources().getColor(R.color.profile_selected))); 
		

		  int apino =Integer.valueOf(android.os.Build.VERSION.SDK);
		  if(apino>=11)
		  {
			//setting action bar title font
				int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
			
				TextView actionBarTitleView = (TextView) findViewById(actionBarTitle);
				  Typeface typeface = Typeface.createFromAsset(actionBarTitleView.getContext().getAssets(), actionBarTitleView.getContext().getString(R.string.fontname));
				  if(actionBarTitleView != null){
				      actionBarTitleView.setTypeface(typeface);
				  }
		  }
		
		display_event=(TextView)findViewById(R.id.textview);
		list=(ListView)findViewById(R.id.list);
		
		caldroidFragment = new CaldroidFragment();
		
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
			args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

			// Uncomment this to customize startDayOfWeek
			// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
			// CaldroidFragment.TUESDAY); // Tuesday
			caldroidFragment.setArguments(args);
		}

		utils.ls("entered in acad calendar ------2222");
		
		DatabaseHandler db = new DatabaseHandler(this);
		String result1="";	
		
		//db.setAcadCalender();
		result1=db.getAcadCalender();
		db.close();
		store= result1.split("~");
		System.out.println("result1---" + result1);
	//	System.out.println("store0---" + store[0]);
	//	System.out.println("store0---" + store[1]);

		for(int i=0;i<store.length;++i){
			System.out.println("in loop---" + Integer.toString(size));
			size=size+1;
		 String[] parts = store[i].split(",");
		
		 utils.ls("entered in acad calendar ------333");
		 
		System.out.println("parts_length---"+Integer.toString(parts.length));
	  final String start_string[]=parts[2].split("\\s+");
	    final String end_string[]=parts[3].split("\\s+");
	    
	   date_start_array.add(start_string[0]);
	    time_start_array.add(start_string[1]);
	    date_end_array.add(end_string[0]);
	    time_end_array.add(end_string[1]);
	    title_array.add(parts[0]);
	     description_array.add(parts[1]);
	    venue_array.add(parts[4]);
	    special_guest_array.add(parts[5]);
	    extra_details_array.add(parts[6]);
        System.out.println("parts : -- " + parts);
		}
		setCustomResourceForDates();
		final MySimpleArrayAdapter list_adapter =new MySimpleArrayAdapter(this, date_start_array, title_array, date_start_array.size());
		list.setAdapter(list_adapter);
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				 // ListView Clicked item index
                int itemPosition     = position;
                
                // ListView Clicked item value
                String  itemValue    = (String) list.getItemAtPosition(position);
               
            	for(int j=0;j<date_start_array.size();++j){
    				if(itemValue.equals(date_start_array.get(j))){
    					display_event.setText(title_array.get(j));
    					Intent myIntent=new Intent(AcadCalender.this,EventDisplayAcadCal.class);
    					String [] print =new String[] {title_array.get(j),date_start_array.get(j),
    							time_start_array.get(j),date_end_array.get(j),time_end_array.get(j),
    							description_array.get(j),special_guest_array.get(j),venue_array.get(j),
    							extra_details_array.get(j)};
    					myIntent.putExtra("firstKeyName",print);
    				
    					startActivity(myIntent);
    					break;
    				}
    				
    				}
				
			}
			
		});
		list.setVisibility(View.GONE);
		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();
	
		// Setup Caldroid
		
		caldroidFragment.setCaldroidListener(setuplistener());
		
		
		
		display_event.setClickable(true);		
		display_event.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				if(display_event.getText() !=""){
					int j= display_event_flag;
				Intent myIntent=new Intent(AcadCalender.this,EventDisplayAcadCal.class);
				String [] print =new String[] {title_array.get(j),date_start_array.get(j),
						time_start_array.get(j),date_end_array.get(j),time_end_array.get(j),
						description_array.get(j),special_guest_array.get(j),venue_array.get(j),
						extra_details_array.get(j)};
				myIntent.putExtra("firstKeyName",print);
			
				startActivity(myIntent);
				
				display_event.setText("");
				}
			}
		});

}
	public CaldroidListener setuplistener(){
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				String temp=formatter.format(date);
				//Toast.makeText(getApplicationContext(), formatter.format(date),
					//	Toast.LENGTH_SHORT).show();
				for(int j=0;j<date_start_array.size();++j){
				if(temp.equals(date_start_array.get(j))){
					display_event.setText(title_array.get(j));
					
					
					display_event_flag=j;
					/*Intent myIntent=new Intent(AcadCalender.this,EventDisplayAcadCal.class);
					String [] print =new String[] {title_array.get(j),date_start_array.get(j),
							time_start_array.get(j),date_end_array.get(j),time_end_array.get(j),
							description_array.get(j),special_guest_array.get(j),venue_array.get(j),
							extra_details_array.get(j)};
					myIntent.putExtra("firstKeyName",print);
				
					startActivity(myIntent);*/
					break;
				}
				
				}
			}
	};
	return listener;
	}
	public void setup_calender(){
		
		caldroidFragment = new CaldroidFragment();
		Bundle args = new Bundle();
		Calendar cal = Calendar.getInstance();
		args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
		args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
		args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
		args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

		// Uncomment this to customize startDayOfWeek
		// args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
		// CaldroidFragment.TUESDAY); // Tuesday
		caldroidFragment.setArguments(args);
		setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();
		
		
		caldroidFragment.setCaldroidListener(setuplistener());	
	}
public void month_clicked(View v){
	
	//final MySimpleArrayAdapter list_adapter =new MySimpleArrayAdapter(this, new ArrayList<String>(),new ArrayList<String>(), 0);
	//list.setAdapter(list_adapter);
	list.setVisibility(View.GONE);
	display_event.setVisibility(View.VISIBLE);
	calenderview=(LinearLayout)findViewById(R.id.calendar1);
	setup_calender();
	calenderview.setVisibility(View.VISIBLE);	
	
		
	}

public void all_clicked(View v){
	calenderview=(LinearLayout)findViewById(R.id.calendar1);
	calenderview.setVisibility(View.GONE);
	display_event.setVisibility(View.GONE);
	//final MySimpleArrayAdapter list_adapter =new MySimpleArrayAdapter(this, date_start_array, title_array, date_start_array.size());
	//list.setAdapter(list_adapter);
	list.setVisibility(View.VISIBLE);
}

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> dates_array_dummy;
	private final ArrayList<String> events;
	private final int size2;
	public MySimpleArrayAdapter(Context context, ArrayList<String>values,ArrayList<String> value1,int size1) {
		super(context, R.layout.row_layout_list, values);
		this.context = context;
		this.dates_array_dummy = values;
		this.events=value1;
		this.size2=size1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rowView = inflater.inflate(R.layout.row_layout_list, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.textView1);
	
		// Change the icon for Windows and iPhone

		String s = dates_array_dummy.get(position);
		textView.setText(s);
		TextView tv2=(TextView) rowView.findViewById(R.id.textView2);
		tv2.setText(events.get(position));
		return rowView;
	}
}


@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

	}
	
public void onBackPressed() {
	   Intent intent = new Intent(this, Home.class);
	   startActivity(intent);
	 }
	
}
