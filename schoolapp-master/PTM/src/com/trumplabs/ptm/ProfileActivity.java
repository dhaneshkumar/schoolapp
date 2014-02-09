package com.trumplabs.ptm;

import java.util.ArrayList;
import java.util.HashMap;

import library.DatabaseHandler;
import library.UserFunctions;

import org.json.JSONObject;

import spinner.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.TextView;

public class ProfileActivity extends ActionBarActivity implements OnItemSelectedListener, ActionBar.OnNavigationListener{
	ImageView img;
	DatabaseHandler d;
	UserFunctions u;
	TextView name,age,dob;
	TextView class_no,section,roll_no,interests,achieve,phone_no,email;
	TextView pname, profession, pphone, pemail;
	Spinner spinner;
	int edit_flag;
	TabHost student_tab;
	int curr_child;
	
	
	private DrawerLayout			mDrawerLayout;
	private ListView				mDrawerList;
	private ActionBarDrawerToggle	mDrawerToggle;

	private CharSequence			mDrawerTitle;
	private CharSequence			mTitle;
	private String[]				mPlanetTitles;
	
	private HashMap<String, String> studentsList;
	
	// Title navigation Spinner data
    private ArrayList<StudentSpinner> navSpinner;
     
    // Navigation adapter
    private SpinnerAdapter adapter;
    private ActionBar actionBar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		//Defining all the related objects from xml
		img = (ImageView)findViewById(R.id.pic);
		name = (TextView)findViewById(R.id.name);
		dob = (TextView)findViewById(R.id.dob);
		class_no = (TextView)findViewById(R.id.class_no);
		roll_no = (TextView)findViewById(R.id.roll_no);
		phone_no = (TextView)findViewById(R.id.phone);
		email = (TextView)findViewById(R.id.email);
		
		spinner = (Spinner)findViewById(R.id.parent_spinner);
		
		pname = (TextView)findViewById(R.id.pname);
		profession = (TextView)findViewById(R.id.profession);
		pphone = (TextView)findViewById(R.id.pphone);
		pemail = (TextView)findViewById(R.id.pemail);
		
		
		u = new UserFunctions();
		d = new DatabaseHandler(this);
		edit_flag = 0;
		curr_child = 1;
		studentsList = new HashMap<String ,String>();
		
		if(u.isUserLoggedIn(this)){
			//if not fetched, fetch now
			addParentsToDb(d.id);
			addChildrenToDb(d.id);
			
			//Set tabs
			//setStudentTabs();
			
			//Set the choices of parent type
			setSpinnerChoices();
			
			//Now parents table is not empty so fetch from DB
			spinner.setOnItemSelectedListener(this);
			
			//Show student details
			showStudentDetails(curr_child);
		}
		
		//Action Bar///////////////////////////////////////////////////////////////////////////
		actionBar = getSupportActionBar();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		//////////////Spinner
		setStudentChoices();
		
		// title drop down adapter
        adapter = new SpinnerAdapt(getApplicationContext(), navSpinner);
 
