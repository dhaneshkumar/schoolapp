package nav_drawer;


import java.util.ArrayList;

import library.utils;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schoolapp.*;


public class commonDrawer extends ActionBarActivity {
	public String[] navigationList;
    public DrawerLayout mDrawerLayout;
    public LinearLayout linearLayout;
    public ListView mDrawerList;
    public ActionBarDrawerToggle mDrawerToggle;
    public CharSequence mTitle;
    public CharSequence mDrawerTitle;
    public TypedArray navMenuIcons;
    public ArrayList<NavDrawerItem> navDrawerItems;
	public NavDrawerListAdapter adapter;
	public int homeFlag=0;
	public int CONTENT_LAYOUT_ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.nav_drawer);
		
		utils ul = new utils();
		TextView tt = (TextView)findViewById(R.id.title);
		/*ul.setFont(tt);*/
		
		ul.ls("111111111111");

		CONTENT_LAYOUT_ID =R.layout.phonelist;
		mTitle = mDrawerTitle = getTitle();
		navigationList = getResources().getStringArray(R.array.navigationList);
		linearLayout = (LinearLayout) findViewById(R.id.LinearLayout);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        int j=R.id.left_drawer;
        
        ul.ls("2222");
        navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        
     // adding nav drawer items to array
     		navDrawerItems.add(new NavDrawerItem(navigationList[0], navMenuIcons.getResourceId(0, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[1], navMenuIcons.getResourceId(1, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[2], navMenuIcons.getResourceId(2, -1),true, "2"));
     		navDrawerItems.add(new NavDrawerItem(navigationList[3], navMenuIcons.getResourceId(3, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[4], navMenuIcons.getResourceId(4, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[5], navMenuIcons.getResourceId(5, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[6], navMenuIcons.getResourceId(6, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[7], navMenuIcons.getResourceId(7, -1)));
     		navDrawerItems.add(new NavDrawerItem(navigationList[8], navMenuIcons.getResourceId(8, -1)));
     		
     		ul.ls("3333333");
     	// Recycle the typed array
    		navMenuIcons.recycle();
    		
    		//if(mDrawerList != null)
    		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

    		// setting the nav drawer list adapter
    		adapter = new NavDrawerListAdapter(getApplicationContext(),
    				navDrawerItems);
    		mDrawerList.setAdapter(adapter);
    		// enabling action bar app icon and behaving it as toggle button
    		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    		getSupportActionBar().setHomeButtonEnabled(true);
    		
    		
    		ul.ls("4444444");
    		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
    				R.drawable.ic_drawer, //nav menu toggle icon
    				R.string.app_name, // nav drawer open - description for accessibility
    				R.string.app_name // nav drawer close - description for accessibility
    		) {
    			public void onDrawerClosed(View view) {
    				getSupportActionBar().setTitle(mTitle);
    				// calling onPrepareOptionsMenu() to show action bar icons
    				supportInvalidateOptionsMenu();
    			}

    			
    			public void onDrawerOpened(View drawerView) {
    				//getSupportActionBar().setTitle(mDrawerTitle);
    				// calling onPrepareOptionsMenu() to hide action bar icons
    				supportInvalidateOptionsMenu();
    			}
    		};
    		mDrawerLayout.setDrawerListener(mDrawerToggle);
    		ul.ls("1111555555555555");
    }

	
	protected void setContentLayout(int sourceId) {
	    View contentLayout = findViewById(CONTENT_LAYOUT_ID);
	    ViewGroup parent = (ViewGroup) contentLayout.getParent();
	    int index = parent.indexOfChild(contentLayout);

	    parent.removeView(contentLayout);
	    contentLayout = getLayoutInflater().inflate(sourceId, parent, false);
	    parent.addView(contentLayout, index);
	}



        
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);

		}
	}   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* Called whenever we call supportInvalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
     //  menu.findItem(R.id.action_search).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    
    /* *
	 * Called when supportInvalidateOptionsMenu() is triggered
	 */

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Intent fragment = null;
		switch (position) {
		case 0:
			 fragment = new Intent(this,  Home.class);			
			break;
		case 1:
			 fragment = new Intent(this,  profile_student.class);			
			break;
		case 2:
			 fragment = new Intent(this,  Messages.class);			
			break;
		case 3:
			fragment = new Intent(this,  TimetableSetup.class);
			break;
		case 4:
			fragment = new Intent(this, AcadCalender.class);
			break;
		case 5:
			fragment = new Intent(this, ContactList.class);
			break;
		case 6:
			fragment = new Intent(this, report_card.class);
			break;
		case 7:
			fragment = new Intent(this, Leaveapp.class);
			break;
		case 8:
			fragment = new Intent(this, settings_page.class);
			break;
		default:
			break;
		}

		if (fragment != null) {
			startActivity(fragment);

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navigationList[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void show(String str)
	{
		System.out.println(str);

	}

}