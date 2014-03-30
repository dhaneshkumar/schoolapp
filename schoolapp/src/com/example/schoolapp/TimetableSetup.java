package com.example.schoolapp;


import library.TabsPagerAdapter;
import nav_drawer.commonDrawer;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;



	public class TimetableSetup  extends commonDrawer implements
			ActionBar.TabListener {

		private ViewPager viewPager;
		private TabsPagerAdapter mAdapter;
		private ActionBar actionBar;
		// Tab titles
		private String[] tabs = { "MON", "TUE", "WED", "THU","FRI", "SAT" };

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//setContentView(R.layout.timetablesetup);
			
			LayoutInflater inflater = (LayoutInflater) this
		            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View contentView = inflater.inflate(R.layout.timetablesetup, null, false);
		    mDrawerLayout.addView(contentView, 0);
		
			Typeface tf= Typeface.createFromAsset(getAssets(), getString(R.string.fontname));

			// Initilization
			viewPager = (ViewPager) findViewById(R.id.pager);
			
			
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
	    
			  
			 //Action bar description
			actionBar = getSupportActionBar();
			actionBar.setTitle("Timetable");
			actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.app_back_color)));
		
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setBackgroundDrawable(new 
					   ColorDrawable(getResources().getColor(R.color.profile_selected)));  

			
			mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
			viewPager.setAdapter(mAdapter);
					
			// Adding Tabs
			for (String tab_name : tabs) {
				actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
			}

			/**
			 * on swiping the viewpager make respective tab selected
			 * */
			viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageSelected(int position) {
					// on changing the page
					// make respected tab selected
					actionBar.setSelectedNavigationItem(position);
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}

				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			});
		}

		/*@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// on tab selected
			// show respected fragment view
			
			viewPager.setCurrentItem(tab.getPosition());
		}*/

		@Override
		public void onTabReselected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(arg0.getPosition());
		}

		@Override
		public void onTabUnselected(Tab arg0,
				android.support.v4.app.FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
		
		
	

	}