        // assigning the spinner navigation    
        actionBar.setListNavigationCallbacks(adapter, this);
        //////////////\Spinner
		//\Action Bar//////////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////////Drawer shit
		
		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.left_drawer_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		)
		{
			public void onDrawerClosed(View view)
			{
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView)
			{
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null)
		{
			selectItem(0);
		}
        
        
		//////////////////////////////////////////////////////////////////\Drawer Shit
	}
	
	//Set spinner choices
	private void setSpinnerChoices(){
		//set all the possible parentTypes
		ArrayList<String> parent_types = d.setParentTypes();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.spinner_layout, parent_types);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	
	private void setStudentChoices(){
		navSpinner = new ArrayList<StudentSpinner>();
		ArrayList<String>students = d.getStudents();
		for(int i = 0; i < d.getRowCount("Student"); i++){
			navSpinner.add(new StudentSpinner(students.get(i), R.drawable.ic_action_person));
			studentsList.put(i +"", students.get(i));
		}
	}
	
	private void showStudentDetails(int id){
		//if already fetched, display directly
		HashMap<String,String> h = d.getStudentDetails(id);
		name.setText(h.get("first_name") + " " + h.get("last_name"));
		dob.setText(h.get("dob"));
		phone_no.setText(h.get("phone_no"));
		email.setText(h.get("email_id"));
		roll_no.setText(h.get("id"));
	}
	
	//Adds all parents with id = id
	private boolean addParentsToDb(int id){
		if(d.getRowCount("Parent") <= 0){
			try{
				JSONObject json_parent = u.getDetails(id, "Parent");
				int count = json_parent.getInt("count");
				JSONObject json_parent_user = json_parent.getJSONObject("user");
				for(int i=0; i < count; i++){
					JSONObject json_parent_row = json_parent_user.getJSONObject(i + "");
					d.addParent(id,
	        				json_parent_row.getString("first_name"),
	            			json_parent_row.getString("last_name"),
	            			json_parent_row.getString("relation"),
	            			json_parent_row.getString("profession"),
	            			json_parent_row.getString("email_id"),
	            			json_parent_row.getString("phone_no"),
	            			json_parent_row.getInt("no_of_children"),
	            			json_parent_row.getString("sec_password"));
				}
			}
			catch(Exception e){
				System.out.print(e.toString());
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	//Adds all children of parents with id = id to Student
	private boolean addChildrenToDb(int id){
		if(d.getRowCount("Student") <= 0){
			try{
				JSONObject json_parent = u.getChildren(id);
				int count = json_parent.getInt("count");
				JSONObject json_user = json_parent.getJSONObject("user");
				for(int i=0; i<count; i++){
					JSONObject json_child_user = json_user.getJSONObject(i+"");
	                
	                int child_id = json_child_user.getInt("id");
	                JSONObject json_child = u.getDetails(child_id,"Student");
	                JSONObject json_child1 = json_child.getJSONObject("user");
	            	d.addStudent(child_id,
	        				json_child1.getString("first_name"),
	            			json_child1.getString("last_name"),
	            			json_child1.getInt("age"),
	            			json_child1.getString("dob"),
	            			json_child1.getString("email_id"),
	            			json_child1.getString("phone_no"));
				}
			}
			catch(Exception e){
				System.out.print(e.toString());
			}
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String choice = arg0.getItemAtPosition(arg2).toString();
		Log.e("Spinner", choice + " is selected");
		
		HashMap<String,String> h = d.getSelfDetails(choice);
		pname.setText(h.get("first_name") + " " + h.get("last_name"));
		profession.setText(h.get("profession"));
		pphone.setText(h.get("phone_no"));
		pemail.setText(h.get("email_id"));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.edit_profile).setVisible(!drawerOpen);
        if(drawerOpen){
        	actionBar.setDisplayShowTitleEnabled(false);
        	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        }
        else{
        	actionBar.setDisplayShowTitleEnabled(true);
        	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }
        return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)){
	          return true;
		}
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.edit_profile:{
	        	if(edit_flag == 0){
	        		item.setIcon(R.drawable.ic_action_save);
	        		edit_flag = 1;
	        	}
	        	else{
	        		item.setIcon(R.drawable.ic_action_edit);
	        		edit_flag = 0;
	        	}
	        	return true;
	        }
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @SuppressLint("NewApi")
    private void selectItem(int position)
	{
		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mPlanetTitles[position]);
		if(position == 1){
			Intent intent = new Intent("android.intent.action.MED");
			startActivity(intent);
		}
		mDrawerLayout.closeDrawer(mDrawerList);
	}
    
    @Override
	public void setTitle(CharSequence title)
	{
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
    
    @Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onNavigationItemSelected(int arg0, long arg1) {
		// TODO Auto-generated method stub
		showStudentDetails(d.getStudentId(studentsList.get(arg0 +"")));
		return false;
	}
}