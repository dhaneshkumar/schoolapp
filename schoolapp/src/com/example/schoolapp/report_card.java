package com.example.schoolapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nav_drawer.commonDrawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class report_card extends commonDrawer {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private List<String> _h_quiz_type; // header titles
    private List<String> _h_quiz_no;
    private List<String> _h_quiz_subject;
    private List<String> _h_quiz_marks; 
    // child data in format of header title, child title
    private HashMap<String, List<String>> _c_chapter;
    private HashMap<String, List<String>> _c_rank;
    private HashMap<String, List<String>> _c_remark;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.reportcard);
		
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.reportcard, null, false);
	    mDrawerLayout.addView(contentView, 0);
	    
	    //setting action bar title
	    getSupportActionBar().setTitle("Report Card");
	    getSupportActionBar().setBackgroundDrawable(new 
				   ColorDrawable(Color.parseColor("#58a533"))); 
		
		//View rLayout =  findViewById(R.id.r_layout1);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.info);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		int right = (int) (getResources().getDisplayMetrics().widthPixels - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
		expListView.setIndicatorBounds(right - getResources().getDrawable(R.drawable.ic_launcher).getIntrinsicWidth(), right);

		prepareListData();
		System.out.println("check12");
		listAdapter = new ExpandableListAdapter(report_card.this, _h_quiz_type, _h_quiz_no,_h_quiz_subject,_h_quiz_marks,_c_chapter,_c_rank,_c_remark);
		System.out.println("check124");
		expListView.setAdapter(listAdapter);
		
	}
public void prepareListData(){
	_h_quiz_type=new ArrayList<String>();
	_h_quiz_no=new ArrayList<String>();
	_h_quiz_subject=new ArrayList<String>();
	_h_quiz_marks=new ArrayList<String>();
	
	 _c_chapter=new HashMap<String, List<String>>();
	 _c_rank=new HashMap<String, List<String>>();
	 _c_remark=new HashMap<String, List<String>>();

	 
	_h_quiz_type.add("Midsem");
	_h_quiz_no.add("1");
	_h_quiz_subject.add("PowerSytems");
	_h_quiz_marks.add("14/20");
	
	List<String> _c_chapter_string=new ArrayList<String> ();
	_c_chapter_string.add("1,2,3");
	List<String> _c_rank_string=new ArrayList<String> ();
	_c_rank_string.add("6/30");
	List<String> _c_remark_string=new ArrayList<String> ();
	_c_remark_string.add("Excellent");
	
	_c_chapter.put(_h_quiz_type.get(0),_c_chapter_string);
	_c_rank.put(_h_quiz_type.get(0),_c_rank_string);
	_c_remark.put(_h_quiz_type.get(0),_c_remark_string);
	
	
}

}
