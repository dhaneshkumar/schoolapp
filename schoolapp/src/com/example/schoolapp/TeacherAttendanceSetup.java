package com.example.schoolapp;


import nav_drawer.commonDrawer;
import library.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.*;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;



	@SuppressLint("NewApi")
	public class TeacherAttendanceSetup  extends FragmentActivity implements
			ActionBar.TabListener {

		private ViewPager viewPager;
		private TabsPagerAdapter mAdapter;
		private ActionBar actionBar;
		// Tab titles
		private String[] tabs ;
		//={"1","2","3","4","5","6","7","8","9","10"};
		
		public TeacherAttendanceSetup()
		{
			tabs =new String[32];
			
			for(int i=0;i<32;i++)
			{
				tabs[i]= String.valueOf(i+1);
			}
		}
		
		
		

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.teacherattendancesetup);
			
			
			

			// Initilization
			viewPager = (ViewPager) findViewById(R.id.pager);
			actionBar = getActionBar();
			actionBar.setTitle("Attendance");
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

		
		public static void setTabColor(TabHost tabhost) {
		    for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
		        tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FF0000")); //unselected
		    }
		    tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF")); // selected
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
