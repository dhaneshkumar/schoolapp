package com.example.schoolapp;


import nav_drawer.commonDrawer;
import library.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.*;
import android.view.LayoutInflater;
import android.view.View;



	@SuppressLint("NewApi")
	public class TimetableSetup  extends FragmentActivity implements
			ActionBar.TabListener {

		private ViewPager viewPager;
		private TabsPagerAdapter mAdapter;
		private ActionBar actionBar;
		// Tab titles
		private String[] tabs = { "MON", "TUE", "WED", "THU","FRI", "SAT" };

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.timetablesetup);
			
			

			// Initilization
			viewPager = (ViewPager) findViewById(R.id.pager);
			actionBar = getActionBar();
			actionBar.setTitle("Timetable");
			getActionBar().setBackgroundDrawable(new 
					   ColorDrawable(getResources().getColor(R.color.profile_selected)));  
			//ActionBar bar = getSupportActionBar();
			//bar.setTitle("Student Profile");
			//actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.profile_selected)));
			
			mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

			viewPager.setAdapter(mAdapter);
			actionBar.setHomeButtonEnabled(false);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

			// Adding Tabs
			for (String tab_name : tabs) {
				actionBar.addTab(actionBar.newTab().setText(tab_name)
						.setTabListener(this));
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

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// on tab selected
			// show respected fragment view
			viewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

	}
