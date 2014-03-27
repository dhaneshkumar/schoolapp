package com.example.schoolapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nav_drawer.commonDrawer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;

import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

public class report_card extends commonDrawer {
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private List<String> _h_quiz_type; // header titles
    private List<String> _h_quiz_no;
    private List<String> _h_quiz_subject;
    private List<String> _h_quiz_marks; 
    private XYPlot plot;
    // child data in format of header title, child title
    private HashMap<String, List<String>> _c_chapter;
    private HashMap<String, List<String>> _c_rank;
    private HashMap<String, List<String>> _c_remark;
    
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = (LayoutInflater) this
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View contentView = inflater.inflate(R.layout.reportcard, null, false);
	    mDrawerLayout.addView(contentView, 0);
		
		 getSupportActionBar().setTitle("Report Card");
		    getSupportActionBar().setBackgroundDrawable(new 
					   ColorDrawable(getResources().getColor(R.color.profile_selected)));  
		
		 Typeface tf = Typeface.createFromAsset(getAssets(), getString(R.string.fontname));
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
		
		
		
		//potting graph
		
				plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
				plot.getGraphWidget().setRangeLabelPaint(null);
				XYGraphWidget g = plot.getGraphWidget();

			    g.setDomainLabelPaint(null);
			    g.setRangeLabelPaint(null);
			    g.setDomainOriginLabelPaint(null);
			    g.setRangeOriginLabelPaint(null);
			    //g.setGridBackgroundPaint(null);
			    g.setGridPaddingLeft(0);
			    g.setGridPaddingRight(0);
			    g.setMarginLeft(0);
			    g.getGridBackgroundPaint().setColor(Color.rgb(235, 235, 235));
			    g.position(-0.5f, XLayoutStyle.RELATIVE_TO_RIGHT, 
			            -0.5f, YLayoutStyle.RELATIVE_TO_BOTTOM,
			            AnchorPosition.CENTER);
			    g.setSize(new SizeMetrics(
			            0, SizeLayoutType.FILL,
			            0, SizeLayoutType.FILL));

			    
			    
			    LayoutManager l = plot.getLayoutManager();
			    l.remove(plot.getDomainLabelWidget());
			    l.remove(plot.getRangeLabelWidget());
			    //l.remove(plot.getLegendWidget());
			    //plot.getLegendWidget().position(-20, XLayoutStyle.RELATIVE_TO_RIGHT, -20, YLayoutStyle.RELATIVE_TO_BOTTOM,AnchorPosition.CENTER);
			    //plot.getLegendWidget();//.setSize(new SizeMetrics(11, SizeLayoutType.ABSOLUTE, 150, SizeLayoutType.ABSOLUTE));
			    g.setRangeLabelWidth(0);
			    g.setDomainLabelWidth(0);
			    g.setPadding(0, 0, 0, 0);
			    g.setMargins(4,4, 4, 4);
			    plot.getBackgroundPaint().setColor(R.color.white);;
			    g.setGridPadding(0, 0, 0, 0);
			    plot.setPlotMargins(0, 0, 0, 0);
			    plot.setPlotPadding(0, 0, 0, 0);
				Number[] series1Numbers = {10, 38, 55, 62, 37, 84,50};
				Number[] series2Numbers = {7, 28, 40, 45, 26, 70,30};
				Number[] series3Numbers = {1, 15, 35, 45, 15, 20,10};
		        //Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
				plot.setRangeBoundaries(0, 100, BoundaryMode.FIXED );
			    plot.setDomainBoundaries(-1, series1Numbers.length, BoundaryMode.FIXED );
			    plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 10);
			    plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
				XYSeries series1 = new SimpleXYSeries(
		                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
		                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
		                "Max");                             // Set the display title of the series
				XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"YOUR");
				XYSeries series3 = new SimpleXYSeries(Arrays.asList(series3Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"YOUR");
				// Create a formatter to use for drawing a series using LineAndPointRenderer
		        // and configure it from xml:
		        LineAndPointFormatter series1Format = new LineAndPointFormatter();
		        series1Format.setPointLabelFormatter(new PointLabelFormatter());
		        series1Format.configure(getApplicationContext(),
		                R.layout.line_point_formatter_with_plf1);
		        LineAndPointFormatter series2Format = new LineAndPointFormatter();
		        series2Format.setPointLabelFormatter(new PointLabelFormatter());
		        series2Format.configure(getApplicationContext(),
		                R.layout.line_point_formatter_with_plf2);
		        LineAndPointFormatter series3Format = new LineAndPointFormatter();
		        series3Format.setPointLabelFormatter(new PointLabelFormatter());
		        series3Format.configure(getApplicationContext(),
		                R.layout.line_point_formatter_with_plf3);
		        // add a new series' to the xyplot:
		        plot.addSeries(series1, series1Format);
		        plot.addSeries(series2, series2Format);
		        plot.addSeries(series3, series3Format);
				
		
		
		
		
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
