package com.example.schoolapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class AcadCalender extends FragmentActivity{
	TextView display_event;
	String date_string = "2014-02-24";
	String date_string_2 ="2014-02-25";
	String event="Midsem over party";
	String event2="endsem lukha";
	Boolean month_selected=true;
	private CaldroidFragment caldroidFragment;
	Date date1;
	SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	ListView list;
	int size=2;
	LinearLayout calenderview;
	 String[] date_array = new String[] { "2014-02-24", 
             "2014-02-25"
            };
	 String[] event_array = new String[] { "Midsem over party", 
             "endsem lukha"
            };
	private void setCustomResourceForDates() {
		Calendar cal = Calendar.getInstance();
		Date blueDate;
		try {
			blueDate = new SimpleDateFormat("yyyy-MM-dd").parse(date_string);
		
       

		// Min date is last 7 days
		//cal.add(Calendar.DATE, -18);
		//Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 2);
		Date greenDate = new SimpleDateFormat("yyyy-MM-dd").parse(date_string_2);

		if (caldroidFragment != null) {
			caldroidFragment.setBackgroundResourceForDate(R.color.blue,
					blueDate);
			caldroidFragment.setBackgroundResourceForDate(R.color.green,
					greenDate);
			caldroidFragment.setTextColorForDate(R.color.white, blueDate);
			caldroidFragment.setTextColorForDate(R.color.white, greenDate);
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acadcalender);
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

		setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();
		
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				String temp=formatter.format(date);
				//Toast.makeText(getApplicationContext(), formatter.format(date),
					//	Toast.LENGTH_SHORT).show();
				if(temp.equals(date_string)){
					display_event.setText(event);
				}
				else if(temp.equals(date_string_2)){
					display_event.setText(event2);	
				}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);

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
		
		final CaldroidListener listener = new CaldroidListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				String temp=formatter.format(date);
				//Toast.makeText(getApplicationContext(), formatter.format(date),
					//	Toast.LENGTH_SHORT).show();
				if(temp.equals(date_string)){
					display_event.setText(event);
				}
				else if(temp.equals(date_string_2)){
					display_event.setText(event2);	
				}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);	
	}
public void month_clicked(View v){
	
	final MySimpleArrayAdapter list_adapter =new MySimpleArrayAdapter(this, new String[0],new String[0], 0);
	list.setAdapter(list_adapter);
	display_event.setVisibility(View.VISIBLE);
	calenderview=(LinearLayout)findViewById(R.id.calendar1);
	calenderview.setVisibility(View.VISIBLE);	
	setup_calender();
		
	}
public void all_clicked(View v){
	calenderview=(LinearLayout)findViewById(R.id.calendar1);
	calenderview.setVisibility(View.GONE);
	display_event.setVisibility(View.GONE);
	final MySimpleArrayAdapter list_adapter =new MySimpleArrayAdapter(this, date_array, event_array, size);
	list.setAdapter(list_adapter);
	
}
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] dates_array_dummy;
	private final String[] events;
	private final int size2;
	public MySimpleArrayAdapter(Context context, String[] values,String[] value1,int size1) {
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

		String s = dates_array_dummy[position];
		textView.setText(s);
		TextView tv2=(TextView) rowView.findViewById(R.id.textView2);
		tv2.setText(events[position]);
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
	
	
}
